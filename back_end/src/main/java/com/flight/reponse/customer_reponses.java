package com.flight.reponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class customer_reponses {
	private Long id;
	private String fullname;
	private String email;
	private String phone;
	private String age;
	private String nationality;
	private String passport;
	private int pricechair;
	private int pricetick;
	private String namelocation_departure;
	private String namelocation_complete;
	private Long date_departure;
	private String status;
	private Long idticker;
	private Long idflight;
	private Long idchair;
	private Long idbill;
}
