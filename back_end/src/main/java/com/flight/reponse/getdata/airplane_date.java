package com.flight.reponse.getdata;

import java.util.List;
import java.util.Optional;

import com.flight.request.orther.sizetypechair;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class airplane_date{
	private String name;
	private String name_trademark;
	private Long id_trademark;
	private  List<chair_data> array_list;
	private int empty;
	private int full;
	private int size;
	private String status;
	private Long id;
	private List<sizetypechair> typechair;	
}
