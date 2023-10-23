package com.flight.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class trademark_request {

	private Long id;
	private String name;
	private String img;
	private int count;
	private int buy;
	
}
