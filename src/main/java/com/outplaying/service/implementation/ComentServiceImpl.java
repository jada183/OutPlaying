package com.outplaying.service.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.outplaying.dto.CommentDTO;
import com.outplaying.repository.ICommentRepository;
import com.outplaying.service.ICommentService;

public class ComentServiceImpl implements ICommentService {
	
	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private ICommentRepository commentRepository; 
	
	@Override
	public List<CommentDTO> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentDTO getCommentById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentDTO createComment(CommentDTO commentDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentDTO updateComment(CommentDTO commentDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteComment(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteCommentByAdmin(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
