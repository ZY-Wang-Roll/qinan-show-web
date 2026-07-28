package com.luyingqinhua.service;

import com.luyingqinhua.entity.SiteConfig;
import com.luyingqinhua.repository.SiteConfigRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class SiteService {

    private final TeamService teamService;
    private final CultureService cultureService;
    private final ProductService productService;
    private final DocumentaryService documentaryService;
    private final NavService navService;
    private final SiteConfigRepository configRepo;

    public SiteService(TeamService teamService, CultureService cultureService,
                       ProductService productService, DocumentaryService documentaryService,
                       NavService navService, SiteConfigRepository configRepo) {
        this.teamService = teamService;
        this.cultureService = cultureService;
        this.productService = productService;
        this.documentaryService = documentaryService;
        this.navService = navService;
        this.configRepo = configRepo;
    }

    public Map<String, Object> getFullSiteData() {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("navItems", navService.getAll());
        data.put("teamInfo", teamService.getInfo());
        data.put("teamStats", teamService.getStats());
        data.put("teamAchievements", teamService.getAchievements());
        data.put("cultureItems", cultureService.getAll());
        data.put("products", productService.getAll());
        data.put("documentaryItems", documentaryService.getAllItems());
        data.put("stories", documentaryService.getAllStories());

        // 全站配置（closing 图片等）
        SiteConfig cfg = getConfig();
        data.put("closingImage", cfg.getClosingImage());
        data.put("closingBrand", cfg.getClosingBrand());
        data.put("closingSubtitle", cfg.getClosingSubtitle());
        return data;
    }

    public SiteConfig getConfig() {
        return configRepo.findAll().stream().findFirst().orElseGet(() -> {
            SiteConfig c = new SiteConfig();
            c.setClosingBrand("鹿映秦华");
            c.setClosingSubtitle("以青年视角重新发现乡土价值");
            return configRepo.save(c);
        });
    }

    public SiteConfig updateConfig(SiteConfig updated) {
        SiteConfig cfg = getConfig();
        if (updated.getClosingImage() != null) cfg.setClosingImage(updated.getClosingImage());
        if (updated.getClosingBrand() != null) cfg.setClosingBrand(updated.getClosingBrand());
        if (updated.getClosingSubtitle() != null) cfg.setClosingSubtitle(updated.getClosingSubtitle());
        return configRepo.save(cfg);
    }
}
