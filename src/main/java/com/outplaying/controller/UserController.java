package com.outplaying.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.outplaying.dto.UserDTO;
import com.outplaying.model.User;
import com.outplaying.service.IUserService;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
	
	@Autowired
	private IUserService userService;

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDTO addUser(@RequestBody UserDTO userDTO) {
		
		return userService.addUser(userDTO);
	}
	
	@GetMapping(value="", produces = "application/json")
	public List<UserDTO> listUsers() { 
		
		return this.userService.getAll();
	}

}
