package com.stu.yqs.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class SessionUtil {
	@Autowired
	private HttpServletRequest request;
	
	
	public HttpSession getSession() {
		return request.getSession();
	}
}
