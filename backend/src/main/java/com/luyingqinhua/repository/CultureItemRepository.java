package com.luyingqinhua.repository;

import com.luyingqinhua.entity.CultureItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CultureItemRepository extends JpaRepository<CultureItem, Long> {
    List<CultureItem> findAllByOrderBySortOrderAsc();
}
