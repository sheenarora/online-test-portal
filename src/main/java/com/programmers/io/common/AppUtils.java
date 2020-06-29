package com.programmers.io.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.programmers.io.securityConfig.JwtTokenUtil;

import io.jsonwebtoken.Claims;

public class AppUtils {

	@Autowired
	private static JwtTokenUtil jwtTokenUtil;
	
	public static Claims fetchClaimsFromToken(HttpServletRequest request) {
		final String requestTokenHeader = request.getHeader("Authorization");
		
		/*
		 * JWT Token is in the form "programmers.io token". Remove
		 * programmers.io word and get only the Token
		 */
		String jwtToken = requestTokenHeader.substring(15);
		jwtTokenUtil = new JwtTokenUtil();
		
		Claims claims = jwtTokenUtil.getAllClaimsFromToken(jwtToken);
		
		return claims;

	}

}
