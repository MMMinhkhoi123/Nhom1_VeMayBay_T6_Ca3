package com.flight.request;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class file_request {

	private MultipartFile img_file;
}
