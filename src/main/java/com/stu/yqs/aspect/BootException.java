package com.stu.yqs.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alibaba.fastjson.JSONObject;


@ControllerAdvice
public class BootException {
	 @ExceptionHandler(value = Exception.class)
	    public void errorHandler(HttpServletRequest request, HttpServletResponse response,Exception e) throws Exception {
	        e.printStackTrace();
	        JSONObject json=new JSONObject();
	        json.put("errcode", 510);
			json.put("message", "容器异常，请检查传入参数格式");
			json.put("data",new JSONObject());
			System.out.println("testBootException");
			response.getOutputStream().write(json.toJSONString().getBytes("utf-8"));
	    }
}
