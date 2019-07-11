package com.outplaying.dto;

import com.outplaying.enumerables.PostStatus;

public class PostStatusUpdateDTO {

	private Long idPost;
	
	private PostStatus status;
	
	private Long  idUserManager;

	public Long getIdPost() {
		return idPost;
	}

	public void setIdPost(Long idPost) {
		this.idPost = idPost;
	}

	public PostStatus getStatus() {
		return status;
	}

	public void setStatus(PostStatus status) {
		this.status = status;
	}

	public Long getIdUserManager() {
		return idUserManager;
	}

	public void setIdUserManager(Long idUserManager) {
		this.idUserManager = idUserManager;
	}
	
	

}
