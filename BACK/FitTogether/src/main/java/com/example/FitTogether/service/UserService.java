package com.example.FitTogether.service;

import java.util.List;

import com.example.FitTogether.dto.UserDTO;

public interface UserService {
    UserDTO getUserById(int id);
    List<UserDTO> getAllUsers();
    void insertUser(UserDTO user);
    void updateUser(UserDTO user);
    void deleteUser(int id);
}
