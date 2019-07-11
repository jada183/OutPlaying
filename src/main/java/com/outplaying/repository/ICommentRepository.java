package com.outplaying.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.outplaying.model.Comment;
import com.outplaying.model.Post;


public interface ICommentRepository extends JpaRepository<Comment, Long> {

	
	@Transactional
	@Query("SELECT c  FROM Comment c WHERE c.post = ?1")
	public List<Comment> commentsByPost(Post post);
}
