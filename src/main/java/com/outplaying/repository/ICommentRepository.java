package com.outplaying.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.outplaying.model.Comment;
import com.outplaying.model.Post;
import com.outplaying.model.User;


public interface ICommentRepository extends JpaRepository<Comment, Long> {

	
	@Transactional
	public Integer removeByIdComment(Long idComment);
	
	public Page<Comment> findByPost(Post post, Pageable pageable);
	
	@Transactional
	@Query("SELECT c  FROM Comment c WHERE c.user = ?1")
	public List<Comment> commentsByIdUser(User user);
}


