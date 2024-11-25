package com.example.FitTogether.dao;

import java.util.List;
import java.util.Map;

import com.example.FitTogether.dto.UserDTO;

public interface UserDAO {
    UserDTO getUserById(int id);
    UserDTO getUserByEmail(String email);
    UserDTO selectOne(Map<String, String> info);
    List<UserDTO> getAllUsers();
    void insertUser(UserDTO user);
    void updateUser(UserDTO user);
    void deleteUser(int id);
}