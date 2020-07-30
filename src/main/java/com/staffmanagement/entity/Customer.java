package com.staffmanagement.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreatedDate
	@Column(name = "CREATEDDATE", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
	private Date createddate;

	@LastModifiedDate
	private Date modifieddate;

	private String name;

	private int familycount;

	private String flatno;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Society society;

	@ManyToMany(mappedBy = "customers")
	@EqualsAndHashCode.Exclude
	private Set<User> users;
}
