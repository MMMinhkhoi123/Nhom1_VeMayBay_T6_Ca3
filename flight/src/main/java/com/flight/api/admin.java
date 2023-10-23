package com.flight.api;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.flight.reponse.airflied_reponse;
import com.flight.reponse.airplane_reponse;
import com.flight.reponse.bill_parent_reponse;
import com.flight.reponse.bill_reponse;
import com.flight.reponse.flight_reponse;
import com.flight.reponse.location_reponse;
import com.flight.reponse.trademark_reponse;
import com.flight.reponse.type_chair_reponse;
import com.flight.reponse.getdata.airfield_data;
import com.flight.reponse.getdata.airplane_datav;
import com.flight.reponse.getdata.airplane_date;
import com.flight.reponse.getdata.customer_data;
import com.flight.reponse.getdata.flight_data;
import com.flight.reponse.getdata.location_data;
import com.flight.reponse.getdata.tick_datav;
import com.flight.reponse.getdata.ticker_data;
import com.flight.reponse.getdata.trademark_data;
import com.flight.reponse.getdata.typechair_data;
import com.flight.repository.customer_repo;
import com.flight.request.airfield_request;
import com.flight.request.airplane_request;
import com.flight.request.file_request;
import com.flight.request.flight_request;
import com.flight.request.location_request;
import com.flight.request.trademark_request;
import com.flight.request.type_chair_request;
import com.flight.service.impl.airfield_service;
import com.flight.service.impl.airplane_service;
import com.flight.service.impl.customer_service;
import com.flight.service.impl.file_service;
import com.flight.service.impl.fligth_service;
import com.flight.service.impl.location_service;
import com.flight.service.impl.parent_service;
import com.flight.service.impl.ticker_service;
import com.flight.service.impl.trademark_service;
import com.flight.service.impl.type_chair_service;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class admin {
	
	@Autowired 
	location_service locationservice;
	
	@Autowired
	trademark_service trademarkservice;
	
	@Autowired
	airfield_service airfieldservice;
	
	@Autowired
	type_chair_service typechairsrevice;
	
	@Autowired
	airplane_service airplaneservice;
	
	@Autowired 
	fligth_service flightservice;
	
	@Autowired
	ticker_service tickservice;
	
	@PostMapping("/location")
	public ResponseEntity<location_reponse> create_file(
			@RequestParam("file_location") Optional<MultipartFile> file_img,
			@RequestParam("name") Optional<String> name,
			@RequestParam("contry") Optional<String> contry,
			@RequestParam("img") Optional<String> img,
			@RequestParam("id") Optional<Long> id) {
	return locationservice.save(location_request.builder()
			.contry(contry.get())
			.name(name.get())
			.img(img.get())
			.id(id.get())
			.build(),
			file_img.get());
	}
	
	@PutMapping("/location")
	public ResponseEntity<location_reponse> update_file(
			@RequestParam("name") Optional<String> name,
			@RequestParam("contry") Optional<String> contry,
			@RequestParam("img") Optional<String> img,
			@RequestParam("id") Optional<Long> id) {
	return locationservice.save(location_request.builder()
			.contry(contry.get())
			.name(name.get())
			.img(img.get())
			.id(id.get())
			.build() ,null);
	}
	
	
	@GetMapping("/location")
	public ResponseEntity<location_data> getlocation_location(
			@RequestParam("limit") Optional<Integer> limit,
			@RequestParam("page")Optional<Integer> page,
			@RequestParam("key") Optional<String> key) {
		return locationservice.get(page.get(), limit.get(), key.get());
	}
	
	@GetMapping("/location/all")
	public ResponseEntity<List<location_request>> getlocation_all_location() {
		return locationservice.get();
	}
	
	@DeleteMapping("/location/{id}")
	public ResponseEntity<location_reponse> delete_location(
			@PathVariable(value = "id",required = true) Optional<Long> idlocation) {
		return locationservice.remove(idlocation.get());
	}
	
	//trademark
	
	@PostMapping("/trademark")
	public ResponseEntity<trademark_reponse> create_trademark(
			@RequestParam("file_trademark") Optional<MultipartFile> file_img,
			@RequestParam("name") Optional<String> name,
			@RequestParam("img") Optional<String> img,
			@RequestParam("id") Optional<Long> id) {
		return trademarkservice.save(trademark_request.builder()
				.name(name.get())
				.img(img.get())
				.id(id.get())
				.build(), file_img.get());
	}
	
	@PutMapping("/trademark")
	public ResponseEntity<trademark_reponse> update_trademark(
			@RequestParam("name") Optional<String> name,
			@RequestParam("img") Optional<String> img,
			@RequestParam("id") Optional<Long> id) {
		return trademarkservice.save(trademark_request.builder()
				.name(name.get())
				.img(img.get())
				.id(id.get())
				.build(), null);
	}
	
	
	@DeleteMapping("/trademark/{id}")
	public ResponseEntity<trademark_reponse> delete_trademark(@PathVariable(value = "id",required = true) Optional<Long> trademark) {
		return trademarkservice.remove(trademark.get());
	}
	@GetMapping("/trademark")
	public ResponseEntity<trademark_data> get_trademark(@RequestParam("limit") Optional<Integer> limit,
			@RequestParam("page")Optional<Integer> page,
			@RequestParam("key") Optional<String> key) {
		return trademarkservice.get(page.get(),limit.get(),key.get());
	}
	
	@GetMapping("/trademark/all")
	public ResponseEntity<List<trademark_request>> get_trademark() {
		return trademarkservice.get();
	}
	
	
	@PostMapping("/airfield")
	public ResponseEntity<airflied_reponse> create_airfield(@RequestBody Optional<airfield_request> airfield) {
		return airfieldservice.save(airfield.get());
	}
	
	@DeleteMapping("/airfield/{id}")
	public ResponseEntity<airflied_reponse> delete_airfield(
			@PathVariable(value = "id",required = true) Optional<Long> idlocation) {
		return airfieldservice.remove(idlocation.get());
	}
	
	@GetMapping("/airfield")
	public ResponseEntity<airfield_data> get_airfield(@RequestParam("limit") Optional<Integer> limit,
			@RequestParam("page")Optional<Integer> page,
			@RequestParam("key") Optional<String> key) {
		return airfieldservice.get(page.get(),limit.get(),key.get());
	}
	
	@GetMapping("/airfield/all")
	public ResponseEntity<List<airfield_request>> get_all_airfield() {
		return airfieldservice.get();
	}
	//typechair
	
	@PostMapping("/type-chair")
	public ResponseEntity<type_chair_reponse> create_type_chair(@RequestBody Optional<type_chair_request> type_chair) {
		return typechairsrevice.save(type_chair.get());
	}
	
	@GetMapping("/type-chair")
	public ResponseEntity<List<typechair_data>> get_type_chair() {
		return typechairsrevice.get();
	}
	
	@DeleteMapping("/type-chair/{id}")
	public ResponseEntity<type_chair_reponse> delete_type_chair(@PathVariable(value = "id", required = true) Optional<Long> type_chair) {
		return typechairsrevice.remove(type_chair.get());
	}
	//airplane
	@PostMapping("/airplane")
	public ResponseEntity<airplane_reponse> create_airplane(@RequestBody Optional<airplane_request> air_plane) {
		return airplaneservice.save(air_plane.get());
	}
	@GetMapping("/airplane")
	public ResponseEntity<airplane_datav> get_airplane(@RequestParam("limit") Optional<Integer> limit,
			@RequestParam("page")Optional<Integer> page,
			@RequestParam("key") Optional<String> key) {
		return airplaneservice.get(page.get(),limit.get(),key.get());
	}
	@GetMapping("/airplane/all")
	public ResponseEntity<List<airplane_date>> get_all_airplane() {
		return airplaneservice.get();
	}
	@PutMapping("/airplane")
	public ResponseEntity<airplane_reponse> update_airplane(@RequestBody Optional<airplane_request> air_plane) {
		return airplaneservice.update(air_plane.get());
	}
	@DeleteMapping("/airplane/{id}")
	public ResponseEntity<airplane_reponse> delete_airplane(@PathVariable(value = "id") Optional<Long> air_plane) {
		return airplaneservice.remove(air_plane.get());
	}
	
	//filght
	@PostMapping("/flight")
	public ResponseEntity<flight_reponse> create_flight(@RequestBody Optional<flight_request> flight) {
		return  flightservice.save(flight.get());
	}
	@PutMapping("/flight")
	public ResponseEntity<flight_reponse> update_flight(@RequestBody Optional<flight_request> flight) {
		return flightservice.update(flight.get());
	}
	@GetMapping("/flight")
	public ResponseEntity<flight_data> get_flight(@RequestParam("limit") Optional<Integer> limit,
			@RequestParam("page")Optional<Integer> page) {
		return  flightservice.get(page.get(),limit.get());
	}
	
	@Autowired
	customer_service customerservice;
	// customer
	@GetMapping("/customer")
	public ResponseEntity<customer_data> get_customer(@RequestParam("limit") Optional<Integer> limit,
			@RequestParam("page")Optional<Integer> page,@RequestParam("key") Optional<String> key) {
		return customerservice.gets(page.get(), limit.get(), key.get());
	}
	@GetMapping("/flight/all")
	public ResponseEntity<List<flight_request>> get_all_flight() {
		return flightservice.get();
	}
	
	@DeleteMapping("/flight/{id}")
	public ResponseEntity<String> delete_flight(@PathVariable(value = "id")Optional<Long> id) {
		flightservice.delete(id.get());
		return new ResponseEntity<>("delete success",HttpStatus.OK);
	}
	
	//ticker
	@GetMapping("/tick/all")
	public ResponseEntity<List<ticker_data>> get_ticker() {
		return tickservice.get();
	}
	@GetMapping("/tick")
	public ResponseEntity<tick_datav> get(@RequestParam("limit") Optional<Integer> limit,
			@RequestParam("page")Optional<Integer> page) {
		return tickservice.get(page.get(),limit.get());
	}
	
	@Autowired
	parent_service parentservice;
	@GetMapping("/prbill")
	public ResponseEntity<bill_parent_reponse> bills(@RequestParam("limit") Optional<Integer> limit,
			@RequestParam("page")Optional<Integer> page,@RequestParam("key") Optional<String> key) {
		return parentservice.get(page.get(), limit.get(), key.get());
	}
	
	@GetMapping("/prbill-all")
	public ResponseEntity<List<bill_reponse>> bills_all() {
		return parentservice.get();
	}
}
