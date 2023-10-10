package com.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.entity.flight;

public interface flight_repo extends JpaRepository<flight, Long> {

}
