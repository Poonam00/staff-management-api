package com.staffmanagement.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SocietyDTO {
	private Long id;
	private String societyname;
	private AddressDTO address;
	private List<CustomerDTO> customers;
	
    @JsonIgnore
	private Date createddate;
    
    @JsonIgnore
	private Date modifieddate;
}
