package com.flight.reponse.getdata;

import java.util.List;

import com.flight.request.location_request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class location_data  extends base_page{
	public List<location_request> list;
}
