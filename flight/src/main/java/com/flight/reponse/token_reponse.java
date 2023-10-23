package com.flight.reponse;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class token_reponse {
	private String Token;
	private List<String> role;
	private String fullname;
	private String phone;
	private String email;
}
