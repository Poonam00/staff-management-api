package com.staffmanagement.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.staffmanagement.jsonview.UserViews;

import lombok.Data;

@Data
public class UserDTO {
    @JsonView(UserViews.Summary.class)
	private Long id;

    @JsonView(UserViews.Summary.class)
	private String name;

    @JsonView(UserViews.Detail.class)
	private String profession;

    @JsonView(UserViews.Detail.class)
	private String age;

    @JsonView(UserViews.Detail.class)
	private String address;

}
