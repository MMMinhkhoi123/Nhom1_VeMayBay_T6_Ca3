package com.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flight.entity.ticker;

import jakarta.transaction.Transactional;

public interface ticker_repo extends JpaRepository<ticker, Long> {

	@Modifying
	@Transactional
	@Query(value = "delete from ticker where flight_id = :ids", nativeQuery = true)
	public void deletebyidflight(@Param(value = "ids") Long idflight);
	
	@Query(value = "select * from ticker where flight_id = :ids", nativeQuery = true)
	public ticker findbyidflight(@Param(value = "ids") Long idflight);
	
	
	@Modifying
	@Transactional
	@Query(value = "update ticker set quality = quality - 1  where id = :data ", nativeQuery = true)
	public void update_status(@Param(value = "data") Long id);
}
