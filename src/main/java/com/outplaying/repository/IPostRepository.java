package com.outplaying.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.outplaying.model.Post;


@Repository
public interface IPostRepository extends JpaRepository<Post, Long> {

	
	@Transactional
	public Integer removeByIdPost(Long idPost);
}
