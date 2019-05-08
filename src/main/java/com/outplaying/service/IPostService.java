package com.outplaying.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.outplaying.dto.PostDTO;


@Service
public interface IPostService {

	public PostDTO findPostById(Long id);
	
	public List<PostDTO> getAll();
	
	public PostDTO addPost(PostDTO postDTO);
	
	public PostDTO updatePost(PostDTO postDTO);
	
	public Integer deleteById(Long id);
	
	public PostDTO updateStatusPost(PostDTO postDTO);
	
}
