package com.rooftoplog.service.impl;

import com.rooftoplog.dto.GuestbookDto;
import com.rooftoplog.entity.GuestbookEntity;
import com.rooftoplog.repository.GuestbookRepository;
import com.rooftoplog.service.GuestbookService;
import com.rooftoplog.util.EncryptUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class GuestbookServiceImpl implements GuestbookService {

    private final GuestbookRepository guestbookRepository;

    private final EncryptUtil encryptUtil;

    @Override
    public List<GuestbookEntity> findAllGuestbooks() {
        return guestbookRepository.findAllByIsDeletedFalse();
    }

    @Override
    public GuestbookEntity addGuestbook(GuestbookDto guestbookDto) {
        GuestbookEntity guestbook = new GuestbookEntity();

        guestbook.setContent(guestbookDto.getContent());
        guestbook.setAuthor(guestbookDto.getAuthor());
        guestbook.setPassword(guestbookDto.getPassword());
        guestbook.setIsDeleted(guestbookDto.getIsDeleted());
        guestbook.setCreatedAt(LocalDateTime.now());
        guestbook.setUpdatedAt(LocalDateTime.now());

        return saveGuestbook(guestbook);
    }

    private GuestbookEntity saveGuestbook(GuestbookEntity guestbook) {

        String encryptPw = encryptUtil.encryptPassword(guestbook.getPassword());
        guestbook.setPassword(encryptPw);

        if(guestbook.getIsDeleted() == null) {
            guestbook.setIsDeleted(false);
        }

        return guestbookRepository.save(guestbook);
    }
}
