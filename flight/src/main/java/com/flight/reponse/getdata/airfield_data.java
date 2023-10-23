package com.flight.reponse.getdata;

import java.util.List;

import com.flight.request.airfield_request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class airfield_data extends base_page{

	public List<airfield_request> list;
}
