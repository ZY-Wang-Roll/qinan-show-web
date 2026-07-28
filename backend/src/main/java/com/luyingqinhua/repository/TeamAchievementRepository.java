package com.luyingqinhua.repository;

import com.luyingqinhua.entity.TeamAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TeamAchievementRepository extends JpaRepository<TeamAchievement, Long> {
    List<TeamAchievement> findAllByOrderBySortOrderAsc();
}
