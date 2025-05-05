package com.rooftoplog.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuestbookDto {

    private Long guestbookId;
    private String author;
    private String password; // insert/update 시에만 사용
    private String content;
    private Boolean isDeleted;
}
