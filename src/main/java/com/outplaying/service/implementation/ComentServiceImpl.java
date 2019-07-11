package com.outplaying.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outplaying.dto.CommentDTO;
import com.outplaying.model.Comment;
import com.outplaying.model.Post;
import com.outplaying.model.User;
import com.outplaying.repository.ICommentRepository;
import com.outplaying.repository.IPostRepository;
import com.outplaying.repository.IUserRepository;
import com.outplaying.service.ICommentService;
@Service
public class ComentServiceImpl implements ICommentService {
	
	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private ICommentRepository commentRepository; 
	
	@Autowired
	private IPostRepository postRepository;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public List<CommentDTO> getListByPostId(Long idPost) {
		List<CommentDTO> commentsDTO = new ArrayList<>();
		List<Comment> comentsBack = new ArrayList<>();
		Optional<Post> postOp = postRepository.findById(idPost);
		if(postOp.isPresent()) { 
			comentsBack = commentRepository.commentsByPost(postOp.get());
			for (Comment c : comentsBack) {
				commentsDTO.add(modelMapper.map(c, CommentDTO.class));
			}
			return commentsDTO;
		} else {
			throw new EntityNotFoundException("Post  with id " + idPost + " does not exists");
		}
		
	}

	@Override
	public List<CommentDTO> getListByUserId(Long idUser) {
		List<CommentDTO> commentsDTO = new ArrayList<>();
		List<Comment> comentsBack = new ArrayList<>();
		Optional<User> userOp = userRepository.findById(idUser);
		if(userOp.isPresent()) { 
			comentsBack = commentRepository.commentsByIdUser(userOp.get());
			for (Comment c : comentsBack) {
				commentsDTO.add(modelMapper.map(c, CommentDTO.class));
			}
			return commentsDTO;
		} else {
			throw new EntityNotFoundException("user  with id " + idUser + " does not exists");
		}
	}
	
	@Override
	public CommentDTO getCommentById(Long commentId) {
		
		return modelMapper.map(commentRepository.getOne(commentId), CommentDTO.class);
	}

	@Override
	public CommentDTO createComment(CommentDTO commentDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public CommentDTO addLikes(Long idComment) {
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
