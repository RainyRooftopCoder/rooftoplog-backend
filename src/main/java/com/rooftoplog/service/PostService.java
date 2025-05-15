package com.rooftoplog.service;

import com.rooftoplog.dto.PostDto;
import com.rooftoplog.entity.PostEntity;

import java.util.List;

public interface PostService {

    public List<PostEntity> getPosts(String boardId);

    public PostEntity getPost(PostDto postDto);

    public void createPost(PostDto postDto);
}
