package com.flight.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.flight.service.I_file;

@Service
public class file_service implements I_file {

	@Override
	public InputStream getresoure(String path, String filename) {
		String fullpath = path + File.separator + filename;
		try {
			InputStream is = new FileInputStream(fullpath);
			return is;
		} catch (FileNotFoundException e) {
			return null;
		}
	}

}
