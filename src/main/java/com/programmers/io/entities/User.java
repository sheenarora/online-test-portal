package com.programmers.io.entities;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String emailId;

	private String firstName;

	private String middleName;

	private String lastName;

	private String gender;

	private int age;

	private String college;

	private String skills;

	@Transient
	private Set<String> skillList;

	private String degree;

	private int graduationYear;

	private String mobileNumber;

	private String country;

	private String state;

	private String city;

	private double graduationPercentage;

	@UpdateTimestamp
	public Date timestamp;
}