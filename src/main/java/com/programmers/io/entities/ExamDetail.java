package com.programmers.io.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"exam_id", "questionCategory_id" , "section_id"})})
public class ExamDetail {
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name= "questionCategory_id")
    private QuestionCategory questionCategory;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name= "section_id")
	private Section section;
	
	private int numberOfQuestions;
	
	
}
