package com.flight.repository;

import java.util.ArrayList;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flight.entity.role;

public interface role_repo extends JpaRepository<role, Long> {

	@Query(value = "SELECT * FROM role us JOIN users_roles ur ON us.id = ur.role_id AND ur.user_id = :iduser", nativeQuery = true)
	ArrayList<role> findallbyid(@Param(value = "iduser") Long iduser);
}
