package com.flight.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight.service.impl.file_service;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/img")
@CrossOrigin
public class file {
	
	@Autowired
	file_service filesrevice;
	
	@GetMapping( value = "/location/{imgname}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void imglocation(HttpServletResponse response, @PathVariable("imgname") Optional<String> name) throws IOException {
		 InputStream resoures = filesrevice.getresoure("C:\\Users\\Admin\\Downloads\\CNPMNC_TH\\flight\\src\\main\\resources\\static\\img\\location\\", name.get());
		 response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		 StreamUtils.copy(resoures, response.getOutputStream());
		 resoures.close();
	}
	@GetMapping( value = "/trademark/{imgname}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void imgtrademark(HttpServletResponse response, @PathVariable("imgname") Optional<String> name) throws IOException {
		 InputStream resoures = filesrevice.getresoure("C:\\Users\\Admin\\Downloads\\CNPMNC_TH\\flight\\src\\main\\resources\\static\\img\\trademark\\", name.get());
		 response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		 StreamUtils.copy(resoures, response.getOutputStream());
		 resoures.close();
	}

}
