package com.luyingqinhua.controller;

import com.luyingqinhua.common.Result;
import com.luyingqinhua.entity.DocumentaryItem;
import com.luyingqinhua.entity.Story;
import com.luyingqinhua.service.DocumentaryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DocumentaryController {

    private final DocumentaryService docService;

    public DocumentaryController(DocumentaryService docService) {
        this.docService = docService;
    }

    // ====== Documentary Items ======
    @GetMapping("/documentary/items")
    public Result<List<DocumentaryItem>> getAllItems() {
        return Result.ok(docService.getAllItems());
    }

    @PutMapping("/documentary/items/{id}")
    public Result<DocumentaryItem> updateItem(@PathVariable Long id, @RequestBody DocumentaryItem item) {
        return Result.ok(docService.updateItem(id, item));
    }

    // ====== Stories ======
    @GetMapping("/stories")
    public Result<List<Story>> getAllStories() {
        return Result.ok(docService.getAllStories());
    }

    @PutMapping("/stories/{id}")
    public Result<Story> updateStory(@PathVariable Long id, @RequestBody Story story) {
        return Result.ok(docService.updateStory(id, story));
    }
}
