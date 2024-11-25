package com.example.FitTogether.controller;

import com.example.FitTogether.dto.UserDTO;
import com.example.FitTogether.jwt.JwtUtil;
import com.example.FitTogether.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id) {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<Void> insertUser(@RequestBody UserDTO user) {
        userService.insertUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable int id, @RequestBody UserDTO user) {
        user.setId(id);
        userService.updateUser(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO user) {
        try {
            userService.insertUser(user);
            return new ResponseEntity<>("회원가입 성공", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("회원가입 실패", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserDTO user) {
        HttpStatus status = null;
    	Map<String, Object> result = new HashMap<>();
    	UserDTO loginUser = userService.login(user.getEmail(), user.getPassword());
    	if(loginUser != null) {
    		result.put("message", "login 성공");
    		result.put("access-token", jwtUtil.createToken(loginUser.getUsername()));
    		status = HttpStatus.ACCEPTED;
    	} else {
    		status = HttpStatus.INTERNAL_SERVER_ERROR;
    	}
    	return new ResponseEntity<>(result, status);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();  // 세션을 무효화하여 로그아웃합니다.
        return new ResponseEntity<>("로그아웃 성공", HttpStatus.OK);
    }
    
}
