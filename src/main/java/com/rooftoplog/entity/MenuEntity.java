package com.rooftoplog.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="MENU")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MENU_ID")
    private Long menuId;

    @Column(name="PARENT_ID")
    private Integer parentId;

    @Column(name="NAME")
    private String name;

    @Column(name="URL")
    private String url;

    @Column(name = "IS_ADMIN", columnDefinition = "CHAR(1) DEFAULT 'N'")
    private char isAdmin;

    @Column(name="ORDER_NO", columnDefinition = "INTEGER DEFAULT 0")
    private Integer orderNo;

    @Column(name="IS_ACTIVE", columnDefinition = "BOOLEAN DEFAULT 1")
    private Boolean isActive;

    @Column(name="IS_DELETED", columnDefinition = "BOOLEAN DEFAULT 0")
    private Boolean isDeleted;

    @Column(name = "CREATED_AT", columnDefinition = "TEXT", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", columnDefinition = "TEXT")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
