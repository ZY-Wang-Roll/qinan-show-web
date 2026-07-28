package com.luyingqinhua.controller;

import com.luyingqinhua.common.Result;
import com.luyingqinhua.entity.HeroSlide;
import com.luyingqinhua.service.HeroSlideService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HeroSlideController {

    private final HeroSlideService heroSlideService;

    public HeroSlideController(HeroSlideService heroSlideService) {
        this.heroSlideService = heroSlideService;
    }

    @GetMapping("/hero-slides")
    public Result<List<HeroSlide>> getAll() {
        return Result.ok(heroSlideService.getAll());
    }

    @PostMapping("/hero-slides")
    public Result<HeroSlide> create(@RequestBody HeroSlide slide) {
        return Result.ok(heroSlideService.create(slide));
    }

    @PutMapping("/hero-slides/{id}")
    public Result<HeroSlide> update(@PathVariable Long id, @RequestBody HeroSlide slide) {
        return Result.ok(heroSlideService.update(id, slide));
    }

    @DeleteMapping("/hero-slides/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        heroSlideService.delete(id);
        return Result.ok();
    }
}
