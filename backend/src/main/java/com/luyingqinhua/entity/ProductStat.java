package com.luyingqinhua.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "product_stat")
public class ProductStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 数据标签: "年均日照" */
    @Column(length = 50)
    private String label;

    /** 数值: "1800" */
    @Column(length = 20)
    private String value;

    /** 后缀: "h+", "°C", "万亩" */
    @Column(length = 10)
    private String suffix;

    /** 是否纯文本（非数字） */
    @Column(name = "is_text")
    private Boolean isText = false;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    // ---- getters & setters ----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
    public String getSuffix() { return suffix; }
    public void setSuffix(String suffix) { this.suffix = suffix; }
    public Boolean getIsText() { return isText; }
    public void setIsText(Boolean isText) { this.isText = isText; }
    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
}
