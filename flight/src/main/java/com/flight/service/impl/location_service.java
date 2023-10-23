package com.flight.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.data.domain.Sort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.flight.entity.location;
import com.flight.reponse.location_reponse;
import com.flight.reponse.getdata.location_data;
import com.flight.repository.location_repo;
import com.flight.request.location_request;
import com.flight.service.I_location;
import org.springframework.data.domain.Pageable;

@Service
public class location_service implements I_location {

	
	final static String link_save_img = "C:\\Users\\Admin\\Downloads\\CNPMNC_TH\\flight\\src\\main\\resources\\static\\img\\location\\";
	final static String key = "img3";
	
	@Autowired
	location_repo location_reponsetory;
	
	@Override
	public ResponseEntity<location_reponse> save(location_request locattionx, MultipartFile file){
		String mess_save = "";
		int status_save = 0;
		try {
			location data = new location(locattionx.getName(),locattionx.getImg(),locattionx.getContry());
			if (file != null) {
				 mess_save = "Tạo thành công";
				 if (locattionx.getId() != 0) {
					 mess_save = "Cập nhật thành công";
					 status_save = 1;
					 data.setId(locattionx.getId());
						location origin = location_reponsetory.findById(locattionx.getId()).get();
					 Path paths2 = Paths.get(link_save_img + locattionx.getId().toString() + "&3&" + origin.getImg());
					 Files.delete(paths2); 
				 }
				 location dataxx = location_reponsetory.save(data);
				 Path paths = Paths.get(link_save_img + dataxx.getId().toString() + "&3&" + locattionx.getImg());
				 String h = dataxx.getId().toString() + "&3&" + locattionx.getImg();
				 Files.copy(file.getInputStream(),paths,StandardCopyOption.REPLACE_EXISTING);
			 } else {
				 data.setId(locattionx.getId());
				 status_save = 1;
				 mess_save = "Cập nhật thành công";
				 location_reponsetory.save(data);
			 }
			} catch (IOException e) {
				e.printStackTrace();
			}
		return new ResponseEntity<> 
		(location_reponse.builder()
				.mess(mess_save)
				.status(status_save).build()
				,HttpStatus.OK);
	}
	
	
	@Autowired
	file_service filesrevice;
	@Override
	public ResponseEntity<location_reponse> remove(Long id) {
		 	Path paths = Paths.get(link_save_img + location_reponsetory.findById(id).get().getImg());
			try {
				location_reponsetory.deleteById(id);
			    Files.deleteIfExists(paths);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		return new ResponseEntity<> 
		(location_reponse.builder()
				.mess("Xóa Thành công")
				.status(2).build(),HttpStatus.OK);
	}

	
	public String convertUpcase(String data) {
		return data.toUpperCase();
	}
	
	@Override
	public ResponseEntity<location_data> get(int page, int limit, String key) {
		
		List<location_request> data_array = new ArrayList<location_request>();

		Pageable xx = new PageRequest(page -1,limit,Sort.by("id")) {
		};
		location_reponsetory.findAll(xx).forEach((e) -> {	
			if (!key.equalsIgnoreCase("")) {
				if(convertUpcase(e.getName()).contains(convertUpcase(key))) {
				  data_array.add(location_request.builder().id(e.getId()).img(e.getImg()).name(e.getName()).contry(e.getName_contry()).build());
				}
			} else {
				data_array.add(location_request.builder().id(e.getId()).img(e.getImg()).name(e.getName()).contry(e.getName_contry()).build());
			}
		});
		return new ResponseEntity<>(location_data.builder()
				.list(data_array)
				.toltalpage((int) Math.ceil((double) (location_reponsetory.count()) / limit))
				.page(page)
				.build(),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<location_request>> get() {
		List<location_request> data_array = new ArrayList<location_request>();
		location_reponsetory.findAll().forEach((e) -> {
					data_array.add(location_request.builder()
							.contry(e.getName_contry())
							.img(e.getImg())
							.id(e.getId())
							.name(e.getName())
							.build());

		});
		return new ResponseEntity<>(data_array,HttpStatus.OK);
	}




	
}
