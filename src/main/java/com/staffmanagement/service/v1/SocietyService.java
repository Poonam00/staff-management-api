package com.staffmanagement.service.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staffmanagement.entity.Customer;
import com.staffmanagement.entity.Society;
import com.staffmanagement.repository.SocietyRepository;

@Service
public class SocietyService {

	@Autowired
	SocietyRepository societyRepository;

	public Society addSociety(Society society) {
		return societyRepository.save(society);
	}

	public Society getSocietyById(Long societyId) {
		Society society = new Society();
		Optional<Society> opt = societyRepository.findById(societyId);
		if (opt.isPresent()) {
			society = opt.get();
		}
		return society;
	}

	public long count() {
		return societyRepository.count();
	}

	public void deleteById(Long societyId) {
		societyRepository.deleteById(societyId);
	}

	public Society update(Society society) {
		return societyRepository.save(society);
	}

	public List<Society> getAllSocietys() {
		List<Society> list = new ArrayList<>();
		societyRepository.findAll().forEach(list::add);
		return list;
	}
	public List<Customer> getCustomersBySocietyId(Long societyId) {
		List<Customer> customers = new ArrayList<>();
		societyRepository.findCustomersById(societyId).forEach(customers::add);
		return customers;
	}
}
