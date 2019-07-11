package com.outplaying.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.outplaying.dto.CredentialDTO;
import com.outplaying.dto.UpdatePasswordDTO;
import com.outplaying.model.Credential;
import com.outplaying.repository.ICredentialRepository;
import com.outplaying.repository.IUserRepository;
import com.outplaying.service.ICredentialService;

@Service
public class CredentialServiceImpl implements UserDetailsService, ICredentialService {

	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private ICredentialRepository credentialRepository;
	
	@Autowired
	private IUserRepository userRepository;
	
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
	public CredentialDTO addCredential(CredentialDTO credentialDTO ) {
		Credential credential = modelMapper.map(credentialDTO, Credential.class);
		String password = bCryptPasswordEncoder.encode(credential.getPassword());
		credential.setPassword(password);
		return modelMapper.map(credentialRepository.save(credential), CredentialDTO.class);
	}

	@Override
	public List<CredentialDTO> getAll() {
		List<CredentialDTO> listCredentialDTO = new ArrayList<CredentialDTO>();
		List<Credential> credentialList = new ArrayList<Credential>();
		credentialList = credentialRepository.findAll();
		for (Credential credential : credentialList) {
			listCredentialDTO.add(modelMapper.map(credential, CredentialDTO.class));
		}
		return listCredentialDTO;
		//return credentialList;

	}

	@Override
	public CredentialDTO updateCredential(CredentialDTO credentialDTO) {
		Credential credential = modelMapper.map(credentialDTO, Credential.class);
		String password = bCryptPasswordEncoder.encode(credential.getPassword());
		credential.setPassword(password);
		return modelMapper.map(credentialRepository.save(credential),
				CredentialDTO.class);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Credential credential = this.credentialRepository.findByusername(username);
		if (credential == null) {
			throw new UsernameNotFoundException(username);
		}
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
            	.commaSeparatedStringToAuthorityList("ROLE_" + credential.getUser().getRole());
		return new User(credential.getUser().getIdUser().toString(), credential.getPassword(), grantedAuthorities);
	}

	
	@Override
	public CredentialDTO updatePassword(UpdatePasswordDTO updatePasswordDTO) {
		Optional<com.outplaying.model.User> userOp = userRepository.findById(updatePasswordDTO.getIdUser());

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!"anonymousUser".equals(authentication.getName())) {
			Long idUserAuth = Long.parseLong(authentication.getName());

			if (updatePasswordDTO.getIdUser() == idUserAuth) {
				Credential credential = credentialRepository.credentialByIdUSer(userOp.get());
				
				if (bCryptPasswordEncoder.matches(updatePasswordDTO.getLastPassword(), credential.getPassword())) {
					String password = bCryptPasswordEncoder.encode(updatePasswordDTO.getNewPassword());
					credential.setPassword(password);
					return modelMapper.map(credentialRepository.save(credential), CredentialDTO.class);
				} else {
					throw new HttpMessageNotReadableException(
							"you cant update this credentials. the last password doesnt math",
							new Throwable("you cant update this credentials"));
				}

			} else {
				throw new EntityNotFoundException("user with id:  " + updatePasswordDTO.getIdUser() + " doesnt found");
			}
		} else {
			throw new HttpMessageNotReadableException(
					"you cant update this credentials. you dont have authenticate",
					new Throwable("you cant update this credentials,  you dont have authenticate"));
		}

	}
}
