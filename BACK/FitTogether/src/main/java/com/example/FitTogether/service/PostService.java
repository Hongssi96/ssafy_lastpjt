package com.example.FitTogether.service;

import java.util.List;

import com.example.FitTogether.dto.PostDTO;

public interface PostService {
    PostDTO getPostById(int id);
    List<PostDTO> getPostsByUserId(int userId);
    void insertPost(PostDTO post);
    void updatePost(PostDTO post);
    void deletePost(int id);
}
