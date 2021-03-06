package com.programmers.io.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class ResultDetail  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Result result;
	
	@OneToOne
	private ExamDetail examDetail;

	private int obtainedMarks;
	
	private int totalMarks;

}
