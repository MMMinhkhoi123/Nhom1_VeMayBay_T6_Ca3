package com.flight.reponse;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class bill_parent_reponse {
	public int toltalpage;
	public int page;
	public List<bill_reponse> list;
}
