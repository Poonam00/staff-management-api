package com.staffmanagement.dto;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.staffmanagement.jsonview.UserViews;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO {
	private Long id;
	@JsonView(UserViews.Detail.class)
	private String name;
	private int familycount;
	private String flatno;
	private SocietyDTO society;
	private Set<UserDTO> users;
	@JsonIgnore
	private Date createddate;
	@JsonIgnore
	private Date modifieddate;
}
