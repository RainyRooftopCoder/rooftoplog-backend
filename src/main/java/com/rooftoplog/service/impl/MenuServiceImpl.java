package com.rooftoplog.service.impl;

import com.rooftoplog.entity.MenuEntity;
import com.rooftoplog.repository.MenuRepository;
import com.rooftoplog.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    @Override
    public List<MenuEntity> getGuestMenus() {
        return menuRepository.findAllByIsDeletedFalseAndIsActiveTrueAndIsAdminOrderByOrderNoAsc('N');
    }

    @Override
    public List<MenuEntity> getAdminMenus() {
        return menuRepository.findAllByIsDeletedFalseOrderByOrderNoAsc();
    }
}
