package com.outplaying.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.outplaying.dto.CommentDTO;

@Service
public interface ICommentService {
	
	public List<CommentDTO> getListByPostId(Long idPost);
	
	public List<CommentDTO> getListByUserId(Long idUser);
	
	public CommentDTO getCommentById(Long idComment);
	
	public CommentDTO createComment(CommentDTO commentDTO);
	
	public CommentDTO addLikes(Long idComment);
	
	public Integer deleteComment(Long id);
	
	public Integer deleteCommentByAdmin(Long id);
	

}
