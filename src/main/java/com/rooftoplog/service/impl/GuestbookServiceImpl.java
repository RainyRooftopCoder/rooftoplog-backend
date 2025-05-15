package com.rooftoplog.service.impl;

import com.rooftoplog.dto.GuestbookDto;
import com.rooftoplog.entity.GuestbookEntity;
import com.rooftoplog.repository.GuestbookRepository;
import com.rooftoplog.service.GuestbookService;
import com.rooftoplog.util.EncryptUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

        String executionDivision = guestbookDto.getExecutionDivision();

        if(!StringUtils.hasText(executionDivision)) {
            throw new RuntimeException("실행 구분이 부정확합니다.");
        }

        String encryptPw = encryptUtil.encryptPassword(guestbook.getPassword());

        guestbook.setContent(guestbookDto.getContent());
        guestbook.setAuthor(guestbookDto.getAuthor());
        guestbook.setPassword(encryptPw);
        guestbook.setIsDeleted(false);
        guestbook.setCreatedAt(LocalDateTime.now());
        guestbook.setUpdatedAt(LocalDateTime.now());

        return saveGuestbook(guestbook);
    }

    @Override
    public GuestbookEntity checkPswd(GuestbookDto guestbookDto) {
        GuestbookEntity guestbook = guestbookRepository.findByIsDeletedFalseAndGuestbookId(guestbookDto.getGuestbookId());

        if(encryptUtil.decryptPassword(guestbookDto.getPassword(), guestbook.getPassword())) {
            return guestbook;
        }else {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
    }

    @Override
    public GuestbookEntity updateGuestbook(GuestbookDto guestbookDto) {
        GuestbookEntity guestbook = new GuestbookEntity();

        if("delete".equals(guestbookDto.getExecutionDivision())) {
            guestbook = guestbookRepository.findByIsDeletedFalseAndGuestbookId(guestbookDto.getGuestbookId());
            guestbook.setIsDeleted(true);
            guestbook.setUpdatedAt(LocalDateTime.now());
        } else {
            guestbook.setContent(guestbookDto.getContent());
            guestbook.setAuthor(guestbookDto.getAuthor());
            guestbook.setPassword(guestbookDto.getPassword());
            guestbook.setIsDeleted(false);
            guestbook.setUpdatedAt(LocalDateTime.now());
        }

        return saveGuestbook(guestbook);
    }

    private GuestbookEntity saveGuestbook(GuestbookEntity guestbook) {
        return guestbookRepository.save(guestbook);
    }
}
