package com.rooftoplog.repository;

import com.rooftoplog.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
    
    // 관리자용
    List<MenuEntity> findAllByIsDeletedFalseOrderByOrderNoAsc();

    // 게스트용
    List<MenuEntity> findAllByIsDeletedFalseAndIsActiveTrueAndIsAdminOrderByOrderNoAsc(char isAdmin);

    /* @Query 로 써도 되는데 네이밍 규칙만 맞으면 조건을 메소드명으로 작성해서 사용가능 대박!!
    @Query("SELECT m FROM MenuEntity m WHERE m.isDeleted = false ORDER BY m.sortOrder ASC")
    List<MenuEntity> findVisibleMenus();

    @Query("SELECT m FROM MenuEntity m WHERE m.isDeleted = false AND m.isVisible = true ORDER BY m.sortOrder ASC")
    List<MenuEntity> findGuestMenus();
    */

    int countByParentIdNull();

    int countByParentId(long parentId);
}
