package com.luyingqinhua.repository;

import com.luyingqinhua.entity.FooterIcon;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FooterIconRepository extends JpaRepository<FooterIcon, Long> {
    List<FooterIcon> findAllByOrderBySortOrderAsc();
}
