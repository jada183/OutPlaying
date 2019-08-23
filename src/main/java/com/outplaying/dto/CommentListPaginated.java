package com.outplaying.dto;

import java.util.List;

public class CommentListPaginated {

	private List<CommentDTO> commentList;
	
	private int numberOfPages;

	public List<CommentDTO> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentDTO> commentList) {
		this.commentList = commentList;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}
	
	
}
