package com.luyingqinhua.controller;

import com.luyingqinhua.common.Result;
import com.luyingqinhua.entity.TeamAchievement;
import com.luyingqinhua.entity.TeamInfo;
import com.luyingqinhua.entity.TeamStat;
import com.luyingqinhua.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    // ====== TeamInfo ======
    @GetMapping("/info")
    public Result<TeamInfo> getInfo() {
        return Result.ok(teamService.getInfo());
    }

    @PutMapping("/info")
    public Result<TeamInfo> updateInfo(@RequestBody TeamInfo info) {
        return Result.ok(teamService.updateInfo(info));
    }

    // ====== TeamStat ======
    @GetMapping("/stats")
    public Result<List<TeamStat>> getStats() {
        return Result.ok(teamService.getStats());
    }

    @PutMapping("/stats/{id}")
    public Result<TeamStat> updateStat(@PathVariable Long id, @RequestBody TeamStat stat) {
        return Result.ok(teamService.updateStat(id, stat));
    }

    // ====== TeamAchievement ======
    @GetMapping("/achievements")
    public Result<List<TeamAchievement>> getAchievements() {
        return Result.ok(teamService.getAchievements());
    }

    @PutMapping("/achievements/{id}")
    public Result<TeamAchievement> updateAchievement(@PathVariable Long id, @RequestBody TeamAchievement achievement) {
        return Result.ok(teamService.updateAchievement(id, achievement));
    }
}
