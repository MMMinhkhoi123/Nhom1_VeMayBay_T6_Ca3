package com.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.entity.location;

public interface location_repo extends JpaRepository<location, Long>{
	
}
