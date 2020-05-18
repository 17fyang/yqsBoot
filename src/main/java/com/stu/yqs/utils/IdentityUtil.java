package com.stu.yqs.utils;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stu.yqs.aspect.LogicException;

@Service
public class IdentityUtil {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private SessionUtil sessionUtil;
	
	//判断验证码是否相符
		public boolean verificationIsEqual(String phoneNumber,String verification) throws LogicException {
			HttpSession session=request.getSession();
			String verificationCode=String.valueOf(session.getAttribute("verificationCode"));
			Date date=(Date)session.getAttribute("verificationTime");
			long intervalTime=60000*5;//验证码有效期为五分钟
			if(date==null)	throw new LogicException(503,"请获取验证码"); 
			if(new Date().getTime()-date.getTime()>intervalTime)	throw new LogicException(503,"验证码已过期");
			verification=phoneNumber+"_"+verification;
			if(!verification.equals(verificationCode)) throw new LogicException(503,"验证码错误");
			return true;
		}
		
		
		
		//判断用户是否登录
		public int isLogin() throws LogicException {
//			if(1==1)	return 44;
			
			String id=sessionUtil.getLoginSession();
			if(id==null || id.equals("null"))	throw new LogicException(502,"用户未登录");
			return Integer.parseInt(id);
		}
}
