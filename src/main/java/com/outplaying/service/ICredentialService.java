package com.outplaying.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.outplaying.dto.CredentialDTO;
import com.outplaying.model.Credential;

public interface ICredentialService {

	public CredentialDTO findCredentialById(Long idCredential);

	public CredentialDTO addCredential(CredentialDTO credentialDTO);

	public List<Credential> getAll();

	public CredentialDTO updateCredential(CredentialDTO credentialDTO);

	public Integer deleteById(Long idCredential);
	
	public UserDetails loadUserByUsername(String username);
}
