package com.luyingqinhua.service;

import com.luyingqinhua.entity.FooterIcon;
import com.luyingqinhua.repository.FooterIconRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FooterIconService {

    private final FooterIconRepository repo;

    public FooterIconService(FooterIconRepository repo) {
        this.repo = repo;
    }

    public List<FooterIcon> getAll() {
        return repo.findAllByOrderBySortOrderAsc();
    }

    public FooterIcon getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("页脚图标不存在: " + id));
    }

    @Transactional
    public FooterIcon create(FooterIcon icon) {
        return repo.save(icon);
    }

    @Transactional
    public FooterIcon update(Long id, FooterIcon updated) {
        FooterIcon icon = getById(id);
        if (updated.getImageUrl() != null) icon.setImageUrl(updated.getImageUrl());
        if (updated.getName() != null) icon.setName(updated.getName());
        if (updated.getLink() != null) icon.setLink(updated.getLink());
        if (updated.getSortOrder() != null) icon.setSortOrder(updated.getSortOrder());
        if (updated.getEnabled() != null) icon.setEnabled(updated.getEnabled());
        return repo.save(icon);
    }
}
