package com.rooftoplog.service.impl;

import com.rooftoplog.dto.MenuDto;
import com.rooftoplog.entity.MenuEntity;
import com.rooftoplog.repository.MenuRepository;
import com.rooftoplog.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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

    @Override
    public MenuEntity createMenu(MenuDto menuDto) {
        MenuEntity menuEntity = commonMenuSet(menuDto);

        return menuRepository.save(menuEntity);
    }

    @Override
    public void deleteMenu(MenuDto menuDto) {
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setMenuId(menuDto.getMenuId());

        menuRepository.delete(menuEntity);
    }

    private MenuEntity commonMenuSet(MenuDto menuDto) {
        MenuEntity menuEntity = new MenuEntity();

        int sortNo = 0;
        if(menuDto.getParentId() == null) {
            sortNo = menuRepository.countByParentIdNull();
        } else {
            sortNo = menuRepository.countByParentId(menuDto.getParentId());
        }

        if(menuDto.getMenuId() != null) {
            menuEntity.setMenuId(menuDto.getMenuId());
        }

        menuEntity.setName(menuDto.getName());
        menuEntity.setIsAdmin(menuDto.getIsAdmin());
        menuEntity.setOrderNo(sortNo);

        if("Y".equals(menuDto.getIsActive())) {
            menuEntity.setIsActive(false);
        } else {
            menuEntity.setIsActive(true);
        }

        if("Y".equals(menuDto.getIsDeleted())) {
            menuEntity.setIsDeleted(true);
        } else {
            menuEntity.setIsDeleted(false);
        }

        return menuEntity;
    }
}
