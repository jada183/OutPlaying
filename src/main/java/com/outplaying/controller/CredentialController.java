package com.outplaying.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.outplaying.dto.CredentialDTO;
import com.outplaying.service.ICredentialService;

@RestController
@RequestMapping("api/v1/credential")
public class CredentialController {
	
	@Autowired
	private ICredentialService credentialService;
	
	@PostMapping(value="", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CredentialDTO addCredential(@RequestBody CredentialDTO credentialDTO) {
		System.out.println("punto de interrupcion"+ credentialDTO.getUser().getIdUser());
		return credentialService.addCredential(credentialDTO);
	}
	@GetMapping(value="",  produces = "application/json")
	public List<CredentialDTO> getAll() {
		return credentialService.getAll();
	}
}
