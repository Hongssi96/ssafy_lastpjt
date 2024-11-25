package com.example.FitTogether.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FitTogether.dto.PostDTO;
import com.example.FitTogether.service.PostService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private PostService postService;
	
	@GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable int id) {
        PostDTO post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }
	@GetMapping
	public ResponseEntity<List<PostDTO>> getAllPost(){
		List<PostDTO> posts = postService.getAllPost();
		return ResponseEntity.ok(posts);
	}

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDTO>> getPostsByUserId(@PathVariable int userId) {
        List<PostDTO> posts = postService.getPostsByUserId(userId);
        return ResponseEntity.ok(posts);
    }

    @PostMapping
    public ResponseEntity<?> insertPost(@RequestBody PostDTO post) {
        postService.insertPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable int id, @RequestBody PostDTO post) {
        post.setId(id);
        postService.updatePost(post);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable int id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
	
}
