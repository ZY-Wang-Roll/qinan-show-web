package com.luyingqinhua.controller;

import com.luyingqinhua.common.Result;
import com.luyingqinhua.entity.SiteConfig;
import com.luyingqinhua.service.SiteService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/config")
public class SiteConfigController {

    private final SiteService siteService;

    public SiteConfigController(SiteService siteService) {
        this.siteService = siteService;
    }

    @GetMapping
    public Result<SiteConfig> get() {
        return Result.ok(siteService.getConfig());
    }

    @PutMapping
    public Result<SiteConfig> update(@RequestBody SiteConfig config) {
        return Result.ok(siteService.updateConfig(config));
    }
}
