package com.luyingqinhua.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "team_achievement")
public class TeamAchievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 成果数值/文本: "12" */
    @Column(length = 20)
    private String value;

    /** 成果标签: "篇调研报告" */
    @Column(length = 50)
    private String label;

    @Column(name = "sort_order")
    private Integer sortOrder;

    // ---- getters & setters ----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
}
