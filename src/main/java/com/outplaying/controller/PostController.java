package com.outplaying.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.outplaying.dto.PostDTO;
import com.outplaying.service.IPostService;

@RestController
@RequestMapping(value="post")
public class PostController {
	
	@Autowired
	private IPostService postService;
	
	
	@GetMapping(value="")
	public List<PostDTO> getAll() {
		return this.postService.getAll();
	}
	
	@GetMapping(value="/{idPost}")
	public PostDTO getPostById(@PathVariable(value = "idPost") Long idPost) {
		return this.postService.findPostById(idPost);
	}
	
	@PostMapping(value="",  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PostDTO addPost(@RequestBody PostDTO postDTO) {
		return this.postService.addPost(postDTO);
	}
	
	@PutMapping(value= "")
	public PostDTO updatePost(@RequestBody PostDTO postDTO) {
		return this.postService.updatePost(postDTO);
	}
	
	@DeleteMapping(value="/{idPost}")
	public Integer deleteById(@PathVariable Long idPost) {
		return this.postService.deleteById(idPost);
	}
}
