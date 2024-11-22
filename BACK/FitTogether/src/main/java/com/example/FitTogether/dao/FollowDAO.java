package com.example.FitTogether.dao;

import java.util.List;


import com.example.FitTogether.dto.FollowDTO;

public interface FollowDAO {
    FollowDTO getFollowById(int id);
    List<FollowDTO> getFollowersByUserId(int userId);
    List<FollowDTO> getFollowingByUserId(int userId);
    void insertFollow(FollowDTO follow);
    void deleteFollow(int id);
}