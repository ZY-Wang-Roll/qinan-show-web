package com.luyingqinhua.service;

import com.luyingqinhua.entity.DocumentaryItem;
import com.luyingqinhua.entity.Story;
import com.luyingqinhua.repository.DocumentaryItemRepository;
import com.luyingqinhua.repository.StoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocumentaryService {

    private final DocumentaryItemRepository docRepo;
    private final StoryRepository storyRepo;

    public DocumentaryService(DocumentaryItemRepository docRepo, StoryRepository storyRepo) {
        this.docRepo = docRepo;
        this.storyRepo = storyRepo;
    }

    // ====== Documentary Items ======
    public List<DocumentaryItem> getAllItems() {
        return docRepo.findAllByOrderBySortOrderAsc();
    }

    public DocumentaryItem getItemById(Long id) {
        return docRepo.findById(id).orElseThrow(() -> new RuntimeException("纪实项不存在: " + id));
    }

    @Transactional
    public DocumentaryItem updateItem(Long id, DocumentaryItem updated) {
        DocumentaryItem item = getItemById(id);
        if (updated.getLabel() != null) item.setLabel(updated.getLabel());
        if (updated.getColsSpan() != null) item.setColsSpan(updated.getColsSpan());
        if (updated.getRowsSpan() != null) item.setRowsSpan(updated.getRowsSpan());
        if (updated.getTimeLabel() != null) item.setTimeLabel(updated.getTimeLabel());
        if (updated.getNote() != null) item.setNote(updated.getNote());
        if (updated.getImageUrl() != null) item.setImageUrl(updated.getImageUrl());
        if (updated.getBgGradient() != null) item.setBgGradient(updated.getBgGradient());
        if (updated.getIcon() != null) item.setIcon(updated.getIcon());
        return docRepo.save(item);
    }

    // ====== Stories ======
    public List<Story> getAllStories() {
        return storyRepo.findAllByOrderBySortOrderAsc();
    }

    public Story getStoryById(Long id) {
        return storyRepo.findById(id).orElseThrow(() -> new RuntimeException("故事不存在: " + id));
    }

    @Transactional
    public Story updateStory(Long id, Story updated) {
        Story story = getStoryById(id);
        if (updated.getTitle() != null) story.setTitle(updated.getTitle());
        if (updated.getDescription() != null) story.setDescription(updated.getDescription());
        return storyRepo.save(story);
    }
}
