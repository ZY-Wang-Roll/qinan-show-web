package com.luyingqinhua.service;

import com.luyingqinhua.entity.TeamAchievement;
import com.luyingqinhua.entity.TeamInfo;
import com.luyingqinhua.entity.TeamStat;
import com.luyingqinhua.repository.TeamAchievementRepository;
import com.luyingqinhua.repository.TeamInfoRepository;
import com.luyingqinhua.repository.TeamStatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamInfoRepository infoRepo;
    private final TeamStatRepository statRepo;
    private final TeamAchievementRepository achievementRepo;

    public TeamService(TeamInfoRepository infoRepo, TeamStatRepository statRepo, TeamAchievementRepository achievementRepo) {
        this.infoRepo = infoRepo;
        this.statRepo = statRepo;
        this.achievementRepo = achievementRepo;
    }

    // ====== TeamInfo ======
    public TeamInfo getInfo() {
        return infoRepo.findAll().stream().findFirst().orElse(null);
    }

    public TeamInfo updateInfo(TeamInfo updated) {
        TeamInfo info = getInfo();
        if (info == null) {
            info = new TeamInfo();
        }
        if (updated.getEyebrow() != null) info.setEyebrow(updated.getEyebrow());
        if (updated.getTitle() != null) info.setTitle(updated.getTitle());
        if (updated.getSubtitle() != null) info.setSubtitle(updated.getSubtitle());
        if (updated.getDescription() != null) info.setDescription(updated.getDescription());
        if (updated.getMissionTitle() != null) info.setMissionTitle(updated.getMissionTitle());
        if (updated.getMissionDesc() != null) info.setMissionDesc(updated.getMissionDesc());
        if (updated.getMotto() != null) info.setMotto(updated.getMotto());
        if (updated.getMissionImageUrl() != null) info.setMissionImageUrl(updated.getMissionImageUrl());
        if (updated.getImageUrl() != null) info.setImageUrl(updated.getImageUrl());
        if (updated.getHeroImageUrl() != null) info.setHeroImageUrl(updated.getHeroImageUrl());
        if (updated.getTeamLogoUrl() != null) info.setTeamLogoUrl(updated.getTeamLogoUrl());
        if (updated.getPhoto1Url() != null) info.setPhoto1Url(updated.getPhoto1Url());
        if (updated.getPhoto1Label() != null) info.setPhoto1Label(updated.getPhoto1Label());
        if (updated.getPhoto2Url() != null) info.setPhoto2Url(updated.getPhoto2Url());
        if (updated.getPhoto2Label() != null) info.setPhoto2Label(updated.getPhoto2Label());
        if (updated.getPhoto3Url() != null) info.setPhoto3Url(updated.getPhoto3Url());
        if (updated.getPhoto3Label() != null) info.setPhoto3Label(updated.getPhoto3Label());
        if (updated.getPhoto4Url() != null) info.setPhoto4Url(updated.getPhoto4Url());
        if (updated.getPhoto4Label() != null) info.setPhoto4Label(updated.getPhoto4Label());
        return infoRepo.save(info);
    }

    // ====== TeamStat ======
    public List<TeamStat> getStats() {
        return statRepo.findAllByOrderBySortOrderAsc();
    }

    public TeamStat updateStat(Long id, TeamStat updated) {
        TeamStat stat = statRepo.findById(id).orElseThrow(() -> new RuntimeException("数据不存在: " + id));
        if (updated.getLabel() != null) stat.setLabel(updated.getLabel());
        if (updated.getValue() != null) stat.setValue(updated.getValue());
        if (updated.getSuffix() != null) stat.setSuffix(updated.getSuffix());
        if (updated.getIsText() != null) stat.setIsText(updated.getIsText());
        return statRepo.save(stat);
    }

    // ====== TeamAchievement ======
    public List<TeamAchievement> getAchievements() {
        return achievementRepo.findAllByOrderBySortOrderAsc();
    }

    public TeamAchievement updateAchievement(Long id, TeamAchievement updated) {
        TeamAchievement a = achievementRepo.findById(id).orElseThrow(() -> new RuntimeException("数据不存在: " + id));
        if (updated.getValue() != null) a.setValue(updated.getValue());
        if (updated.getLabel() != null) a.setLabel(updated.getLabel());
        return achievementRepo.save(a);
    }
}
