package com.example.FitTogether.dao;

import java.util.List;


import com.example.FitTogether.dto.UserDTO;

public interface UserDAO {
    UserDTO getUserById(int id);
    List<UserDTO> getAllUsers();
    void insertUser(UserDTO user);
    void updateUser(UserDTO user);
    void deleteUser(int id);
}