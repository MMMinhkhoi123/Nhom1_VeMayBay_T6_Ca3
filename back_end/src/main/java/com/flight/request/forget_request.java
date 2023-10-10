package com.flight.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class forget_request {
	private String email;
	private String mess;
	private boolean status;
	private String code;
}
