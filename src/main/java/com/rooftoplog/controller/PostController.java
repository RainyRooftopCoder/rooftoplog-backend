package com.rooftoplog.controller;

import com.rooftoplog.dto.PostDto;
import com.rooftoplog.entity.PostEntity;
import com.rooftoplog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @GetMapping("/list")
    public List<PostEntity> getPosts(@RequestParam String boardId) {
        return postService.getPosts(boardId);
    }

    @GetMapping()
    public PostEntity getPost(@RequestBody PostDto postDto) {
        return postService.getPost(postDto);
    }

    @PostMapping
    public void createPost(@RequestBody PostDto postDto) {
        postService.createPost(postDto);
    }
}
