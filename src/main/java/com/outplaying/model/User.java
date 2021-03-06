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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Long idUser;

	@Column(name = "name", length = 45)
	private String name;

	@Column(name = "surname", length = 45)
	private String surname;

	@Column(name = "email", length = 80, unique = true, nullable = false)
	@Email
	private String email;

	@Column(name = "role", length = 45, nullable = false)
	private String role;
	
	@Temporal(TemporalType.DATE)
	@Column(name="create_acount_date", nullable = false)
	private Date createAcountDate;
	
	@Column(name="url_img")
	private String urlImg;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "user")
	private List<Post> userPost = new ArrayList<>();

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true, fetch = FetchType.LAZY)
	private Credential credential;


	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "user")
	private List<Comment> userComments = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy="userManager", orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Post> postManaged = new ArrayList<>();
	
	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Credential getCredential() {
		return credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}

	public String getRole() {

		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getCreateAcountDate() {
		return createAcountDate;
	}

	public void setCreateAcountDate(Date createAcountDate) {
		this.createAcountDate = createAcountDate;
	}
	
	
	public String getUrlImg() {
		return urlImg;
	}

	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}

	public List<Post> getUserPost() {
		return userPost;
	}

	public void setUserPost(List<Post> userPost) {
		this.userPost = userPost;
	}

	public List<Comment> getUserComments() {
		return userComments;
	}

	public void setUserComments(List<Comment> userComments) {
		this.userComments = userComments;
	}

	public List<Post> getPostManaged() {
		return postManaged;
	}

	public void setPostManaged(List<Post> postManaged) {
		this.postManaged = postManaged;
	}
	
}
