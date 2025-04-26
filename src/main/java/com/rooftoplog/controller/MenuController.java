package com.rooftoplog.controller;

import com.rooftoplog.entity.MenuEntity;
import com.rooftoplog.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menu")
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/guest")
    public List<MenuEntity> getGuestMenus() {
        List<MenuEntity> menus = menuService.getGuestMenus();
        log.debug("GET GUEST MENU!!");
        return menus;
    }

    @GetMapping("/admin")
    public List<MenuEntity> getAdminMenus() {
        List<MenuEntity> menus = menuService.getAdminMenus();
        log.debug("GET ADMIN MENU!!");
        return menus;
    }
}
