package com.example.FitTogether.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.FitTogether.dao.UserDAO;
import com.example.FitTogether.dto.UserDTO;

@Service
@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
    public UserDTO getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public void insertUser(UserDTO user) {
        userDAO.insertUser(user);
    }

    @Override
    public void updateUser(UserDTO user) {
        userDAO.updateUser(user);
    }

    @Override
    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }

}
