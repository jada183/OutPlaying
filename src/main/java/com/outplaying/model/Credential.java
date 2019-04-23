package com.outplaying.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "user_credentials")
public class Credential {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_user_credential", length = 20)
	private Long idCredential;

	@Column(name = "username", unique = true, length = 45, nullable = false)
	private String username;

	@Column(name = "password", length = 255, nullable = false)
	private String password;

	@OneToOne
	@JoinColumn(name = "id_user", nullable = false)
	private User user;

	public Long getIdCredential() {
		return idCredential;
	}

	public void setIdCredential(Long idCredential) {
		this.idCredential = idCredential;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
