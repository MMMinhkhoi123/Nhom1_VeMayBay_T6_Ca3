package com.flight.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class verify_request {
	private String code;
	private String email;
}
