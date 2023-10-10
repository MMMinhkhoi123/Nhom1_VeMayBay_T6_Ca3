package com.flight.reponse;

import com.flight.request.customer_request;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class einvoice_reponse {

	private int price;
	private Long id;
	private Long datecreate;
	private customer_request customer;
	private chair_reponse chair;
	private tick_reponse tick;
	
}
