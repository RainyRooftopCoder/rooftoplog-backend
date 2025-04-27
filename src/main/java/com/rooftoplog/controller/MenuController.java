package com.rooftoplog.controller;

import com.rooftoplog.dto.MenuDto;
import com.rooftoplog.entity.MenuEntity;
import com.rooftoplog.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public MenuEntity createMenu(@RequestBody MenuDto menuDto) {
        return menuService.createMenu(menuDto);
    }

    @DeleteMapping()
    public void deleteMenu(@RequestBody MenuDto menuDto) {
        menuService.deleteMenu(menuDto);
    }
}
