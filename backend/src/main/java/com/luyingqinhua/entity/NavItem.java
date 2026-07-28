package com.luyingqinhua.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "nav_item")
public class NavItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 导航标识: "team" */
    @Column(name = "nav_id", length = 30)
    private String navId;

    /** 显示标签: "团队" */
    @Column(length = 30)
    private String label;

    /** 锚点: "#team" */
    @Column(length = 50)
    private String href;

    @Column(name = "sort_order")
    private Integer sortOrder;

    // ---- getters & setters ----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNavId() { return navId; }
    public void setNavId(String navId) { this.navId = navId; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public String getHref() { return href; }
    public void setHref(String href) { this.href = href; }
    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
}
