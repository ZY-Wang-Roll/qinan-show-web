package com.luyingqinhua.service;

import com.luyingqinhua.entity.NavItem;
import com.luyingqinhua.repository.NavItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NavService {

    private final NavItemRepository navRepo;

    public NavService(NavItemRepository navRepo) {
        this.navRepo = navRepo;
    }

    public List<NavItem> getAll() {
        return navRepo.findAllByOrderBySortOrderAsc();
    }

    public NavItem update(Long id, NavItem updated) {
        NavItem item = navRepo.findById(id).orElseThrow(() -> new RuntimeException("导航项不存在: " + id));
        if (updated.getLabel() != null) item.setLabel(updated.getLabel());
        if (updated.getNavId() != null) item.setNavId(updated.getNavId());
        if (updated.getHref() != null) item.setHref(updated.getHref());
        return navRepo.save(item);
    }
}
