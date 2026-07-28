package com.luyingqinhua.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "footer_icon")
public class FooterIcon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 图标图片 URL */
    @Column(name = "image_url", length = 500)
    private String imageUrl;

    /** 平台名称 */
    @Column(length = 50)
    private String name;

    /** 跳转链接 */
    @Column(length = 200)
    private String link;

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
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
    public Boolean getEnabled() { return enabled; }
    public void setEnabled(Boolean enabled) { this.enabled = enabled; }
}
