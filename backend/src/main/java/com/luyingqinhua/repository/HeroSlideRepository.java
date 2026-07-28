package com.luyingqinhua.repository;

import com.luyingqinhua.entity.HeroSlide;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HeroSlideRepository extends JpaRepository<HeroSlide, Long> {
    List<HeroSlide> findAllByOrderBySortOrderAsc();
}
