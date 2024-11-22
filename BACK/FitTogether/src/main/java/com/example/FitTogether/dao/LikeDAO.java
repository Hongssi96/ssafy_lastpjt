package com.example.FitTogether.dao;

import java.util.List;


import com.example.FitTogether.dto.LikeDTO;

public interface LikeDAO {
    LikeDTO getLikeById(int id);
    List<LikeDTO> getLikesByPostId(int postId);
    void insertLike(LikeDTO like);
    void deleteLike(int id);
}