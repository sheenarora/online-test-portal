package com.programmers.io.service;

import java.util.Map;

import com.programmers.io.bean.LoginBean;
import com.programmers.io.bean.LoginStatusBean;

public interface LoginService {

	LoginStatusBean validateloginBean(String emailId, String password) throws Exception;

	Map<String, String> login(LoginBean loginBean) throws Exception;

}
