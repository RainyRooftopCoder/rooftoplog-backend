package com.rooftoplog.controller;

import com.rooftoplog.dto.GuestbookDto;
import com.rooftoplog.entity.GuestbookEntity;
import com.rooftoplog.service.GuestbookService;
import lombok.RequiredArgsConstructor;
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
    public GuestbookEntity createGuestbook(@RequestBody GuestbookDto guestbookDto) {
        return guestbookService.addGuestbook(guestbookDto);
    }
}
