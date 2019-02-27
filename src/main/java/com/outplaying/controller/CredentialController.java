package com.outplaying.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.outplaying.dto.CredentialDTO;
import com.outplaying.model.Credential;
import com.outplaying.service.ICredentialService;

@RestController
@RequestMapping("api/v1/credential")
public class CredentialController {

	@Autowired
	private ICredentialService credentialService; 

	@PostMapping(value = "/sign-up")
	public CredentialDTO addCredential(@RequestBody CredentialDTO credentialDTO) {
		return credentialService.addCredential(credentialDTO);
	}

	@GetMapping(value = "", produces = "application/json")
	public List<Credential> getAll() {
		return credentialService.getAll();
	}

	@GetMapping(value = "/{idCredential}")
	public CredentialDTO findById(@PathVariable Long idCredential) {
		return credentialService.findCredentialById(idCredential);
	}

	@PutMapping(value = "")
	public CredentialDTO updateCredential(@RequestBody CredentialDTO credentialDTO) {
		return credentialService.updateCredential(credentialDTO);
	}
	
	@DeleteMapping(value = "{idCredential}")
	public Integer deleteCredential(@PathVariable Long idCredential) {
		return credentialService.deleteById(idCredential);
	}

}
