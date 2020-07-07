package com.programmers.io.bean;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class StatusBean {

	private HttpStatus code;

	private String message;

	public StatusBean(HttpStatus code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	

}
