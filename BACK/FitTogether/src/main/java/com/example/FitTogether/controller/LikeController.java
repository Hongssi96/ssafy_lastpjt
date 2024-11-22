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

import com.example.FitTogether.dto.LikeDTO;
import com.example.FitTogether.service.LikeService;

@RestController
@RequestMapping("/api/likes")
public class LikeController {
    
	@Autowired
	private LikeService likeService;

    @GetMapping("/{id}")
    public ResponseEntity<LikeDTO> getLikeById(@PathVariable int id) {
        LikeDTO like = likeService.getLikeById(id);
        return ResponseEntity.ok(like);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<LikeDTO>> getLikesByPostId(@PathVariable int postId) {
        List<LikeDTO> likes = likeService.getLikesByPostId(postId);
        return ResponseEntity.ok(likes);
    }

    @PostMapping
    public ResponseEntity<Void> insertLike(@RequestBody LikeDTO like) {
        likeService.insertLike(like);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLike(@PathVariable int id) {
        likeService.deleteLike(id);
        return ResponseEntity.noContent().build();
    }
}