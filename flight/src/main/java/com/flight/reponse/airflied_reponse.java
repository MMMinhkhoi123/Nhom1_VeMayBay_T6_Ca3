package com.flight.reponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class airflied_reponse extends common_mess_status {
	private String mess;
	private int status;
}
