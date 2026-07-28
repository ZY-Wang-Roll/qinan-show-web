package com.luyingqinhua.repository;

import com.luyingqinhua.entity.TeamStat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TeamStatRepository extends JpaRepository<TeamStat, Long> {
    List<TeamStat> findAllByOrderBySortOrderAsc();
}
