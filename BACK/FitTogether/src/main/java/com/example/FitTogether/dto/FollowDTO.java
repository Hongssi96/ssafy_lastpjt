package com.example.FitTogether.dto;

import java.time.LocalDateTime;

public class FollowDTO {
    private int id;
    private int followerId;
    private int followingId;
    private LocalDateTime createdAt;

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getFollowerId() {
        return followerId;
    }
    public void setFollowerId(int followerId) {
        this.followerId = followerId;
    }
    public int getFollowingId() {
        return followingId;
    }
    public void setFollowingId(int followingId) {
        this.followingId = followingId;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}