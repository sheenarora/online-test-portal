package com.programmers.io.entities;

import java.util.Date;	

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class ExamCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique=true)
	private String examCategoryName;
	
	//@Column(columnDefinition = "boolean default true")
	private Boolean isActive = true;
	
	@UpdateTimestamp
	public Date timestamp;
	
}
