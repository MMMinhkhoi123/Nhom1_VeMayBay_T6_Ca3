package com.flight.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class airfield_request {

	private String  name;
	private String adrress;
	private String contry;
	private Long id;
	
}
