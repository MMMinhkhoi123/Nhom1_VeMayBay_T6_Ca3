package com.flight.reponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class bill_reponse {
	private int sumprice;
	private long date;
	private long date_pay;
	private long id;
	private long iduser;
	private String codepay;
	private String status;
}
