package com.outplaying.dto;

import java.util.Date;

public class PostDTO {

	private Long idPost;

	private String postName;

	private String picturesURL;

	private String contentText;

	private Date date;

	private int likes;
	
	private Date publishDate;
	
	private boolean published;

	private Long IdUser;

	private Long idApprover;
	
	public Long getIdPost() {
		return idPost;
	}

	public void setIdPost(Long idPost) {
		this.idPost = idPost;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getPicturesURL() {
		return picturesURL;
	}

	public void setPicturesURL(String picturesURL) {
		this.picturesURL = picturesURL;
	}

	public String getContentText() {
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public Long getIdUser() {
		return IdUser;
	}

	public void setIdUser(Long idUser) {
		IdUser = idUser;
	}

	public Long getIdApprover() {
		return idApprover;
	}

	public void setIdApprover(Long idApprover) {
		this.idApprover = idApprover;
	}
}
