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

import com.outplaying.dto.PostDTO;
import com.outplaying.dto.PostStatusUpdateDTO;
import com.outplaying.enumerables.PostStatus;
import com.outplaying.model.Post;
import com.outplaying.model.User;
import com.outplaying.repository.IPostRepository;
import com.outplaying.repository.IUserRepository;
import com.outplaying.service.IPostService;

@Service
public class PostServiceImpl implements IPostService {

	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private IPostRepository postRepository;

	@Autowired
	private IUserRepository userRepository;

	@Override
	public PostDTO findPostById(Long id) {

		return modelMapper.map(postRepository.getOne(id), PostDTO.class);
	}

	@Override
	public List<PostDTO> getAll() {
		List<PostDTO> listDTO = new ArrayList<>();
		List<Post> listPost = postRepository.findAll();
		for (Post p : listPost) {
			listDTO.add(modelMapper.map(p, PostDTO.class));
		}
		return listDTO;
	}

	@Override
	public PostDTO addPost(PostDTO postDTO) {

		// temporal para no tener que estar siempre autentificado para modificar post
		// Post post = modelMapper.map(postDTO, Post.class);
		// post.setLikes(0);
		// post.setStatus(postDTO.getStatus());
		// post.setDate(new Date());
		// return modelMapper.map(postRepository.save(post), PostDTO.class);

		// comprobar que el idUser del post es del usuario autentificado.

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!"anonymousUser".equals(authentication.getName())) {
			Long idUserAuth = Long.parseLong(authentication.getName());
			;

			if (postDTO.getIdUser() == idUserAuth) {
				Post post = modelMapper.map(postDTO, Post.class);
				post.setLikes(0);
				post.setStatus(postDTO.getStatus());
				post.setDate(new Date());
				return modelMapper.map(postRepository.save(post), PostDTO.class);
			} else {
				throw new HttpMessageNotReadableException("you cant save post",
						new Throwable("you cant save post. the user binding to post isnt the authenticated"));
			}
		} else {
			throw new HttpMessageNotReadableException("you cant add post. you dont have authenticated ",
					new Throwable("you cant add this post, you dont have authenticated "));
		}
	}

	@Override
	public PostDTO updatePost(PostDTO postDTO) {

		// metodo que comprueba autentificacion
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!"anonymousUser".equals(authentication.getName())) {
			Long idUserAuth = Long.parseLong(authentication.getName());
			Optional<Post> postOp = postRepository.findById(postDTO.getIdPost());

			if (postOp.isPresent()) {
				if (PostStatus.Pendiente.equals(postOp.get().getStatus())) {
					if (postOp.get().getUser().getIdUser() == idUserAuth) {
						Post post = postOp.get();
						post.setPostName(postDTO.getPostName());
						post.setPicturesURL(postDTO.getPicturesURL());
						post.setContentText(postDTO.getContentText());
						return modelMapper.map(postRepository.save(post), PostDTO.class);
					} else {
						throw new HttpMessageNotReadableException("you cant update this post",
								new Throwable("you cant update this post "));
					}

				} else {
					throw new HttpMessageNotReadableException("you cant udpate post posted",
							new Throwable("you cant udpate post posted"));
				}
			} else {
				throw new EntityNotFoundException("Post  with id " + postDTO.getIdPost() + " does not exists");
			}
		} else {
			throw new HttpMessageNotReadableException("you cant update this post. you dont have authenticated ",
					new Throwable("you cant update this post, you dont have authenticated "));
		}

	}

	@Override
	public PostDTO addLikes(Long idPost) {
		// metodo que comprueba autentificacion
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!"anonymousUser".equals(authentication.getName())) {
			Long idUserAuth = Long.parseLong(authentication.getName());
			Optional<Post> postOp = postRepository.findById(idPost);
			if (postOp.isPresent()) {
				if (postOp.get().getUser().getIdUser() == idUserAuth) {
					Post post = postOp.get();
					post.setLikes(post.getLikes() + 1);
					return modelMapper.map(postRepository.save(post), PostDTO.class);
				} else {
					throw new HttpMessageNotReadableException("you cant update this post",
							new Throwable("you cant update this post "));
				}

			} else {
				throw new EntityNotFoundException("Post  with id " + idPost + " does not exists");
			}
		} else {
			throw new HttpMessageNotReadableException("you cant give like to this post. you dont have authenticated ",
					new Throwable("you cant give like to this post, you dont have authenticated "));
		}

	}

	@Override
	public Integer deleteById(Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!"anonymousUser".equals(authentication.getName())) {
			Long idUserAuth = Long.parseLong(authentication.getName());
			Optional<Post> postOp = postRepository.findById(id);
			if (postOp.isPresent()) {
				if (postOp.get().getUser().getIdUser() == idUserAuth) {
					return postRepository.removeByIdPost(id);
				} else {
					throw new HttpMessageNotReadableException("you cant delete this post",
							new Throwable("you cant delete this post"));
				}
			} else {
				throw new EntityNotFoundException("Post  with id " + id + " does not exists");
			}
		} else {
			throw new HttpMessageNotReadableException("you cant delete this post. you dont have authenticated ",
					new Throwable("you cant delete this post, you dont have authenticated "));
		}

	}

	@Override
	public PostDTO updateStatusPost(PostStatusUpdateDTO postStatusUpdateDTO) {

		Optional<Post> postOp = postRepository.findById(postStatusUpdateDTO.getIdPost());

		if (postOp.isPresent()) {
			Post post = postOp.get();
			post.setStatus(postStatusUpdateDTO.getStatus());
			post.setManageDate(new Date());
			Optional<User> userOp = userRepository.findById(postStatusUpdateDTO.getIdUserManager());
			if (userOp.isPresent()) {
				post.setUserManager(userOp.get());

			} else {
				throw new EntityNotFoundException("User  with id " + postStatusUpdateDTO.getIdUserManager()
						+ " does not exists to managed this post");
			}
			return modelMapper.map(postRepository.save(post), PostDTO.class);
		} else {
			throw new EntityNotFoundException("Post  with id " + postStatusUpdateDTO.getIdPost() + " does not exists");
		}
	}

	@Override
	public PostDTO updatePostByAdmin(PostDTO postDTO) {
		Optional<Post> postOp = postRepository.findById(postDTO.getIdPost());
		if (postOp.isPresent()) {
			if (PostStatus.Pendiente.equals(postOp.get().getStatus())) {
				Post post = postOp.get();
				post.setPostName(postDTO.getPostName());
				post.setPicturesURL(postDTO.getPicturesURL());
				post.setContentText(postDTO.getContentText());
				return modelMapper.map(postRepository.save(post), PostDTO.class);
			} else {
				throw new HttpMessageNotReadableException("you cant udpate post posted",
						new Throwable("you cant udpate post posted"));
			}
		} else {
			throw new EntityNotFoundException("Post  with id " + postDTO.getIdPost() + " does not exists");
		}
	}

	@Override
	public Integer deleteByAdmin(Long id) {
		if (id != null)
			return postRepository.removeByIdPost(id);

		return -1;
	}

}
