package com.outplaying.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.outplaying.dto.PostDTO;
import com.outplaying.dto.PostStatusUpdateDTO;


@Service
public interface IPostService {

	public PostDTO findPostById(Long id);
	
	public List<PostDTO> getAll();
	
	public List<PostDTO> getByUserAuthenticated();
	
	public List<PostDTO> getManagedPost();
	
	public PostDTO addPost(PostDTO postDTO);
	
	public PostDTO updatePost(PostDTO postDTO);
	
	public PostDTO addLikes(Long idPost);
	
	public Integer deleteById(Long id);
	
	public PostDTO updateStatusPost(PostStatusUpdateDTO postStatusUpdateDTO);
	
	public PostDTO updatePostByAdmin(PostDTO postDTO);
	
	public Integer deleteByAdmin(Long id);
}
