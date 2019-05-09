package com.outplaying.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.outplaying.dto.CredentialDTO;
import com.outplaying.dto.UserDTO;
@Service
public interface IUserService {

	public UserDTO findUserById(Long idUser);

	public List<UserDTO> findUsersByFilter(UserDTO userDTO);

	public UserDTO addUser(UserDTO userDTO, CredentialDTO credentialDTO);

	public List<UserDTO> getAll();

	public UserDTO updateUser(UserDTO userDTO);
	
	public UserDTO updateUserByAdmin(UserDTO userDTO);
	
	public Integer deleteById(Long idUser);
	
	public Integer deleteByAdmin(Long idUser);
}
