package com.outplaying.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_comment")
	private Long idComment;

	@Column(name = "content_text")
	private String contentText;

	@Column(name = "likes")
	private int likes;

	@Column(name = "date")
	private Date date;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_user")
	@NotNull
	private User user;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_post")
	@NotNull
	private Post post;

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
		return user;
	}

	public void setUserIdUser(User user) {
		this.user = user;
	}

	public Post getPostIdPost() {
		return post;
	}

	public void setPostIdPost(Post postIdPost) {
		this.post = postIdPost;
	}

}
