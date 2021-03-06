package com.outplaying.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.outplaying.dto.CredentialDTO;
import com.outplaying.dto.UpdatePasswordDTO;
import com.outplaying.service.ICredentialService;

@RestController
@RequestMapping("credentials")
public class CredentialController {

	@Autowired
	private ICredentialService credentialService;


	@GetMapping(value = "", produces = "application/json")
	public List<CredentialDTO> getAll() {
		return credentialService.getAll();
	}

	@GetMapping(value = "/{idCredential}")
	public CredentialDTO findById(@PathVariable Long idCredential) {
		return credentialService.findCredentialById(idCredential);
	}
	// sin uso solo para casos de prueba
	@PostMapping(value = "")
	public CredentialDTO addCredential(@RequestBody CredentialDTO credentialDTO) {
		return credentialService.addCredential(credentialDTO);
	}

	@PutMapping(value = "/password")
	public CredentialDTO updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO)  {
		return credentialService.updatePassword(updatePasswordDTO);
	}

}
