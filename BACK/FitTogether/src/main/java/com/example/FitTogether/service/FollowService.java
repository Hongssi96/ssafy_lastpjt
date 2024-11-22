package com.example.FitTogether.service;

import java.util.List;

import com.example.FitTogether.dto.FollowDTO;

public interface FollowService {
    FollowDTO getFollowById(int id);
    List<FollowDTO> getFollowersByUserId(int userId);
    List<FollowDTO> getFollowingByUserId(int userId);
    void insertFollow(FollowDTO follow);
    void deleteFollow(int id);
}
