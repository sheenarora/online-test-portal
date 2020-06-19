package com.programmers.io.bean;

import java.util.List;

import com.programmers.io.entities.Question;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SectionBean {

	//private String id;
	
	private String examDetailId;

	private String marksPerQuestion;	

	private String noOfQuestions;
	
	private List<QuestionBean> questions;
	
}
