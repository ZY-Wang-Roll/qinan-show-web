package com.luyingqinhua.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "culture_item")
public class CultureItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 小标签: "文脉" / "非遗" / "地貌" */
    @Column(name = "subtitle_tag", length = 20)
    private String subtitleTag;

    /** 标题: "陇右文脉" */
    @Column(length = 100)
    private String title;

    /** 描述文本 */
    @Column(columnDefinition = "TEXT")
    private String description;

    /** 配图URL */
    @Column(name = "image_url")
    private String imageUrl;

    /** 配图占位文字 */
    @Column(name = "image_placeholder", length = 200)
    private String imagePlaceholder;

    /** 排序 */
    @Column(name = "sort_order")
    private Integer sortOrder;

    /** 关联标签 */
    @OneToMany(mappedBy = "cultureItem", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("sortOrder ASC")
    private List<CultureTag> tags = new ArrayList<>();

    // ---- getters & setters ----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSubtitleTag() { return subtitleTag; }
    public void setSubtitleTag(String subtitleTag) { this.subtitleTag = subtitleTag; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public String getImagePlaceholder() { return imagePlaceholder; }
    public void setImagePlaceholder(String imagePlaceholder) { this.imagePlaceholder = imagePlaceholder; }
    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
    public List<CultureTag> getTags() { return tags; }
    public void setTags(List<CultureTag> tags) { this.tags = tags; }
}
