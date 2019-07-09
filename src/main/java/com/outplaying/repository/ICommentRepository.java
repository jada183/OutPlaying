package com.outplaying.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outplaying.model.Comment;

public interface ICommentRepository extends JpaRepository<Comment, Long> {

}
