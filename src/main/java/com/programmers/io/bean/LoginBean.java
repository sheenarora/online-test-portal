package com.programmers.io.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginBean {

	private String emailId;

	private String password;

	@Override
	public String toString() {
		return "LoginRequest [emailId=" + emailId + ", password=" + password + "]";
	}

}
