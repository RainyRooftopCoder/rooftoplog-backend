package com.rooftoplog.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="post")
@Getter
@Setter
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String boardId;
    private String title;
    private String content;
    private String author;
    private boolean isNotice;
    private boolean isDeleted;
    private Long fileGroupId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
