package com.flight.reponse.getdata;

import java.util.List;

import com.flight.request.trademark_request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class trademark_data extends base_page {
	private List<trademark_request> list;
}

