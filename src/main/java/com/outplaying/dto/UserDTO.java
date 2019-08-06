package com.outplaying.dto;

import java.util.Date;

public class UserDTO {

	private long idUser;

	private String name;

	private String surname;

	private String email;

	private String role;

	private Date createAcountDate;
	
	private String urlImg;

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
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

}
