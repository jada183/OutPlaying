package com.outplaying.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.outplaying.model.User;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {

	@Transactional
	public Integer removeByIdUser(Long idUser);
}



