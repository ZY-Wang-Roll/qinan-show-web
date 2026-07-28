package com.luyingqinhua.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "uploaded_image")
public class UploadedImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 存储文件名(UUID) */
    @Column(length = 100)
    private String filename;

    /** 原始文件名 */
    @Column(name = "original_name", length = 200)
    private String originalName;

    /** 存储路径 */
    @Column(length = 500)
    private String path;

    /** 访问URL */
    @Column(length = 500)
    private String url;

    /** 文件大小(bytes) */
    private Long size;

    /** 分组标签 */
    @Column(length = 50)
    private String group;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // ---- getters & setters ----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFilename() { return filename; }
    public void setFilename(String filename) { this.filename = filename; }
    public String getOriginalName() { return originalName; }
    public void setOriginalName(String originalName) { this.originalName = originalName; }
    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public Long getSize() { return size; }
    public void setSize(Long size) { this.size = size; }
    public String getGroup() { return group; }
    public void setGroup(String group) { this.group = group; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
