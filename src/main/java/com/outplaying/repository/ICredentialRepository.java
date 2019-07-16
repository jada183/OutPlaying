package com.outplaying.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.outplaying.model.Credential;
import com.outplaying.model.User;


@Repository
public interface ICredentialRepository extends JpaRepository<Credential,Long> {
	@Transactional
	public Integer removeByIdCredential(Long idCredential);
	
	public Credential findByusername(String username);
	
	@Transactional
	@Query("SELECT c  FROM Credential c WHERE c.user = ?1")
	public Credential credentialByIdUSer(User user);
}
