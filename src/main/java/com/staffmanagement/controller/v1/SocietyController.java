package com.staffmanagement.controller.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.staffmanagement.dto.CustomerDTO;
import com.staffmanagement.dto.SocietyDTO;
import com.staffmanagement.service.v1.SocietyService;

@RestController
@RequestMapping(value = "v1/society")
public class SocietyController {

	@Autowired
	private SocietyService societyService;

	@PostMapping
	public ResponseEntity<SocietyDTO> createSociety(@RequestBody SocietyDTO societydto) {
		return new ResponseEntity<>(societyService.addSociety(societydto), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SocietyDTO> updateSociety(@RequestBody SocietyDTO societydto, @PathVariable("id") Long id) {
		return new ResponseEntity<>(societyService.update(societydto, id), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SocietyDTO> getSocietyById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(societyService.getSocietyById(id), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<SocietyDTO>> getAllSocieties() {
		return new ResponseEntity<>(societyService.getAllSocieties(), HttpStatus.OK);
	}

	@GetMapping("/{id}/customers")
	public ResponseEntity<List<CustomerDTO>> getCustomers(@PathVariable("id") Long societyId) {
		return new ResponseEntity<>(societyService.getCustomersBySocietyId(societyId), HttpStatus.OK);
	}

	@GetMapping("/count")
	public ResponseEntity<Long> getSocietyCount() {
		return new ResponseEntity<>(societyService.count(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSociety(@PathVariable("id") Long id) {
		societyService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}