package com.outplaying.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.outplaying.service.ICommentService;

@RestController
@RequestMapping(value="comments")
public class CommentController {
	
	@Autowired
	private ICommentService commentService;

}
