package com.programmers.io.bean;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginStatusBean {

	private long id;

	private String startDate;

	private HttpStatus code;

	private String message;

	public LoginStatusBean(long id, String startDate, HttpStatus code, String message) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.code = code;
		this.message = message;
	}

}
