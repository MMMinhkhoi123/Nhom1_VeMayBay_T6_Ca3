package com.flight.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flight.entity.location;

public interface location_repo extends JpaRepository<location, Long>{
	
	@Query(value = "select * from location where name = :name and contry = :contry ",nativeQuery = true)
	public Optional<location> findonename(@Param(value = "name") String name, @Param(value = "contry") String contry);
	
}
