package com.example.FitTogether.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FitTogether.dto.FollowDTO;
import com.example.FitTogether.service.FollowService;

@RestController
@RequestMapping("/api/follows")
public class FollowController {
    
	@Autowired
	private FollowService followService;

    @GetMapping("/{id}")
    public ResponseEntity<FollowDTO> getFollowById(@PathVariable int id) {
        FollowDTO follow = followService.getFollowById(id);
        return ResponseEntity.ok(follow);
    }

    @GetMapping("/followers/{userId}")
    public ResponseEntity<List<FollowDTO>> getFollowersByUserId(@PathVariable int userId) {
        List<FollowDTO> followers = followService.getFollowersByUserId(userId);
        return ResponseEntity.ok(followers);
    }

    @GetMapping("/following/{userId}")
    public ResponseEntity<List<FollowDTO>> getFollowingByUserId(@PathVariable int userId) {
        List<FollowDTO> following = followService.getFollowingByUserId(userId);
        return ResponseEntity.ok(following);
    }

    @PostMapping
    public ResponseEntity<Void> insertFollow(@RequestBody FollowDTO follow) {
        followService.insertFollow(follow);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFollow(@PathVariable int id) {
        followService.deleteFollow(id);
        return ResponseEntity.noContent().build();
    }
}
