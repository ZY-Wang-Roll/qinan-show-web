package com.luyingqinhua.service;

import com.luyingqinhua.entity.HeroSlide;
import com.luyingqinhua.repository.HeroSlideRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HeroSlideService {

    private final HeroSlideRepository repo;

    public HeroSlideService(HeroSlideRepository repo) {
        this.repo = repo;
    }

    public List<HeroSlide> getAll() {
        return repo.findAllByOrderBySortOrderAsc();
    }

    public HeroSlide getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("轮播页不存在: " + id));
    }

    @Transactional
    public HeroSlide create(HeroSlide slide) {
        return repo.save(slide);
    }

    @Transactional
    public HeroSlide update(Long id, HeroSlide updated) {
        HeroSlide slide = getById(id);
        if (updated.getImageUrl() != null) slide.setImageUrl(updated.getImageUrl());
        if (updated.getTitle() != null) slide.setTitle(updated.getTitle());
        if (updated.getSubtitle() != null) slide.setSubtitle(updated.getSubtitle());
        if (updated.getDescription() != null) slide.setDescription(updated.getDescription());
        if (updated.getSortOrder() != null) slide.setSortOrder(updated.getSortOrder());
        if (updated.getEnabled() != null) slide.setEnabled(updated.getEnabled());
        return repo.save(slide);
    }

    @Transactional
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
