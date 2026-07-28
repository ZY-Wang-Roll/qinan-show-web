package com.luyingqinhua.entity;

import jakarta.persistence.*;

/** 全站全局配置：footer/closing 图片等 */
@Entity
@Table(name = "site_config")
public class SiteConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 关闭/Footer 区域背景图 */
    @Column(name = "closing_image")
    private String closingImage;

    /** Footer 品牌名 */
    @Column(name = "closing_brand", length = 50)
    private String closingBrand;

    /** Footer 副标题 */
    @Column(name = "closing_subtitle", length = 200)
    private String closingSubtitle;

    // ---- getters & setters ----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getClosingImage() { return closingImage; }
    public void setClosingImage(String closingImage) { this.closingImage = closingImage; }
    public String getClosingBrand() { return closingBrand; }
    public void setClosingBrand(String closingBrand) { this.closingBrand = closingBrand; }
    public String getClosingSubtitle() { return closingSubtitle; }
    public void setClosingSubtitle(String closingSubtitle) { this.closingSubtitle = closingSubtitle; }
}
