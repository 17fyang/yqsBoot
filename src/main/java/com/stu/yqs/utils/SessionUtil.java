package com.stu.yqs.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class SessionUtil {
	@Autowired
	private HttpServletRequest request;
}
