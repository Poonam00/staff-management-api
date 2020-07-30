package com.staffmanagement.entity;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Address {
	private String landmark;
	private String city;
	private String state;
	private String zip;
}
