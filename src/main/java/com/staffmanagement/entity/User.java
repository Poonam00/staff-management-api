package com.staffmanagement.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreatedDate
	@Column(name = "CREATEDDATE", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
	private Date createddate;

	@LastModifiedDate
	private Date modifieddate;

	private String name;

	private String profession;

	private String mobileno;

	private int age;

	@Embedded
	private Address address;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_customer", joinColumns = { @JoinColumn(name = "fk_user") }, inverseJoinColumns = {
			@JoinColumn(name = "fk_customer") })
	@EqualsAndHashCode.Exclude
	private Set<Customer> customers;
}
