package com.programmers.io.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResultBean {
	
	//private String ExamName;
	
	//private String UserName;
	
	private String questionCategory;

	private int obtainedMarks;
	
	private int totalMarks;
	
	private StatusBean status;

}
