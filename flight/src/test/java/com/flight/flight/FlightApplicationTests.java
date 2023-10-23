package com.flight.flight;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import com.flight.api.admin;
import com.flight.entity.location;
import com.flight.service.impl.location_service;

@WebMvcTest(value = admin.class)
class FlightApplicationTests {


	@Autowired
	private MockMvc mvc;
	
	@TestConfiguration
	public static class test {
		@Bean
		location_service location_service() {
			return new location_service();
		}
	}
	


}
