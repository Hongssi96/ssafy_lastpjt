package com.example.FitTogether.service;

import com.example.FitTogether.dao.UserDAO;
import com.example.FitTogether.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDTO getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
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

	@Override
	public UserDTO login(String email, String password) {
		Map<String, String> info = new HashMap<>();
		info.put("email", email);
		info.put("password", password);
		UserDTO tmp = userDAO.selectOne(info);
		return tmp;
	}
}
