package com.staffmanagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.staffmanagement.jsonview.UserViews;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDTO {
	@JsonView(UserViews.Detail.class)
	private String landmark;
	@JsonView(UserViews.Detail.class)
	private String city;
	@JsonView(UserViews.Detail.class)
	private String state;
	@JsonView(UserViews.Detail.class)
	private int zip;
}
