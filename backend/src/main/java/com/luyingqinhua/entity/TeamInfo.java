package com.luyingqinhua.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "team_info")
public class TeamInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 模块小标签: "团队概览" */
    @Column(length = 50)
    private String eyebrow;

    /** 大标题: "我们是谁" */
    @Column(length = 100)
    private String title;

    /** 副标题/slogan */
    @Column(length = 200)
    private String subtitle;

    /** 团队描述 */
    @Column(columnDefinition = "TEXT")
    private String description;

    /** 使命标题: "以镜头为笔，以土地为卷..." */
    @Column(name = "mission_title", columnDefinition = "TEXT")
    private String missionTitle;

    /** 使命描述 */
    @Column(name = "mission_desc", columnDefinition = "TEXT")
    private String missionDesc;

    /** 格言 */
    @Column(columnDefinition = "TEXT")
    private String motto;

    /** 青年愿景配图 */
    @Column(name = "mission_image_url", length = 500)
    private String missionImageUrl;

    /** 首屏封面大图 (Hero background) */
    @Column(name = "image_url")
    private String imageUrl;

    /** 团队合影大图 (Hero image fallback) */
    @Column(name = "hero_image_url")
    private String heroImageUrl;

    /** 团队 LOGO (圆形，用于团队板块) */
    @Column(name = "team_logo_url")
    private String teamLogoUrl;

    // ---- 团队照片 4 张 ----
    @Column(name = "photo1_url")
    private String photo1Url;
    @Column(name = "photo1_label", length = 50)
    private String photo1Label;

    @Column(name = "photo2_url")
    private String photo2Url;
    @Column(name = "photo2_label", length = 50)
    private String photo2Label;

    @Column(name = "photo3_url")
    private String photo3Url;
    @Column(name = "photo3_label", length = 50)
    private String photo3Label;

    @Column(name = "photo4_url")
    private String photo4Url;
    @Column(name = "photo4_label", length = 50)
    private String photo4Label;

    // ---- getters & setters ----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEyebrow() { return eyebrow; }
    public void setEyebrow(String eyebrow) { this.eyebrow = eyebrow; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getSubtitle() { return subtitle; }
    public void setSubtitle(String subtitle) { this.subtitle = subtitle; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getMissionTitle() { return missionTitle; }
    public void setMissionTitle(String missionTitle) { this.missionTitle = missionTitle; }
    public String getMissionDesc() { return missionDesc; }
    public void setMissionDesc(String missionDesc) { this.missionDesc = missionDesc; }
    public String getMotto() { return motto; }
    public void setMotto(String motto) { this.motto = motto; }
    public String getMissionImageUrl() { return missionImageUrl; }
    public void setMissionImageUrl(String missionImageUrl) { this.missionImageUrl = missionImageUrl; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public String getHeroImageUrl() { return heroImageUrl; }
    public void setHeroImageUrl(String heroImageUrl) { this.heroImageUrl = heroImageUrl; }
    public String getTeamLogoUrl() { return teamLogoUrl; }
    public void setTeamLogoUrl(String teamLogoUrl) { this.teamLogoUrl = teamLogoUrl; }
    public String getPhoto1Url() { return photo1Url; }
    public void setPhoto1Url(String photo1Url) { this.photo1Url = photo1Url; }
    public String getPhoto1Label() { return photo1Label; }
    public void setPhoto1Label(String photo1Label) { this.photo1Label = photo1Label; }
    public String getPhoto2Url() { return photo2Url; }
    public void setPhoto2Url(String photo2Url) { this.photo2Url = photo2Url; }
    public String getPhoto2Label() { return photo2Label; }
    public void setPhoto2Label(String photo2Label) { this.photo2Label = photo2Label; }
    public String getPhoto3Url() { return photo3Url; }
    public void setPhoto3Url(String photo3Url) { this.photo3Url = photo3Url; }
    public String getPhoto3Label() { return photo3Label; }
    public void setPhoto3Label(String photo3Label) { this.photo3Label = photo3Label; }
    public String getPhoto4Url() { return photo4Url; }
    public void setPhoto4Url(String photo4Url) { this.photo4Url = photo4Url; }
    public String getPhoto4Label() { return photo4Label; }
    public void setPhoto4Label(String photo4Label) { this.photo4Label = photo4Label; }
}
