package com.outplaying.dto;


import java.util.Date;


public class CommentDTO {

	private Long idComment;

	private String contentText;

	private int likes;

	private Date date;

	private Long idUser;

	private Long idPost;

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
		return idUser;
	}

	public Long getIdPost() {
		return idPost;
	}

	public void setIdPost(Long idPost) {
		this.idPost = idPost;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

}
