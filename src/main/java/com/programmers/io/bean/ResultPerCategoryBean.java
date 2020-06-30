package com.programmers.io.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResultPerCategoryBean {
	
	private String questionCategory;

	private int obtainedMarks;
	
	private int totalMarks;
	
	private StatusBean status;

}
