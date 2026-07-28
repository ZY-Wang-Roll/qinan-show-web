package com.luyingqinhua.controller;

import com.luyingqinhua.common.Result;
import com.luyingqinhua.entity.FooterIcon;
import com.luyingqinhua.service.FooterIconService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FooterIconController {

    private final FooterIconService footerIconService;

    public FooterIconController(FooterIconService footerIconService) {
        this.footerIconService = footerIconService;
    }

    @GetMapping("/footer-icons")
    public Result<List<FooterIcon>> getAll() {
        return Result.ok(footerIconService.getAll());
    }

    @PostMapping("/footer-icons")
    public Result<FooterIcon> create(@RequestBody FooterIcon icon) {
        return Result.ok(footerIconService.create(icon));
    }

    @PutMapping("/footer-icons/{id}")
    public Result<FooterIcon> update(@PathVariable Long id, @RequestBody FooterIcon icon) {
        return Result.ok(footerIconService.update(id, icon));
    }
}
