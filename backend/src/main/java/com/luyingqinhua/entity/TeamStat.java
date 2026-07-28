package com.luyingqinhua.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "team_stat")
public class TeamStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 数据标签: "核心队员" */
    @Column(length = 50)
    private String label;

    /** 数值（可动画滚动） */
    @Column(length = 20)
    private String value;

    /** 后缀: "K+", "天", "个" */
    @Column(length = 10)
    private String suffix;

    /** 排序 */
    @Column(name = "sort_order")
    private Integer sortOrder;

    /** 是否纯文本（非数字滚动） */
    @Column(name = "is_text")
    private Boolean isText = false;

    // ---- getters & setters ----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
    public String getSuffix() { return suffix; }
    public void setSuffix(String suffix) { this.suffix = suffix; }
    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
    public Boolean getIsText() { return isText; }
    public void setIsText(Boolean isText) { this.isText = isText; }
}
