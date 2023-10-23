package com.flight.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.flight.entity.trademark;
import com.flight.reponse.trademark_reponse;
import com.flight.reponse.getdata.trademark_data;
import com.flight.repository.flight_repo;
import com.flight.repository.trademark_reponsetory;
import com.flight.request.trademark_request;
import com.flight.service.I_trademark;

@Service
public class trademark_service implements I_trademark {

	final static String link_save_img = "C:\\Users\\Admin\\Downloads\\CNPMNC_TH\\flight\\src\\main\\resources\\static\\img\\trademark\\";
	
	@Autowired
	trademark_reponsetory trademark_reponsesitory;

	@Override
	public ResponseEntity<trademark_reponse> save(trademark_request trademarks, MultipartFile file) {
		String mess_save = "";
		int status_save = 0;
		try {
			trademark data = new trademark(trademarks.getName(),trademarks.getImg());
		if (file != null) {
			 mess_save = "Tạo thành công";
			 if (trademarks.getId() != 0) {
				 mess_save = "Cập nhật thành công";
				 status_save = 1;
				 data.setId(trademarks.getId());
				 trademark origin = trademark_reponsesitory.findById(trademarks.getId()).get();
				 Path paths2 = Paths.get(link_save_img + trademarks.getId().toString() + "&3&" + origin.getImg());
				 Files.delete(paths2); 
			 }
			 trademark dataxx = trademark_reponsesitory.save(data);
			 Path paths = Paths.get(link_save_img + dataxx.getId().toString() + "&3&" + trademarks.getImg());
			 String h = dataxx.getId().toString() + "&3&" + trademarks.getImg();
			 Files.copy(file.getInputStream(),paths,StandardCopyOption.REPLACE_EXISTING);
		} else {
			data.setId(trademarks.getId());
			 status_save = 1;
			 mess_save = "Cập nhật thành công";
			 trademark_reponsesitory.save(data);
		 }
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<> (trademark_reponse.builder()
				.mess(mess_save)
				.status(status_save).build()
				,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<trademark_reponse> remove(Long id) {
	 	Path paths = Paths.get(link_save_img +  id.toString() +"&3&"+ trademark_reponsesitory.findById(id).get().getImg());
		try { 
			trademark_reponsesitory.deleteById(id);
		    Files.deleteIfExists(paths);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return new ResponseEntity<> 
		(trademark_reponse.builder()
				.mess("Xóa Thành công")
				.status(2).build(),HttpStatus.OK);
	}


	public String convertUpcase(String data) {
		return data.toUpperCase();
	}
	
	@Override
	public ResponseEntity<trademark_data> get(int page, int limit, String key) {
		trademark_data result = new trademark_data();
		result.setPage(page);
		Pageable data = new PageRequest(page -1,limit,Sort.by("id")) {
		};
		List<trademark_request> data_array = new ArrayList<trademark_request>();
		trademark_reponsesitory.findAll(data).getContent().forEach((e) -> {
			if(!key.equalsIgnoreCase("")) {
				if(convertUpcase(e.getName()).contains(convertUpcase(key))) {
					data_array.add(trademark_request.builder()
							.name(e.getName())
							.id(e.getId())
							.img(e.getImg())
							.build());
				}
			} else {
				data_array.add(trademark_request.builder()
						.name(e.getName())
						.id(e.getId())
						.img(e.getImg())
						.build());
			}
		});
		result.setList(data_array);
		result.setToltalpage((int) Math.ceil((double) (trademark_reponsesitory.count()) / limit));
		return new ResponseEntity<>(result,HttpStatus.OK);
	}

	
	
	int i = 0;
	int i2 = 0;
	@Override
	public ResponseEntity<List<trademark_request>> get() {
		List<trademark_request> data_array = new ArrayList<trademark_request>();
		trademark_reponsesitory.findAll().forEach((e) -> {
			i = 0;
			i2 = 0;
			e.getAirplane().forEach((x) -> {
				i  = i + getcount(x.getId());
				i2 = i2 + getcountbuy(x.getId());
			});
			data_array.add(trademark_request.builder()
					.name(e.getName())
					.id(e.getId())
					.img(e.getImg())
					.count(i)
					.buy(i2)
					.build());
		});
		return new ResponseEntity<>(data_array,HttpStatus.OK);
	}
	 int x = 0;
	 int x1 = 0;
	@Autowired
	flight_repo flightrepo;
   public int getcount(long idairplane) {
	   x = 0;
	   x1 = 0;
		flightrepo.findbyidairplane(idairplane).forEach((e) -> {
		   x = x + e.getTickers().getQuality();
		   x1 = x1 + e.getTickers().getBuy();
		});
	  return x;
	}
   
   public int getcountbuy(long idairplane) {
	   x1 = 0;
		flightrepo.findbyidairplane(idairplane).forEach((e) -> {
		   x1 = x1 + e.getTickers().getBuy();
		});
	  return x1;
	}
	
	
}
