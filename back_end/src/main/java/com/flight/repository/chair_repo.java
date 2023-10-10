package com.flight.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flight.entity.chair;
import com.flight.request.orther.sizetypechair;

import jakarta.transaction.Transactional;


public interface chair_repo extends JpaRepository<chair, Long> {

	@Modifying
	@Transactional
	@Query(value = "delete from chair where airplane_id = :data", nativeQuery = true)
	public void  deletebyidplane(@Param(value = "data")Long id);
	
	@Query(value = "select * from chair where airplane_id = :data", nativeQuery = true)
	Optional<List<chair>> findbyidairplane(@Param(value = "data")Long idairplane);
	
	@Query(value = "select * from chair where airplane_id = :data and typechair_id = :data2", nativeQuery = true)
	Optional<List<chair>> findchairsize(@Param(value = "data")Long idairplane, @Param(value = "data2") Long id);
	
	@Modifying
	@Transactional
	@Query(value = "update chair set status = 'full' where id = :data ", nativeQuery = true)
	public void update_status(@Param(value = "data") Long id);
	
	@Modifying
	@Transactional
	@Query(value = "update chair set status = 'empty' where id = :data ", nativeQuery = true)
	public void update_status2(@Param(value = "data") Long id);

}
