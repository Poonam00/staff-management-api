package com.staffmanagement.dto;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SocietyDTO {
	private Long id;
	private String societyname;
	private AddressDTO address;
	private Set<CustomerDTO> customers;
	@JsonIgnore
	private Date createddate;
	@JsonIgnore
	private Date modifieddate;
}
