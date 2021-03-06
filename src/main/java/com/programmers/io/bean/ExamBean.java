package com.programmers.io.bean;

import java.util.List;

import com.programmers.io.bean.QuestionCategoryBean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ExamBean {

	private String examName;
	
	private String examDuration;

	private String userName;
	
	private List<QuestionCategoryBean> questionCategories;
	
	private StatusBean status;
}
