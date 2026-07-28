package com.luyingqinhua.controller;

import com.luyingqinhua.common.Result;
import com.luyingqinhua.entity.CultureItem;
import com.luyingqinhua.service.CultureService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/culture")
public class CultureController {

    private final CultureService cultureService;

    public CultureController(CultureService cultureService) {
        this.cultureService = cultureService;
    }

    @GetMapping("/items")
    public Result<List<CultureItem>> getAll() {
        return Result.ok(cultureService.getAll());
    }

    @GetMapping("/items/{id}")
    public Result<CultureItem> getById(@PathVariable Long id) {
        return Result.ok(cultureService.getById(id));
    }

    @PutMapping("/items/{id}")
    public Result<CultureItem> update(@PathVariable Long id, @RequestBody CultureItem item) {
        return Result.ok(cultureService.update(id, item));
    }
}
