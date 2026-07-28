package com.luyingqinhua.service;

import com.luyingqinhua.entity.CultureItem;
import com.luyingqinhua.entity.CultureTag;
import com.luyingqinhua.repository.CultureItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CultureService {

    private final CultureItemRepository itemRepo;

    public CultureService(CultureItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }

    public List<CultureItem> getAll() {
        return itemRepo.findAllByOrderBySortOrderAsc();
    }

    public CultureItem getById(Long id) {
        return itemRepo.findById(id).orElseThrow(() -> new RuntimeException("文化项不存在: " + id));
    }

    @Transactional
    public CultureItem update(Long id, CultureItem updated) {
        CultureItem item = getById(id);
        if (updated.getSubtitleTag() != null) item.setSubtitleTag(updated.getSubtitleTag());
        if (updated.getTitle() != null) item.setTitle(updated.getTitle());
        if (updated.getDescription() != null) item.setDescription(updated.getDescription());
        if (updated.getImageUrl() != null) item.setImageUrl(updated.getImageUrl());
        if (updated.getImagePlaceholder() != null) item.setImagePlaceholder(updated.getImagePlaceholder());

        // 更新标签
        if (updated.getTags() != null && !updated.getTags().isEmpty()) {
            item.getTags().clear();
            for (CultureTag tag : updated.getTags()) {
                tag.setCultureItem(item);
                item.getTags().add(tag);
            }
        }

        return itemRepo.save(item);
    }
}
