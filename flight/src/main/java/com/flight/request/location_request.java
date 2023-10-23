package com.flight.request;

import java.io.File;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class location_request {

	private String name;
	private String img;
	private String contry;
	private Long id;
}
