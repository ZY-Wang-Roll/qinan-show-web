package com.luyingqinhua.controller;

import com.luyingqinhua.common.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 一次性修复：去掉数据库中所有图片 URL 的 http://localhost:8080 前缀，
 * 改为相对路径 /uploads/...，解决 cpolar 穿透后手机端图片无法加载的问题。
 */
@RestController
@RequestMapping("/api/admin")
public class AdminFixController {

    @PersistenceContext
    private EntityManager em;

    @PostMapping("/fix-image-urls")
    @Transactional
    public Result<Map<String, Integer>> fixImageUrls() {
        Map<String, Integer> result = new LinkedHashMap<>();
        String oldPrefix = "http://localhost:8080";

        // uploaded_image
        int n1 = em.createQuery("UPDATE UploadedImage SET url = REPLACE(url, :old, '') WHERE url LIKE :pattern")
                .setParameter("old", oldPrefix)
                .setParameter("pattern", oldPrefix + "%")
                .executeUpdate();
        result.put("uploaded_image", n1);

        // team_info (多个图片字段)
        int n2 = em.createNativeQuery(
            "UPDATE team_info SET image_url = REPLACE(COALESCE(image_url,''), :old, ''), " +
            "hero_image_url = REPLACE(COALESCE(hero_image_url,''), :old, ''), " +
            "team_logo_url = REPLACE(COALESCE(team_logo_url,''), :old, ''), " +
            "photo1_url = REPLACE(COALESCE(photo1_url,''), :old, ''), " +
            "photo2_url = REPLACE(COALESCE(photo2_url,''), :old, ''), " +
            "photo3_url = REPLACE(COALESCE(photo3_url,''), :old, ''), " +
            "photo4_url = REPLACE(COALESCE(photo4_url,''), :old, '') " +
            "WHERE image_url LIKE :pattern OR hero_image_url LIKE :pattern OR team_logo_url LIKE :pattern " +
            "OR photo1_url LIKE :pattern OR photo2_url LIKE :pattern OR photo3_url LIKE :pattern OR photo4_url LIKE :pattern")
                .setParameter("old", oldPrefix)
                .setParameter("pattern", oldPrefix + "%")
                .executeUpdate();
        result.put("team_info", n2);

        // culture_item
        int n3 = em.createQuery("UPDATE CultureItem SET imageUrl = REPLACE(imageUrl, :old, '') WHERE imageUrl LIKE :pattern")
                .setParameter("old", oldPrefix).setParameter("pattern", oldPrefix + "%").executeUpdate();
        result.put("culture_item", n3);

        // product
        int n4 = em.createQuery("UPDATE Product SET imageUrl = REPLACE(imageUrl, :old, '') WHERE imageUrl LIKE :pattern")
                .setParameter("old", oldPrefix).setParameter("pattern", oldPrefix + "%").executeUpdate();
        result.put("product", n4);

        // documentary_item
        int n5 = em.createQuery("UPDATE DocumentaryItem SET imageUrl = REPLACE(imageUrl, :old, '') WHERE imageUrl LIKE :pattern")
                .setParameter("old", oldPrefix).setParameter("pattern", oldPrefix + "%").executeUpdate();
        result.put("documentary_item", n5);

        int total = result.values().stream().mapToInt(Integer::intValue).sum();
        result.put("total_fixed", total);
        return Result.ok(result);
    }
}
