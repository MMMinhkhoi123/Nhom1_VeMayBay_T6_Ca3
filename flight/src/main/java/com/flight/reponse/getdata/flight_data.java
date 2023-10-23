package com.flight.reponse.getdata;

import java.util.List;

import com.flight.request.flight_request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class flight_data extends base_page {

	private List<flight_request> list;
}
