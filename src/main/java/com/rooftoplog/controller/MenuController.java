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

    @GetMapping
    public List<MenuEntity> getMenus() {
        // 나중에 관리자 여부 확인해서 분기 처리
        List<MenuEntity> menus = menuService.getGuestMenus();
        log.debug("MENU IN");
        return menus;
    }
}
