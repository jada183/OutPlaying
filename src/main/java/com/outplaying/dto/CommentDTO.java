package com.outplaying.dto;


import java.util.Date;


public class CommentDTO {

	private Long idComment;

	private String contentText;

	private int likes;

	private Date date;

	private Long IdUser;

	private Long IdPost;

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

	public Long getIdUser() {
		return IdUser;
	}

	public void setIdUser(Long idUser) {
		IdUser = idUser;
	}

	public Long getIdPost() {
		return IdPost;
	}

	public void setIdPost(Long idPost) {
		IdPost = idPost;
	}

}
