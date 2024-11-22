package com.example.FitTogether.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.FitTogether.dao.FollowDAO;
import com.example.FitTogether.dto.FollowDTO;

@Service
@Component
public class FollowServiceImpl implements FollowService {

	@Autowired
	private FollowDAO followDAO;
	
	@Override
    public FollowDTO getFollowById(int id) {
        return followDAO.getFollowById(id);
    }

    @Override
    public List<FollowDTO> getFollowersByUserId(int userId) {
        return followDAO.getFollowersByUserId(userId);
    }

    @Override
    public List<FollowDTO> getFollowingByUserId(int userId) {
        return followDAO.getFollowingByUserId(userId);
    }

    @Override
    public void insertFollow(FollowDTO follow) {
        followDAO.insertFollow(follow);
    }

    @Override
    public void deleteFollow(int id) {
        followDAO.deleteFollow(id);
    }

}
