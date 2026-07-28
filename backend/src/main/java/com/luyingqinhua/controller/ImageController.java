package com.luyingqinhua.controller;

import com.luyingqinhua.common.Result;
import com.luyingqinhua.entity.UploadedImage;
import com.luyingqinhua.service.ImageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public Result<UploadedImage> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "group", defaultValue = "general") String group) throws IOException {
        return Result.ok(imageService.upload(file, group));
    }

    @GetMapping
    public Result<List<UploadedImage>> list(
            @RequestParam(value = "group", required = false) String group) {
        if (group != null && !group.isEmpty()) {
            return Result.ok(imageService.getByGroup(group));
        }
        return Result.ok(imageService.getAll());
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        imageService.delete(id);
        return Result.ok();
    }
}
