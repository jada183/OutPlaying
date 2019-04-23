package com.outplaying.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.outplaying.dto.UserDTO;
@Service
public interface IUserService {

	public UserDTO findUserById(Long idUser);

	public List<UserDTO> findUsersByFilter(UserDTO userDTO);

	public UserDTO addUser(UserDTO userDTO);

	public List<UserDTO> getAll();

	public UserDTO updateUser(UserDTO userDTO);
	
	public Integer deleteById(Long idUser);
}
