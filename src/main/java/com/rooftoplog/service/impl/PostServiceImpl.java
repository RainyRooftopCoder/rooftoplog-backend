package com.rooftoplog.service.impl;

import com.rooftoplog.dto.PostDto;
import com.rooftoplog.entity.PostEntity;
import com.rooftoplog.repository.PostRepository;
import com.rooftoplog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public List<PostEntity> getPosts(String boardId) {
//        String boardId = extractionBoardId(postDto.getBoardId());
        log.debug("boardId :: {}", boardId);

        return postRepository.findAllByIsDeletedFalseAndBoardId(boardId);
    }

    @Override
    public PostEntity getPost(PostDto postDto) {
        log.debug("postDto :: {}", postDto.getPostId());

        return postRepository.findByPostId(postDto.getPostId());
    }

    @Override
    public void createPost(PostDto postDto) {
        PostEntity post = new PostEntity();

        String boardId = extractionBoardId(postDto.getBoardId());
        post.setBoardId(boardId);
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setAuthor(postDto.getAuthor());
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());

        postRepository.save(post);
    }

    private String extractionBoardId(String boardUrl) {
        log.debug("boardUrl :: {}", boardUrl);

        return boardUrl.split("/")[2];
    }
}
