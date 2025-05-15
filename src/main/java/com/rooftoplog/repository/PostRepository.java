package com.rooftoplog.repository;

import com.rooftoplog.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    List<PostEntity> findAllByIsDeletedFalseAndBoardId(String boardId);

    PostEntity findByPostId(Long postId);
}
