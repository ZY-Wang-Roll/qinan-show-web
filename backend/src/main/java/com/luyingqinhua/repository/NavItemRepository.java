package com.luyingqinhua.repository;

import com.luyingqinhua.entity.NavItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NavItemRepository extends JpaRepository<NavItem, Long> {
    List<NavItem> findAllByOrderBySortOrderAsc();
}
