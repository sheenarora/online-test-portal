package com.programmers.io.bean;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuestionCategoryBean {

	//private String id;

	private String text;

	private List<SectionBean> sections;

}
