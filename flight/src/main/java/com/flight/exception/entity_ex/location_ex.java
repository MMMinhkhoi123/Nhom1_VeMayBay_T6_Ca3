package com.flight.exception.entity_ex;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class location_ex extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NonNull
	private String mess;
}
