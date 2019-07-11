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

import com.jayway.jsonpath.internal.Path;
import com.outplaying.dto.CommentDTO;
import com.outplaying.service.ICommentService;

@RestController
@RequestMapping(value="comments")
public class CommentController {
	
	@Autowired
	private ICommentService commentService;
	
	@GetMapping(value="/post/{idPost}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CommentDTO> getListOfCommentsByPostId(@PathVariable Long idPost) { 
		return this.commentService.getListByPostId(idPost);
	}
	
	@GetMapping(value="/user/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CommentDTO> getListOfCommentsByUserId(@PathVariable Long idUser) { 
		return this.commentService.getListByUserId(idUser);
	}
	
	@GetMapping(value="/{idComment}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CommentDTO getCommentById(@PathVariable Long idComment) {
		return this.commentService.getCommentById(idComment);
	}
	
	@PostMapping(value="", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	public CommentDTO saveComment(@RequestBody CommentDTO commentDTO) {
		return this.commentService.createComment(commentDTO);
	}
	
	@PutMapping(value="/{idComment}")
	public CommentDTO addLike(@PathVariable Long idComment) { 
		return this.commentService.addLikes(idComment);
	}
	
	@DeleteMapping(value="{idComment}")
	public Integer deleteComment(@PathVariable Long idComment) {
		return this.commentService.deleteComment(idComment);
	}
	
	@DeleteMapping(value="")
	public Integer deleteByAdmin(@PathVariable Long idComment) {
		return this.commentService.deleteCommentByAdmin(idComment);
	}
	
}

