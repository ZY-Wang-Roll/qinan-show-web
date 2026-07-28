package com.luyingqinhua.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "documentary_item")
public class DocumentaryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 照片标签 */
    @Column(length = 100)
    private String label;

    /** 列跨度(1或2) */
    @Column(name = "cols_span")
    private Integer colsSpan = 1;

    /** 行跨度(1或2) */
    @Column(name = "rows_span")
    private Integer rowsSpan = 1;

    /** 时间标签: "7月12日 · 刘坪镇" */
    @Column(name = "time_label", length = 100)
    private String timeLabel;

    /** 备注: "深入桃园与果农交流" */
    @Column(length = 200)
    private String note;

    /** 图片URL */
    @Column(name = "image_url")
    private String imageUrl;

    /** 背景渐变色 */
    @Column(name = "bg_gradient", length = 200)
    private String bgGradient;

    /** emoji图标 */
    @Column(length = 10)
    private String icon;

    /** 排序 */
    @Column(name = "sort_order")
    private Integer sortOrder;

    // ---- getters & setters ----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public Integer getColsSpan() { return colsSpan; }
    public void setColsSpan(Integer colsSpan) { this.colsSpan = colsSpan; }
    public Integer getRowsSpan() { return rowsSpan; }
    public void setRowsSpan(Integer rowsSpan) { this.rowsSpan = rowsSpan; }
    public String getTimeLabel() { return timeLabel; }
    public void setTimeLabel(String timeLabel) { this.timeLabel = timeLabel; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public String getBgGradient() { return bgGradient; }
    public void setBgGradient(String bgGradient) { this.bgGradient = bgGradient; }
    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }
    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
}
