package com.outplaying.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.outplaying.dto.CredentialDTO;
import com.outplaying.model.Credential;
import com.outplaying.repository.ICredentialRepository;
import com.outplaying.service.ICredentialService;
import static java.util.Collections.emptyList;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class CredentialServiceImpl implements ICredentialService{

	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private ICredentialRepository credentialRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public CredentialServiceImpl(ICredentialRepository credentialRepository) {
		this.credentialRepository = credentialRepository;
	}

	@Override
	public CredentialDTO findCredentialById(Long idCredential) {

		return modelMapper.map(credentialRepository.getOne(idCredential), CredentialDTO.class);
	}

	@Override
	public CredentialDTO addCredential(CredentialDTO credentialDTO) {
		Credential credential = modelMapper.map(credentialDTO, Credential.class);
		credential.setPassword(bCryptPasswordEncoder.encode(credentialDTO.getPassword()));
		return modelMapper.map(credentialRepository.save(credential), CredentialDTO.class);
	}

	@Override
	public List<Credential> getAll() {
		List<CredentialDTO> listCredentialDTO = new ArrayList<CredentialDTO>();
		List<Credential> credentialList = new ArrayList<Credential>();
		credentialList = credentialRepository.findAll();
//		for (Credential credential : credentialList) {
//			listCredentialDTO.add(modelMapper.map(credential, CredentialDTO.class));
//		}
//		return listCredentialDTO;
		return credentialList;

	}

	@Override
	public CredentialDTO updateCredential(CredentialDTO credentialDTO) {

		return modelMapper.map(credentialRepository.save(modelMapper.map(credentialDTO, Credential.class)),
				CredentialDTO.class);
	}

	@Override
	public Integer deleteById(Long idCredential) {
		if (idCredential != null)
			return credentialRepository.removeByIdCredential(idCredential);

		return -1;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Credential credential = credentialRepository.findByUsername(username);
		if (credential == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(credential.getUsername(), credential.getPassword(), emptyList());
	}
}
