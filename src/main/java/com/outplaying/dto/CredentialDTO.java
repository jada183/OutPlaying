package com.outplaying.dto;

public class CredentialDTO {

	private Long idCredential;

	private String username;

	private String password;
	
	private Long idUser;

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

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

}
