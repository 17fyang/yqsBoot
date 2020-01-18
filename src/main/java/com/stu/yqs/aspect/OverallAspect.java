package com.stu.yqs.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.stu.yqs.exception.LogicException;

/*
 * date:2019-11-8
 * author:yf
 * info:全局的controller异常和逻辑处理方法，
 * 			1、获取controller返回的json，封装成restful模式。
 * 			2、扫描所有的controller包，若controller出现异常则返回errcode：500
 * 			3、处理用户的logicException逻辑异常
 */

@Aspect
@Component
public class OverallAspect {
	@Around("execution(* com.stu.yqs.controller..*(..))")
	public Object exceptionDeal(ProceedingJoinPoint pjp) {
		Object result=null;
		JSONObject json=new JSONObject();
		try {
			Object[] args=pjp.getArgs();
			result = pjp.proceed(args);
		}catch(LogicException logicE) {
			json.put("errcode", logicE.getErrCode());
			json.put("message", logicE.getInfo());
			json.put("data",new JSONObject());
			return json.toString();
		}catch (Throwable e) {
			if(e instanceof NullPointerException) {
				e.printStackTrace();
				json.put("errcode", 501);
				json.put("message", "空参数异常");
				json.put("data",new JSONObject());
			}else {
				e.printStackTrace();
				json.put("errcode", 500);
				json.put("message", "未知错误!");
				json.put("data",new JSONObject());
			}
			return json.toString();
		}
		json.put("errcode", 200);
		json.put("message", "处理成功!");
		json.put("data",JSON.parseObject(result.toString()));
		return json.toJSONString();
	}
}
