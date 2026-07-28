package com.luyingqinhua.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 小标签: "核心产品 01" */
    @Column(name = "sub_label", length = 50)
    private String subLabel;

    /** 产品名: "秦安水蜜桃" */
    @Column(length = 50)
    private String name;

    /** 大字前缀: "秦安" */
    @Column(name = "name_prefix", length = 20)
    private String namePrefix;

    /** 产品描述 */
    @Column(columnDefinition = "TEXT")
    private String description;

    /** 产品图片URL */
    @Column(name = "image_url")
    private String imageUrl;

    /** 图片占位文字 */
    @Column(name = "image_placeholder", length = 200)
    private String imagePlaceholder;

    /** 排序 */
    @Column(name = "sort_order")
    private Integer sortOrder;

    /** 特色标签 */
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("sortOrder ASC")
    private List<ProductTag> tags = new ArrayList<>();

    /** 关键数据 */
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("sortOrder ASC")
    private List<ProductStat> stats = new ArrayList<>();

    // ---- getters & setters ----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSubLabel() { return subLabel; }
    public void setSubLabel(String subLabel) { this.subLabel = subLabel; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getNamePrefix() { return namePrefix; }
    public void setNamePrefix(String namePrefix) { this.namePrefix = namePrefix; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public String getImagePlaceholder() { return imagePlaceholder; }
    public void setImagePlaceholder(String imagePlaceholder) { this.imagePlaceholder = imagePlaceholder; }
    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
    public List<ProductTag> getTags() { return tags; }
    public void setTags(List<ProductTag> tags) { this.tags = tags; }
    public List<ProductStat> getStats() { return stats; }
    public void setStats(List<ProductStat> stats) { this.stats = stats; }
}
