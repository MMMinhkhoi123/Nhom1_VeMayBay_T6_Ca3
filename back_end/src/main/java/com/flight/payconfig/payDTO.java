package com.flight.payconfig;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class payDTO implements Serializable{
	private String status;
	private String mess;
	private String url;

}
