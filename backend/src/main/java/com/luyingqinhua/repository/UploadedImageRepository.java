package com.luyingqinhua.repository;

import com.luyingqinhua.entity.UploadedImage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UploadedImageRepository extends JpaRepository<UploadedImage, Long> {
    List<UploadedImage> findAllByOrderByCreatedAtDesc();
    List<UploadedImage> findByGroupOrderByCreatedAtDesc(String group);
}
