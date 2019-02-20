package com.outplaying.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.outplaying.model.Credential;

@Repository
public interface ICredentialRepository extends JpaRepository<Credential,Long> {
	@Transactional
	public Integer removeByIdCredential(Long idCredential);
}
