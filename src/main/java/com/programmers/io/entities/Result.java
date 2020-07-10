package com.programmers.io.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Result {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int totalMarks;
	
	private int obtainedMarks;

	@OneToOne(fetch = FetchType.EAGER)
	private User user;

	@OneToOne(fetch = FetchType.EAGER)
	private Exam exam;

	@OneToMany
	@JoinColumn(name = "result_id")
	private Set<ResultDetail> resultDetailSet;
	
	@UpdateTimestamp
	public Date timestamp;
}
