package com.outplaying.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.outplaying.dto.CommentDTO;

@Service
public interface ICommentService {
	
	public List<CommentDTO> getList();
	
	public CommentDTO getCommentById();
	
	public CommentDTO createComment(CommentDTO commentDTO);
	
	public CommentDTO updateComment(CommentDTO commentDTO);
	
	public Integer deleteComment(Long id);
	
	public Integer deleteCommentByAdmin(Long id);
	

}
