package com.programmers.io.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class StatusBean {

	private int code;

	private String message;

	public StatusBean(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

}
