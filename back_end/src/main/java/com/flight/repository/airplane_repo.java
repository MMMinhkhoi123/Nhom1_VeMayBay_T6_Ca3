package com.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flight.entity.airplane;

import jakarta.transaction.Transactional;

public interface airplane_repo extends JpaRepository<airplane, Long> {

	@Modifying
	@Transactional
	@Query(value = "update airplane set status = :value where id = :idairplane ", nativeQuery = true)
	public void update_action(@Param(value = "value") String value, @Param(value="idairplane") Long id_airplane);
}
