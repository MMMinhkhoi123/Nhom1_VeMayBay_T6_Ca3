package com.flight.reponse;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class user_reponse {
	private Long id;
	private String email;
	private String name;
	private String JsonToken;
	private List<String> role;
}
