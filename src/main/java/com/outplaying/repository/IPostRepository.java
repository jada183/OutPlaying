package com.outplaying.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.outplaying.model.Post;
import com.outplaying.model.User;


@Repository
public interface IPostRepository extends JpaRepository<Post, Long> {

	
	@Transactional
	public Integer removeByIdPost(Long idPost);
	
	@Query("Select p from Post p where p.user =?1")
	public List<Post> getPostByUser(User user);
	

	@Query("Select p from Post p where p.userManager =?1")
	public List<Post> getPostByUserManager(User user);
}
