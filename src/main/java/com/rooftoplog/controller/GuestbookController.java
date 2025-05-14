package com.rooftoplog.controller;

import com.rooftoplog.dto.GuestbookDto;
import com.rooftoplog.entity.GuestbookEntity;
import com.rooftoplog.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/guestbook")
public class GuestbookController {

    private final GuestbookService guestbookService;

    @GetMapping
    public ResponseEntity<List<GuestbookEntity>> getAllGuestbooks() {
        List<GuestbookEntity> Guestbooks = guestbookService.findAllGuestbooks();
        return ResponseEntity.ok(Guestbooks);
    }

    @PostMapping
    public ResponseEntity<?> createGuestbook(@RequestBody GuestbookDto guestbookDto) {
        try {
            GuestbookEntity guestbook = guestbookService.addGuestbook(guestbookDto);

            return ResponseEntity.ok("저장되었습니다.");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/checkPswd")
    public ResponseEntity<?> checkPswd(@RequestBody GuestbookDto guestbookDto) {
        try {
            GuestbookEntity guestbook = guestbookService.checkPswd(guestbookDto);

            return ResponseEntity.ok("비밀번호 확인");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateGuestbook(@RequestBody GuestbookDto guestbookDto) {
        try {
            GuestbookEntity guestbook = guestbookService.updateGuestbook(guestbookDto);

            String division = guestbookDto.getExecutionDivision();
            String divNm = "수정";
            if("delete".equals(division)) {
                divNm = "삭제";
            }
            return ResponseEntity.ok(divNm + "되었습니다.");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

}
