package com.example.FitTogether.dao;

import java.util.List;


import com.example.FitTogether.dto.PostDTO;

public interface PostDAO {
    PostDTO getPostById(int id);
    List<PostDTO> getPostsByUserId(int userId);
    List<PostDTO> getAllPost();
    void insertPost(PostDTO post);
    void updatePost(PostDTO post);
    void deletePost(int id);
}
