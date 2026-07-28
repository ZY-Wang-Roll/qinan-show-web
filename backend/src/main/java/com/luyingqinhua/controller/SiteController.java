package com.luyingqinhua.controller;

import com.luyingqinhua.common.Result;
import com.luyingqinhua.service.SiteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/site")
public class SiteController {

    private final SiteService siteService;

    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @GetMapping("/full")
    public Result<Map<String, Object>> getFullSiteData() {
        return Result.ok(siteService.getFullSiteData());
    }
}
