package com.example.FitTogether.service;

import com.example.FitTogether.dto.UserDTO;
import java.util.List;

public interface UserService {
    UserDTO getUserById(int id);
    UserDTO getUserByEmail(String email);
    List<UserDTO> getAllUsers();
    void insertUser(UserDTO user);
    void updateUser(UserDTO user);
    void deleteUser(int id);
    UserDTO login(String email, String password);
}
