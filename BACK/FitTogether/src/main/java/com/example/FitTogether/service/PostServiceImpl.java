package com.example.FitTogether.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.FitTogether.dao.PostDAO;
import com.example.FitTogether.dto.PostDTO;

@Service
@Component
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDAO postDAO;
	
	@Override
    public PostDTO getPostById(int id) {
        return postDAO.getPostById(id);
    }
	@Override
	public List<PostDTO> getAllPost() {
		return postDAO.getAllPost();
	}

    @Override
    public List<PostDTO> getPostsByUserId(int userId) {
        return postDAO.getPostsByUserId(userId);
    }

    @Override
    public void insertPost(PostDTO post) {
        postDAO.insertPost(post);
    }

    @Override
    public void updatePost(PostDTO post) {
        postDAO.updatePost(post);
    }

    @Override
    public void deletePost(int id) {
        postDAO.deletePost(id);
    }


}
