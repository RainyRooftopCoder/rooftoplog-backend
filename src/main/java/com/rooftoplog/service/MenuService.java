package com.rooftoplog.service;

import com.rooftoplog.entity.MenuEntity;
import java.util.List;

public interface MenuService {

    List<MenuEntity> getGuestMenus();

    List<MenuEntity> getAdminMenus();
}
