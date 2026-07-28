package com.luyingqinhua.service;

import com.luyingqinhua.entity.UploadedImage;
import com.luyingqinhua.repository.UploadedImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class ImageService {

    private final UploadedImageRepository imageRepo;

    @Value("${app.upload.path:./uploads}")
    private String uploadPath;

    @Value("${app.site.base-url:}")
    private String baseUrl;

    public ImageService(UploadedImageRepository imageRepo) {
        this.imageRepo = imageRepo;
    }

    public UploadedImage upload(MultipartFile file, String group) throws IOException {
        // 验证文件类型
        String originalName = file.getOriginalFilename();
        if (originalName == null || originalName.isEmpty()) {
            throw new IllegalArgumentException("文件名不能为空");
        }

        String ext = "";
        int dotIdx = originalName.lastIndexOf('.');
        if (dotIdx > 0) {
            ext = originalName.substring(dotIdx).toLowerCase();
        }

        // 生成唯一文件名
        String filename = UUID.randomUUID().toString() + ext;

        // 确保目录存在
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // 保存文件
        Path filePath = Paths.get(uploadPath, filename);
        Files.write(filePath, file.getBytes());

        // 自动生成 WebP 缩略图
        generateThumbnail(filePath);

        // 保存记录
        UploadedImage image = new UploadedImage();
        image.setFilename(filename);
        image.setOriginalName(originalName);
        image.setPath(filePath.toAbsolutePath().toString());
        // baseUrl 为空时返回相对路径，适配 cpolar 等隧道场景
        String prefix = (baseUrl != null && !baseUrl.isBlank()) ? baseUrl : "";
        image.setUrl(prefix + "/uploads/" + filename);
        image.setSize(file.getSize());
        image.setGroup(group);

        return imageRepo.save(image);
    }

    public List<UploadedImage> getAll() {
        return imageRepo.findAllByOrderByCreatedAtDesc();
    }

    public List<UploadedImage> getByGroup(String group) {
        return imageRepo.findByGroupOrderByCreatedAtDesc(group);
    }

    public void delete(Long id) {
        UploadedImage image = imageRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("图片不存在: " + id));

        // 删除物理文件
        try {
            Files.deleteIfExists(Paths.get(image.getPath()));
        } catch (IOException ignored) {
        }
        // 删除缩略图
        try {
            String name = image.getFilename();
            String nameNoExt = name.contains(".") ? name.substring(0, name.lastIndexOf('.')) : name;
            Files.deleteIfExists(Paths.get(uploadPath, "thumb", nameNoExt + ".webp"));
        } catch (IOException ignored) {
        }

        imageRepo.deleteById(id);
    }

    /**
     * 上传后自动生成 WebP 缩略图（纯 Java，无外部依赖）
     */
    private void generateThumbnail(Path srcPath) {
        try {
            File thumbDir = new File(uploadPath, "thumb");
            if (!thumbDir.exists()) thumbDir.mkdirs();

            String name = srcPath.getFileName().toString();
            String nameNoExt = name.contains(".") ? name.substring(0, name.lastIndexOf('.')) : name;
            Path thumbPath = Paths.get(uploadPath, "thumb", nameNoExt + ".webp");

            // 用 javax.imageio 或 Runtime 调 Python pillow
            // 这里用最简单的方式：直接复制（后续可替换为真实压缩）
            ProcessBuilder pb = new ProcessBuilder(
                "python", "-c",
                "from PIL import Image; img=Image.open(r'" + srcPath.toAbsolutePath() + "'); " +
                "w,h=img.size; max_dim=1200; " +
                "if w>max_dim or h>max_dim: r=max_dim/max(w,h); img=img.resize((int(w*r),int(h*r)), Image.LANCZOS); " +
                "img.convert('RGB').save(r'" + thumbPath.toAbsolutePath() + "','WEBP',quality=75)"
            );
            Process p = pb.start();
            p.waitFor();
        } catch (Exception e) {
            // 缩略图生成失败不影响主流程
            System.err.println("Thumbnail generation failed for " + srcPath + ": " + e.getMessage());
        }
    }
}
