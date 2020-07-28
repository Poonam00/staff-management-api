package com.staffmanagement.controller.v1;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.staffmanagement.dto.CustomerDTO;
import com.staffmanagement.dto.SocietyDTO;
import com.staffmanagement.entity.Customer;
import com.staffmanagement.entity.Society;
import com.staffmanagement.service.v1.SocietyService;

@RestController
@RequestMapping(value = "v1/society")
public class SocietyController {

	@Autowired
	SocietyService societyService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<SocietyDTO> createSociety(@RequestBody SocietyDTO societyDTO) {
		societyDTO.setId(null);
		Society society = modelMapper.map(societyDTO, Society.class);
		SocietyDTO returnSocietyDTO = modelMapper.map(societyService.addSociety(society), SocietyDTO.class);
		return new ResponseEntity<>(returnSocietyDTO, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SocietyDTO> updateSociety(@RequestBody SocietyDTO societyDTO, @PathVariable("id") Long id) {
		Society society = modelMapper.map(societyDTO, Society.class);
		society.setId(id);
		SocietyDTO returnSocietyDTO = modelMapper.map(societyService.update(society), SocietyDTO.class);
		return new ResponseEntity<>(returnSocietyDTO, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SocietyDTO> getSocietyById(@PathVariable("id") Long id) {
		Society society = societyService.getSocietyById(id);
		SocietyDTO societyDTO = modelMapper.map(society, SocietyDTO.class);
		return new ResponseEntity<>(societyDTO, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<SocietyDTO>> getAllSocietys() {
		List<Society> list = societyService.getAllSocietys();
		List<SocietyDTO> listdto = list.stream().map(society -> modelMapper.map(society, SocietyDTO.class))
				.collect(Collectors.toList());
		return new ResponseEntity<>(listdto, HttpStatus.OK);
	}

	public ResponseEntity<Void> deleteSociety(@PathVariable("id") Long id) {
		societyService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/{id}/customers")
	public ResponseEntity<List<CustomerDTO>> getCustomers(@PathVariable("id") Long societyId) {
		List<Customer> customers = societyService.getCustomersBySocietyId(societyId);
		List<CustomerDTO> customerdtos = customers.stream()
				.map(customer -> modelMapper.map(customer, CustomerDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(customerdtos, HttpStatus.OK);
	}
}
