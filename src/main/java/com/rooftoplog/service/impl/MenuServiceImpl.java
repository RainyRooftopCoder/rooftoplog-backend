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

        return saveMenu(menuEntity);
    }

    @Override
    public MenuEntity updateMenu(MenuDto menuDto) {
        MenuEntity menuEntity = commonMenuSet(menuDto);

        return saveMenu(menuEntity);
    }

    @Override
    public void deleteMenu(MenuDto menuDto) {
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setMenuId(menuDto.getMenuId());

        menuRepository.delete(menuEntity);
    }

    private MenuEntity saveMenu(MenuEntity menuEntity) {
        return menuRepository.save(menuEntity);
    }

    private MenuEntity commonMenuSet(MenuDto menuDto) {
        MenuEntity menuEntity = new MenuEntity();

        int sortNo = 0;
        if(menuDto.getParentId() == null) {
            sortNo = menuRepository.countByParentIdNull(); // 부모(카테고리)메뉴 카운트
        } else {
            long parentId = menuDto.getParentId();

            menuEntity.setParentId(parentId);
            sortNo = menuRepository.countByParentId(parentId); // 자식메뉴 카운트
        }

        if(menuDto.getMenuId() != null) {
            menuEntity.setMenuId(menuDto.getMenuId());
        } else {
            sortNo++;
        }

        menuEntity.setName(menuDto.getName());
        menuEntity.setUrl(menuDto.getUrl());
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
