package com.outplaying.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.outplaying.dto.RegisterUserDTO;
import com.outplaying.dto.UserDTO;
import com.outplaying.service.IUserService;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private IUserService userService;

	@GetMapping(value = "", produces = "application/json")
	public List<UserDTO> listUsers() {

		return this.userService.getAll();
	}

	@GetMapping(value = "/{idUser}")
	public UserDTO userById(@PathVariable Long idUser) {

		return userService.findUserById(idUser);
	}

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDTO addUser(@RequestBody RegisterUserDTO registerUserDTO) {

		return userService.addUser(registerUserDTO.getUserDTO(), registerUserDTO.getCredentialDTO());
	}

	@PutMapping(value = "")
	public UserDTO updateUserById(@RequestBody UserDTO userDTO) {

		return this.userService.updateUser(userDTO);
	}

	@DeleteMapping(value = "/{idUser}")
	public Integer deleteById(@PathVariable Long idUser) {
		return this.userService.deleteById(idUser);
	}

}
