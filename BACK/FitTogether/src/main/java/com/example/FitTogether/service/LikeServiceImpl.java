package com.example.FitTogether.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.FitTogether.dao.LikeDAO;
import com.example.FitTogether.dto.LikeDTO;

@Service
@Component
public class LikeServiceImpl implements LikeService {

	@Autowired
	private LikeDAO likeDAO;
	
	@Override
    public LikeDTO getLikeById(int id) {
        return likeDAO.getLikeById(id);
    }

    @Override
    public List<LikeDTO> getLikesByPostId(int postId) {
        return likeDAO.getLikesByPostId(postId);
    }

    @Override
    public void insertLike(LikeDTO like) {
        likeDAO.insertLike(like);
    }

    @Override
    public void deleteLike(int id) {
        likeDAO.deleteLike(id);
    }

}
