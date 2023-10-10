package com.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.entity.auth_jwt;

public interface jwt_repo extends JpaRepository<auth_jwt, Long> {

}
