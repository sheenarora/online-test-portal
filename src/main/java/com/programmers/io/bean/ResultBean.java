package com.programmers.io.bean;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResultBean {
	
	private List<ResultBean> resultResponseList;
	
	private StatusBean status;

}
