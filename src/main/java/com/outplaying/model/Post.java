package com.outplaying.model;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
	
	@Column(name="picture")
	private String picturesURL;
	
	@Column(name = "content_text", nullable = false, length = 600)
	private String contentText;

	@Temporal(TemporalType.DATE)
	@Column(name = "date", nullable = false)
	private Date date;

	@Column(name = "likes")
	private int likes;
	
	@Temporal(TemporalType.DATE)
	@Column(name="publish_date")
	private Date publishDate;
	
	@Column(name="published")
	private boolean published;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_user")
	@NotNull
	private User user;
	
	@Column(name="id_Approver")
	private Long idApprover;

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

	public User getUserIdUser() {
		return user ;
	}

	public void setUserIdUser(User userIdUser) {
		this.user = userIdUser;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Comment> getPostComments() {
		return postComments;
	}

	public void setPostComments(List<Comment> postComments) {
		this.postComments = postComments;
	}

	public Long getIdApprover() {
		return idApprover;
	}

	public void setIdApprover(Long idApprover) {
		this.idApprover = idApprover;
	}
}
