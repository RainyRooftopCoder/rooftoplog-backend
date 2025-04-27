package com.rooftoplog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuDto {
    private Long menuId;
    private String name;
    private Long parentId;
    private String url;
    private char isAdmin;
    private String isActive;
    private String isDeleted;
}
