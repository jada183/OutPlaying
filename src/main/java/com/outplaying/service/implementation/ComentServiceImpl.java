package com.outplaying.service.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.outplaying.dto.CommentDTO;
import com.outplaying.dto.CommentListPaginated;
import com.outplaying.model.Comment;
import com.outplaying.model.Post;
import com.outplaying.model.User;
import com.outplaying.repository.ICommentRepository;
import com.outplaying.repository.IPostRepository;
import com.outplaying.repository.IUserRepository;
import com.outplaying.service.ICommentService;
import com.outplaying.utils.Validator;

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
	public CommentListPaginated  getListByPostId(Long idPost, int page, int size) {
		List<CommentDTO> commentsDTO = new ArrayList<>();
		List<Comment> comentsBack = new ArrayList<>();
		Optional<Post> postOp = postRepository.findById(idPost);
		if (postOp.isPresent()) {
			Pageable sortedByDate = 
					  PageRequest.of(page, size,Sort.by("date").descending());
			Page<Comment> pageComment = commentRepository.findByPost(postOp.get(), sortedByDate);
			comentsBack = pageComment.getContent();
			for (Comment c : comentsBack) {
				commentsDTO.add(modelMapper.map(c, CommentDTO.class));
			}
			CommentListPaginated commentListPaginated =  new CommentListPaginated();
			commentListPaginated.setCommentList(commentsDTO);
			commentListPaginated.setNumberOfPages(pageComment.getTotalPages());
			return commentListPaginated;
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
		if (Validator.isAuthenticated()) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			User user = userRepository.getOne(Long.parseLong(authentication.getName()));
			Comment comment = modelMapper.map(commentDTO, Comment.class);
			comment.setUser(user);
			comment.setLikes(0);
			comment.setDate(new Date());
			return modelMapper.map(commentRepository.save(comment), CommentDTO.class);
		} else {
			throw new HttpMessageNotReadableException("you cant add comment",
					new Throwable("you cant add this comment"));
		}
	}

	@Override
	public CommentDTO addLikes(Long idComment) {
		if (Validator.isAuthenticated()) {
			Optional<Comment> commentOp = commentRepository.findById(idComment);
			if (commentOp.isPresent()) {
				Comment comment = commentOp.get();
				comment.setLikes(comment.getLikes() + 1);
				return modelMapper.map(commentRepository.save(comment), CommentDTO.class);
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
		Optional<Comment> commentOp = commentRepository.findById(id);
		if (commentOp.isPresent()) {
			if (Validator.ValidateIfIdIsOfAuthenticatedUser(commentOp.get().getUser().getIdUser())) {
				return commentRepository.removeByIdComment(id);
			} else {
				throw new HttpMessageNotReadableException("you cant delete this comment",
						new Throwable("you cant delete this comment"));
			}
		} else {
			throw new EntityNotFoundException("comment with id " + id + " doesn´t exists");
		}

	}

	@Override
	public Integer deleteCommentByAdmin(Long id) {
		if (id != null)
			return commentRepository.removeByIdComment(id);

		return -1;
	}

}
