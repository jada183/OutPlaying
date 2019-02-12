package com.outplaying.model;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "post")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_post")
	private Long idPost;

	@Column(name = "post_name", nullable = false)
	private String postName;

	@Column(name = "content_text", nullable = false)
	private String contentText;

	@Column(name = "date", nullable = false)
	private Date date;

	@Column(name = "likes")
	private int likes;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "user_id_user")
	@NotNull
	private User user;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy="post")
	private List<Comment> postComments = new ArrayList<>();

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
		return user ;
	}

	public void setUserIdUser(User userIdUser) {
		this.user = userIdUser;
	}
}
