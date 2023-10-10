package com.flight.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flight.entity.customer;

import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;

public interface customer_repo extends JpaRepository<customer, Long> {

	
	@Modifying
	@Transactional
	@Query(value = "update customer set statuspayment = 'yes'  where id = :data ", nativeQuery = true)
	public void update_status(@Param(value = "data") Long id);
	
	@Query(value = "select * from customer WHERE parent_id = :idbill ", nativeQuery = true)
	Optional<List<customer>> findallbyidbill(@Param(value = "idbill") Long idbil);
	
}
