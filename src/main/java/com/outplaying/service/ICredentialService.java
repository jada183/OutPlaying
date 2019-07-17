package com.outplaying.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.outplaying.dto.CredentialDTO;
import com.outplaying.dto.UpdatePasswordDTO;
@Service
public interface ICredentialService {

	public CredentialDTO findCredentialById(Long idCredential);

	public CredentialDTO addCredential(CredentialDTO credentialDTO);

	public List<CredentialDTO> getAll();
	
	public UserDetails loadUserByUsername(String username);
	
	public CredentialDTO updatePassword(UpdatePasswordDTO updatePasswordDTO);
}
