package com.outplaying.service.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outplaying.dto.PostDTO;
import com.outplaying.model.Post;
import com.outplaying.repository.IPostRepository;
import com.outplaying.service.IPostService;


@Service
public class PostServiceImpl implements IPostService {

	ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	private IPostRepository postRepository;
	
	@Override
	public PostDTO findPostById(Long id) {
		
		return modelMapper.map(postRepository.getOne(id), PostDTO.class);
	}

	@Override
	public List<PostDTO> getAll() {
		List<PostDTO> listDTO = new ArrayList<>();
		List<Post> listPost = postRepository.findAll();
		for (Post p:  listPost) {
			listDTO.add(modelMapper.map(p, PostDTO.class));
		}
		return listDTO;
	}

	@Override
	public PostDTO addPost(PostDTO postDTO) {
		Post post = modelMapper.map(postDTO, Post.class);
		post.setLikes(0);
		post.setPublished(false);
		post.setDate(new Date());

		return modelMapper.map(postRepository.save(post), PostDTO.class);
	}

	@Override
	public PostDTO updatePost(PostDTO postDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
