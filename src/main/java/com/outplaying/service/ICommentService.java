package com.outplaying.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.outplaying.dto.CommentDTO;
import com.outplaying.dto.CommentListPaginated;

@Service
public interface ICommentService {
	
	public CommentListPaginated getListByPostId(Long idPost, int page, int size);
	
	public List<CommentDTO> getListByUserId(Long idUser);
	
	public CommentDTO getCommentById(Long idComment);
	
	public CommentDTO createComment(CommentDTO commentDTO);
	
	public CommentDTO addLikes(Long idComment);
	
	public Integer deleteComment(Long id);
	
	public Integer deleteCommentByAdmin(Long id);
	

}
