package com.outplaying.service.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.outplaying.dto.CommentDTO;
import com.outplaying.dto.PostDTO;
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
		if (postOp.isPresent()) {
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
		if (userOp.isPresent()) {
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
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!"anonymousUser".equals(authentication.getName())) {
			Long idUserAuth = Long.parseLong(authentication.getName());
			if (idUserAuth == commentDTO.getIdUser()) {
				Comment comment = modelMapper.map(commentDTO, Comment.class);
				comment.setLikes(0);
				comment.setDate(new Date());
				return modelMapper.map(commentRepository.save(comment), CommentDTO.class);
			} else {
				throw new HttpMessageNotReadableException("you cant save this comment",
						new Throwable("you cant save comment. the user binding to comment isnt the authenticated"));
			}
		} else {
			throw new HttpMessageNotReadableException("you cant add comment. you dont have authenticated ",
					new Throwable("you cant add this comment, you dont have authenticated "));
		}
	}

	@Override
	public CommentDTO addLikes(Long idComment) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!"anonymousUser".equals(authentication.getName())) {
			Long idUserAuth = Long.parseLong(authentication.getName());
			Optional<Comment> commentOp = commentRepository.findById(idComment);
			if (commentOp.isPresent()) {
				if (commentOp.get().getUser().getIdUser() == idUserAuth) {
					Comment comment = commentOp.get();
					comment.setLikes(comment.getLikes() + 1);
					return modelMapper.map(commentRepository.save(comment), CommentDTO.class);
				} else {
					throw new HttpMessageNotReadableException("you cant update this comment",
							new Throwable("you cant update this  comment"));
				}
			} else {
				throw new EntityNotFoundException("Comment  with id " + idComment + " does not exists");
			}
		} else {
			throw new HttpMessageNotReadableException(
					"you cant give like to this comment. you dont have authenticated ",
					new Throwable("you cant give like to this comment, you dont have authenticated "));
		}
	}

	@Override
	public Integer deleteComment(Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!"anonymousUser".equals(authentication.getName())) {
			Long idUserAuth = Long.parseLong(authentication.getName());
			Optional<Comment> commentOp = commentRepository.findById(id);
			if (commentOp.isPresent()) {
				if (commentOp.get().getUser().getIdUser() == idUserAuth) {
					return commentRepository.removeByIdComment(id);
				} else {
					throw new HttpMessageNotReadableException("you cant delete this comment",
							new Throwable("you cant delete this comment"));
				}
			} else { 
				throw new EntityNotFoundException("comment  with id " + id + " does not exists");
			}
		} else {
			throw new HttpMessageNotReadableException("you cant delete this post. you dont have authenticated ",
					new Throwable("you cant delete this post, you dont have authenticated "));
		}

	}

	@Override
	public Integer deleteCommentByAdmin(Long id) {
		if (id != null)
			return commentRepository.removeByIdComment(id);

		return -1;
	}

}
