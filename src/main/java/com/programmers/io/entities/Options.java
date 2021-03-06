package com.programmers.io.entities;

import java.util.Date;

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
public class Options {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String optionText;
	
	private Boolean answer;

	@UpdateTimestamp
	public Date timestamp;

}
