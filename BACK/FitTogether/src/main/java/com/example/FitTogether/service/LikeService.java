package com.example.FitTogether.service;

import java.util.List;

import com.example.FitTogether.dto.LikeDTO;

public interface LikeService {
    LikeDTO getLikeById(int id);
    List<LikeDTO> getLikesByPostId(int postId);
    void insertLike(LikeDTO like);
    void deleteLike(int id);
}