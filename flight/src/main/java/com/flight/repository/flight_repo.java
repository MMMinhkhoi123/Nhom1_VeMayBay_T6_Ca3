package com.flight.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flight.entity.flight;

import jakarta.transaction.Transactional;

public interface flight_repo extends JpaRepository<flight, Long> {

	@Query(value = "select * from flight WHERE airplane_id = :idairplane ", nativeQuery = true)
	public List<flight> findbyidairplane(@Param(value = "idairplane") Long idairplane);
}
