package com.rooftoplog.repository;

import com.rooftoplog.entity.GuestbookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestbookRepository extends JpaRepository<GuestbookEntity, Long> {

    List<GuestbookEntity> findAllByIsDeletedFalse();

    List<GuestbookEntity> findAllByIsDeletedFalseAndGuestbookId(Long guestbookId);

    Integer countAllByIsDeletedFalse();

    Integer countAllByIsDeletedFalseAndGuestbookId(Long guestbookId);
}
