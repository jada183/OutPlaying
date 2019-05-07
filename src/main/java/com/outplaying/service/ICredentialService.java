package com.outplaying.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.outplaying.dto.CredentialDTO;
@Service
public interface ICredentialService {

	public CredentialDTO findCredentialById(Long idCredential);

	public CredentialDTO addCredential(CredentialDTO credentialDTO);

	public List<CredentialDTO> getAll();

	public CredentialDTO updateCredential(CredentialDTO credentialDTO);
	
	public UserDetails loadUserByUsername(String username);
}
