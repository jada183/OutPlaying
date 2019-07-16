package com.outplaying.dto;

public class RegisterUserDTO {
	private UserDTO userDTO;
	private CredentialDTO credentialDTO;

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public CredentialDTO getCredentialDTO() {
		return credentialDTO;
	}

	public void setCredentialDTO(CredentialDTO credentialDTO) {
		this.credentialDTO = credentialDTO;
	}

}
