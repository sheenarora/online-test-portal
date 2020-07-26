package com.programmers.io.bean;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class LoginStatusBean {
	
	private long id;
	
	private String time;
	
	private HttpStatus code;

	private String message;

	public LoginStatusBean(long id, String time, HttpStatus code, String message) {
		super();
		this.id = id;
		this.time = time;
		this.code = code;
		this.message = message;
	}
}
