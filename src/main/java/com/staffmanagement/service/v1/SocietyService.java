package com.staffmanagement.service.v1;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staffmanagement.dto.CustomerDTO;
import com.staffmanagement.dto.SocietyDTO;
import com.staffmanagement.entity.Society;
import com.staffmanagement.exceptionhandler.DataNotFoundException;
import com.staffmanagement.repository.SocietyRepository;

@Service
public class SocietyService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private SocietyRepository societyRepository;

	public SocietyDTO addSociety(SocietyDTO societydto) {
		societydto.setId(null);
		Society society = modelMapper.map(societydto, Society.class);
		return modelMapper.map(societyRepository.save(society), SocietyDTO.class);
	}

	public SocietyDTO update(SocietyDTO societydto, Long id) {
		Society society = modelMapper.map(societydto, Society.class);
		society.setId(id);
		return modelMapper.map(societyRepository.save(society), SocietyDTO.class);
	}

	public SocietyDTO getSocietyById(Long id) {
		Optional<Society> opt = societyRepository.findById(id);
		if (opt.isPresent()) {
			Society society = opt.get();
			return modelMapper.map(society, SocietyDTO.class);
		} else {
			throw new DataNotFoundException("society not found with id-" + id);
		}
	}

	public List<SocietyDTO> getAllSocieties() {
		return societyRepository.findAll().stream().map(society -> modelMapper.map(society, SocietyDTO.class))
				.collect(Collectors.toList());
	}

	public List<CustomerDTO> getCustomersBySocietyId(Long societyId) {
		return societyRepository.findCustomersById(societyId).stream()
				.map(customer -> modelMapper.map(customer, CustomerDTO.class)).collect(Collectors.toList());
	}

	public long count() {
		return societyRepository.count();
	}

	public void deleteById(Long societyId) {
		societyRepository.deleteById(societyId);
	}

}
