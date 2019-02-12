package com.outplaying.service;

import java.util.List;

import com.outplaying.dto.UserDTO;

public interface IUserService {
	
	public UserDTO findUserById(Long idUser);

	public List<UserDTO> findUsersByFilter(UserDTO userDTO);

	public UserDTO addUser(UserDTO userDTO);
	
	public List<UserDTO> getAll();
}
