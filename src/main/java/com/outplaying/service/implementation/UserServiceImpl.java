package com.outplaying.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outplaying.dto.UserDTO;
import com.outplaying.model.User;
import com.outplaying.repository.IUserRepository;
import com.outplaying.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private IUserRepository userRepository;

	@Override
	public UserDTO findUserById(Long idUser) {
		// cambiar mapeador

		return modelMapper.map(userRepository.getOne(idUser), UserDTO.class);
	}

	@Override
	public List<UserDTO> findUsersByFilter(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO addUser(UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		return modelMapper.map(userRepository.save(user), UserDTO.class);
	}

	@Override
	public List<UserDTO> getAll() {

		List<UserDTO> listUsers = new ArrayList<UserDTO>();
		List<User> usersBack = new ArrayList<User>();
		usersBack = (List<User>) this.userRepository.findAll();
		for (User user : usersBack) {
			listUsers.add(this.modelMapper.map(user, UserDTO.class));
		}
		return listUsers;
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO) {

		return modelMapper.map(userRepository.save(modelMapper.map(userDTO, User.class)), UserDTO.class);
	}

	@Override
	public Integer deleteById(Long idUser) {
		if (idUser != null)
			return userRepository.removeByIdUser(idUser);

		return -1;
	}

}
