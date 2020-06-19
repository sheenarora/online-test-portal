package com.programmers.io.common;

public class Constant {
	
	//Password Constant
	public static final Long USER_PASSWORD =  (long) 12345;
	
	public static final Long JWT_TOKEN_VALIDITY = (long) (5 * 60 * 60);
	
	
	//status codes
	public static final int SUCCESS_CODE = (int) 200;
	public static final int BAD_REQUEST_CODE = (int) 404;
	public static final int CUSTOM_ERROR_CODE = (int) 413;
	public static final int INTERNAL_SERVER_ERROR_CODE = (int) 500;
	
	//status messages
	public static final String SUCCESS_MESSAGE = "Success.";
	public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal Server Error.";
	public static final String BAD_REQUEST_MESSAGE = "Bad Request.";
	
	public static final String INVALID_USER_ID_MESSAGE = "Invalid User Id.";
	public static final String INVALID_QUESTION_CATEGORY_MESSAGE = "Bad Request. Invalid Question Categories.";
	public static final String DUPLICATE_EMAIL_MESSAGE = "EmailId Already Exist.";
	public static final String INCORRECT_PASSWORD_MESSAGE = "Incorrect password.";
	public static final String EXAM_EXPIRED_MESSAGE = "Exam has Expired.";
	public static final String USER_NOTFOUND_MESSAGE = "User not found with ID : ";
	public static final String EXAM_NOTFOUND_MESSAGE = "Exam not found with ID : ";
	
}	
