package com.outplaying.service.implementation;

import java.io.FileNotFoundException;
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

import com.outplaying.dto.PostDTO;
import com.outplaying.dto.PostListPaginatedDTO;
import com.outplaying.dto.PostStatusUpdateDTO;
import com.outplaying.enumerables.PostStatus;
import com.outplaying.model.Post;
import com.outplaying.model.User;
import com.outplaying.repository.IPostRepository;
import com.outplaying.repository.IUserRepository;
import com.outplaying.service.IPostService;
import com.outplaying.service.IStorageService;
import com.outplaying.utils.Validator;

@Service
public class PostServiceImpl implements IPostService {

	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private IPostRepository postRepository;

	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IStorageService storageService;

	
	
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
		if (Validator.isAuthenticated()) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			User user = userRepository.getOne(Long.parseLong(authentication.getName()));
			Post post = modelMapper.map(postDTO, Post.class);
			post.setUserIdUser(user);
			post.setPicturesURL("");
			post.setLikes(0);
			post.setStatus(PostStatus.Pendiente);
			post.setDate(new Date());
			post = postRepository.save(post);
			if(postDTO.getPicturesURL() != "" && postDTO.getPicturesURL() != null) {
				try {
					storageService.saveTempImgPostImg("",postDTO.getPicturesURL(), post.getIdPost().toString());
					String [] nameSpliting = postDTO.getPicturesURL().split("\\.");
					String fileName = nameSpliting[0] + post.getIdPost().toString() + "." + nameSpliting[1];
					post.setPicturesURL(fileName);
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			return modelMapper.map(postRepository.save(post), PostDTO.class);
		} else {
			throw new HttpMessageNotReadableException("you cant save post",
					new Throwable("you cant save post. the user binding to post isnt the authenticated"));
		}
	}

	@Override
	public PostDTO updatePost(PostDTO postDTO) {
		Optional<Post> postOp = postRepository.findById(postDTO.getIdPost());
		if (postOp.isPresent()) {
			if (Validator.ValidateIfIdIsOfAuthenticatedUser(postOp.get().getUser().getIdUser())) {
				if (PostStatus.Pendiente.equals(postOp.get().getStatus())) {
					Post post = postOp.get();
					post.setPostName(postDTO.getPostName());
					post.setContentText(postDTO.getContentText());
					try {
						String fileName = storageService.saveTempImgPostImg(post.getPicturesURL(),postDTO.getPicturesURL(),Long.toString(post.getIdPost()));
						if(fileName != null) {
							post.setPicturesURL(fileName);
						}
						
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					return modelMapper.map(postRepository.save(post), PostDTO.class);
				} else {
					throw new HttpMessageNotReadableException("you cant udpate post posted or rejected",
							new Throwable("you cant udpate post posted"));
				}
			} else {
				throw new HttpMessageNotReadableException("you cant update this post",
						new Throwable("you cant update this post "));
			}
		} else {
			throw new EntityNotFoundException("Post with id " + postDTO.getIdPost() + "does not exists");
		}

	}

	@Override
	public PostDTO addLikes(Long idPost) {
		if (Validator.isAuthenticated()) {
			Optional<Post> postOp = postRepository.findById(idPost);
			if (postOp.isPresent()) {
				Post post = postOp.get();
				post.setLikes(post.getLikes() + 1);
				return modelMapper.map(postRepository.save(post), PostDTO.class);

			} else {
				throw new EntityNotFoundException("Post  with id " + idPost + " does not exists");
			}
		} else {
			throw new HttpMessageNotReadableException("you cant add like to  this post",
					new Throwable("you cant add like to this post "));
		}

	}

	@Override
	public Integer deleteById(Long id) {
		Optional<Post> postOp = postRepository.findById(id);
		if (postOp.isPresent()) {
			if (Validator.ValidateIfIdIsOfAuthenticatedUser(postOp.get().getUser().getIdUser())) {
				return postRepository.removeByIdPost(id);
			} else {
				throw new HttpMessageNotReadableException("you cant delete this post",
						new Throwable("you cant delete this post"));
			}
		} else {
			throw new EntityNotFoundException("Post with id " + id + " does not exists");
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

	@Override
	public List<PostDTO> getByUserAuthenticated() {

		if (Validator.isAuthenticated()) {
			List<PostDTO> listDTO = new ArrayList<>();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Long idUser = Long.parseLong(authentication.getName());
			User user = userRepository.getOne(idUser);
			List<Post> listPost = postRepository.getPostByUser(user);
			for (Post p : listPost) {
				listDTO.add(modelMapper.map(p, PostDTO.class));
			}
			return listDTO;
		} else {
			throw new HttpMessageNotReadableException("you cant get this post list",
					new Throwable("you cant get this post list"));
		}
	}

	@Override
	public List<PostDTO> getManagedPost() {
		if (Validator.isAuthenticated()) {
			List<PostDTO> listDTO = new ArrayList<>();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Long idUser = Long.parseLong(authentication.getName());
			User user = userRepository.getOne(idUser);
			List<Post> listPost = postRepository.getPostByUserManager(user);
			for (Post p : listPost) {
				listDTO.add(modelMapper.map(p, PostDTO.class));
			}
			return listDTO;
		} else {
			throw new HttpMessageNotReadableException("you cant get this post list",
					new Throwable("you cant get this post list"));
		}
	}

	@Override
	public PostListPaginatedDTO getApprovedPostPaginated(int page, int size) {
		Pageable sortedByDate = 
				  PageRequest.of(page, size,Sort.by("date").descending());
		
		List<PostDTO> listPostDTO  = new ArrayList<>();
		Page<Post> pagePost = this.postRepository.findByStatus(PostStatus.Posteado,sortedByDate);
		List<Post> listPost = pagePost.getContent();
		for (Post p : listPost) {
			
			listPostDTO.add(modelMapper.map(p, PostDTO.class));
		}
		PostListPaginatedDTO postListPaginatedDTO =  new PostListPaginatedDTO();
		
		postListPaginatedDTO.setListPost(listPostDTO);
		postListPaginatedDTO.setNumberOfPages(pagePost.getTotalPages());
		return postListPaginatedDTO;
	}

}
