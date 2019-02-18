package com.outplaying.service;

import java.util.List;

import com.outplaying.dto.CredentialDTO;

public interface ICredentialService {

	public CredentialDTO findCredentialById(Long idCredential);

	public CredentialDTO addCredential(CredentialDTO credentialDTO);

	public List<CredentialDTO> getAll();

	public CredentialDTO updateCredential(CredentialDTO credentialDTO);

	public Integer deleteById(Long idCredential);
}
