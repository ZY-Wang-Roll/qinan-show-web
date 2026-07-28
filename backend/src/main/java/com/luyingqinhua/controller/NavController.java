package com.luyingqinhua.controller;

import com.luyingqinhua.common.Result;
import com.luyingqinhua.entity.NavItem;
import com.luyingqinhua.service.NavService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nav")
public class NavController {

    private final NavService navService;

    public NavController(NavService navService) {
        this.navService = navService;
    }

    @GetMapping("/items")
    public Result<List<NavItem>> getAll() {
        return Result.ok(navService.getAll());
    }

    @PutMapping("/items/{id}")
    public Result<NavItem> update(@PathVariable Long id, @RequestBody NavItem item) {
        return Result.ok(navService.update(id, item));
    }
}
