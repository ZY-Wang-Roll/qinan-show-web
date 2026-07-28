package com.luyingqinhua.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "hero_slide")
public class HeroSlide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 轮播图片 URL */
    @Column(name = "image_url", length = 500)
    private String imageUrl;

    /** 轮播标题（大标题） */
    @Column(length = 200)
    private String title;

    /** 轮播副标题（小标签） */
    @Column(length = 200)
    private String subtitle;

    /** 轮播描述文字 */
    @Column(columnDefinition = "TEXT")
    private String description;

    /** 排序 */
    @Column(name = "sort_order")
    private Integer sortOrder;

    /** 是否启用 */
    @Column(nullable = false)
    private Boolean enabled = true;

    // ---- getters & setters ----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getSubtitle() { return subtitle; }
    public void setSubtitle(String subtitle) { this.subtitle = subtitle; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
    public Boolean getEnabled() { return enabled; }
    public void setEnabled(Boolean enabled) { this.enabled = enabled; }
}
