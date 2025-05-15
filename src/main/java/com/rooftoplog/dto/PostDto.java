package com.rooftoplog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {

    private Long postId;
    private String boardId;
    private String title;
    private String content;
    private String author;
    private boolean isNotice;
    private boolean isDeleted;
    private Long fileGroupId;
}
