package com.rooftoplog.service;

import com.rooftoplog.dto.GuestbookDto;
import com.rooftoplog.entity.GuestbookEntity;

import java.util.List;

public interface GuestbookService {

    List<GuestbookEntity> findAllGuestbooks();

    GuestbookEntity addGuestbook(GuestbookDto guestbookDto);

    GuestbookEntity checkPswd(GuestbookDto guestbookDto);

    GuestbookEntity updateGuestbook(GuestbookDto guestbookDto);

}
