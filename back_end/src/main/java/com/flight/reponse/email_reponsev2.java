package com.flight.reponse;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class email_reponsev2 {
	private Long code;
	private int sumprice;
	private List<email_reponse> list;
	private String dateby;
	private String namedata;
}
