package com.outplaying.dto;

import com.outplaying.model.User;

public class CredentialDTO {

	private Long idCredential;

	private String username;

	private String password;

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
