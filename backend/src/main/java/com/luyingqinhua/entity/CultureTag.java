package com.luyingqinhua.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "culture_tag")
public class CultureTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 标签名: "大地湾遗址" */
    @Column(length = 50)
    private String name;

    /** hover提示: "八千年文明曙光" */
    @Column(length = 200)
    private String note;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "culture_item_id")
    @JsonIgnore
    private CultureItem cultureItem;

    // ---- getters & setters ----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
    public CultureItem getCultureItem() { return cultureItem; }
    public void setCultureItem(CultureItem cultureItem) { this.cultureItem = cultureItem; }
}
