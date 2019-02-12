package com.outplaying.dto;

import java.sql.Date;
import java.util.List;

import com.outplaying.model.User;

public class PostDTO {

	private Long idPost;

	private String postName;

	private List<String> picturesURL;

	private String contentText;

	private Date date;

	private int likes;

	private User userIdUser;

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

	public List<String> getPicturesURL() {
		return picturesURL;
	}

	public void setPicturesURL(List<String> picturesURL) {
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

	public User getUserIdUser() {
		return userIdUser;
	}

	public void setUserIdUser(User userIdUser) {
		this.userIdUser = userIdUser;
	}

}
