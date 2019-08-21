package com.outplaying.dto;

import java.util.List;

public class PostListPaginatedDTO {
	private List<PostDTO> listPost;
	private int numberOfPages;
	
	public List<PostDTO> getListPost() {
		return listPost;
	}
	public void setListPost(List<PostDTO> listPost) {
		this.listPost = listPost;
	}
	public int getNumberOfPages() {
		return numberOfPages;
	}
	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	} 
	
	
	
}
