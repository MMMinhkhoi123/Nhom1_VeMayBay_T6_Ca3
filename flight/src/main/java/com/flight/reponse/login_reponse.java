package com.flight.reponse;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class login_reponse {
	private String accessToken;
	private List<String> name_role;
	private String mess;
}
