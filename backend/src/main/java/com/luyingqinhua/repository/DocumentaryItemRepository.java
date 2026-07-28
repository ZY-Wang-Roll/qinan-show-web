package com.luyingqinhua.repository;

import com.luyingqinhua.entity.DocumentaryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DocumentaryItemRepository extends JpaRepository<DocumentaryItem, Long> {
    List<DocumentaryItem> findAllByOrderBySortOrderAsc();
}
