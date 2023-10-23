package com.flight.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flight.entity.role;
import com.flight.entity.user;

import jakarta.transaction.Transactional;

public interface user_repo extends JpaRepository<user, Long> {

	
	@Query(value = "select * from user where email = :emails", nativeQuery = true)
	Optional<user> findonebynemail(@Param(value = "emails")String email);
	
	@Modifying
	@Transactional
	@Query(value = "update user set password = :pass where email = :emails ", nativeQuery = true)
	public void update_pass(@Param(value = "pass") String pass ,@Param(value = "emails") String email);

}
