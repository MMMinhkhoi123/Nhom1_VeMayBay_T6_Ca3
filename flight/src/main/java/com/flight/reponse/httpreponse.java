package com.flight.reponse;

import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(content = Include.NON_DEFAULT)
public class httpreponse {
	protected String timeStamp;
	protected int statuscode;
	protected HttpStatus status;
	protected String message;
	protected Map<?,?> data;
}
