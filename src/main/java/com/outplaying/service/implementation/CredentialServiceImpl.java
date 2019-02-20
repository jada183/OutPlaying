package com.outplaying.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outplaying.dto.CredentialDTO;
import com.outplaying.model.Credential;
import com.outplaying.repository.ICredentialRepository;
import com.outplaying.service.ICredentialService;

@Service
public class CredentialServiceImpl implements ICredentialService {

	ModelMapper modelMapper = new ModelMapper();
	@Autowired
	private ICredentialRepository credentialRepository;

	@Override
	public CredentialDTO findCredentialById(Long idCredential) {

		return modelMapper.map(credentialRepository.getOne(idCredential), CredentialDTO.class);
	}

	@Override
	public CredentialDTO addCredential(CredentialDTO credentialDTO) {
		Credential credential = modelMapper.map(credentialDTO, Credential.class);
		return modelMapper.map(credentialRepository.save(credential), CredentialDTO.class);
	}

	@Override
	public List<CredentialDTO> getAll() {
		List<CredentialDTO> listCredentialDTO = new ArrayList<CredentialDTO>();
		List<Credential> credentialList = new ArrayList<Credential>();
		credentialList = credentialRepository.findAll();
		System.out.println(credentialList.get(0).getUsername());

		for (Credential credential : credentialList) {
			listCredentialDTO.add(modelMapper.map(credential, CredentialDTO.class));
		}
		return listCredentialDTO;

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

}
