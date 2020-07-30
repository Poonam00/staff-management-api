package com.staffmanagement.dto;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.staffmanagement.jsonview.UserViews;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "name", "age", "address", "profession", "mobileno", "address" })
public class UserDTO {

	@JsonView(UserViews.Summary.class)
	private Long id;

	@JsonView(UserViews.Summary.class)
	private String name;

	@JsonView(UserViews.Summary.class)
	private String profession;

	@JsonView(UserViews.Summary.class)
	@NotBlank(message = "mobileno cannot be blank")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "mobileno should be of 10 digits")
	private String mobileno;

	@JsonView(UserViews.Detail.class)
	private String age;

	@JsonView(UserViews.Detail.class)
	private AddressDTO address;

	@JsonView(UserViews.Detail.class)
	private Set<CustomerDTO> customers;

	@JsonIgnore
	private Date createddate;

	@JsonIgnore
	private Date modifieddate;
}
