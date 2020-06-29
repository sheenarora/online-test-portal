package com.programmers.io.service;

import java.util.Map;

import com.programmers.io.bean.LoginBean;
import com.programmers.io.bean.StatusBean;

public interface LoginService {

	StatusBean validateloginBean(String emailId, String password) throws Exception;

	Map<String, String> login(LoginBean loginBean) throws Exception;

}
