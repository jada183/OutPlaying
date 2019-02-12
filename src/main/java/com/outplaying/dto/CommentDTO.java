package com.outplaying.dto;

import java.sql.Date;

import com.outplaying.model.Post;
import com.outplaying.model.User;

public class CommentDTO {

	private Long idComment;

	private String contentText;

	private int likes;

	private Date date;

	private User userIdUser;

	private Post postIdPost;

	public Long getIdComment() {
		return idComment;
	}

	public void setIdComment(Long idComment) {
		this.idComment = idComment;
	}

	public String getContentText() {
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUserIdUser() {
		return userIdUser;
	}

	public void setUserIdUser(User userIdUser) {
		this.userIdUser = userIdUser;
	}

	public Post getPostIdPost() {
		return postIdPost;
	}

	public void setPostIdPost(Post postIdPost) {
		this.postIdPost = postIdPost;
	}

}
