package com.stu.yqs.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.stu.yqs.exception.LogicException;
import com.stu.yqs.dao.UserMapper;
import com.stu.yqs.domain.User;
import com.stu.yqs.domain.EnumPackage.Academy;
import com.stu.yqs.domain.EnumPackage.IdType;

/*
 * date：2020.1.17
 * author：yf
 * detail：
 */
@Service
public class UserService {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private UserMapper userMapper;
	//获取已登录用户个人信息
	public JSONObject getInfo()  throws LogicException{
		String id=String.valueOf( request.getSession().getAttribute("id"));
		if(id==null || id.equals("null"))	throw new LogicException(501,"用户未登录");
		int id_int=Integer.parseInt(id);
		User user=userMapper.selectByPrimaryKey(id_int);
		if(user==null)	throw new LogicException(501,"未找到该用户");
		
		JSONObject json=(JSONObject)JSONObject.toJSON(user);
		json.remove("password");
		return json;
	}
	//用户登录
	public JSONObject login(String phoneNumber,String password)  throws LogicException{
		System.out.println(request.getParameter("phoneNumber"));
		long phoneNumber_long=phoneNumberFormatCheck(phoneNumber);
		User user=userMapper.selectByPhoneNumber(phoneNumber_long);
		if(user==null)	throw new LogicException(502,"未找到该用户");
		else if(!user.getPassword().equals(password))	throw new LogicException(501,"输入密码错误");
		JSONObject json=(JSONObject)JSONObject.toJSON(user);
		json.remove("password");
		HttpSession session=request.getSession();
		session.setAttribute("id", user.getId());
		session.setMaxInactiveInterval(60*60*24*365*4);
		System.out.println(user.getRegisterDate()+"**************");
		System.out.println(json.getString("registerdate")+"**************");
		System.out.println(json.toJSONString()+"************");
		return json;
	}
	//用户注册，需要先获取验证码
	public JSONObject register(String phoneNumber,String password,String academy,String verification) throws LogicException{
		long phoneNumber_long=phoneNumberFormatCheck(phoneNumber);
		verificationIsEqual(request.getSession(),phoneNumber+"_"+verification);
		
		User user = new User();
		user.setPhoneNumber(phoneNumber_long);
		user.setPassword(password);
		user.setIdType(IdType.stringByValue("注册用户"));
		user.setAcademy(Academy.stringByValue(academy));
		User selectUser=userMapper.selectByPhoneNumber(phoneNumber_long);
		if(selectUser!=null)	throw new LogicException(502,"该账号已存在");
		userMapper.insertSelective(user);
		JSONObject json=(JSONObject)JSONObject.toJSON(user);
		json.remove("password");
		return json;
	}
	//获取验证码
	public JSONObject verificationCode(String phoneNumber) throws LogicException {
		System.out.println(request.getParameter("phoneNumber"));
		phoneNumberFormatCheck(phoneNumber);
		//生成验证码
		int random=(int)(Math.random()*900000)+100000;
		String verification=String.valueOf(random);
		JSONObject json =new JSONObject();
		json.put("code", verification);
		
		//测试代码
		int test=1;
		if(test==1) {
			HttpSession session=request.getSession();
	        session.setAttribute("verificationCode", phoneNumber+"_123456");
	        session.setAttribute("verificationTime", new Date());
	        return new JSONObject();
		}
		
		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI91MlauzR6ZnZ", "GSdt21HHx7bqOyUB6QXWn596OBjnY1");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest send = new CommonRequest();
        send.setMethod(MethodType.POST);
        send.setDomain("dysmsapi.aliyuncs.com");
        send.setVersion("2017-05-25");
        send.setAction("SendSms");
        send.putQueryParameter("RegionId", "cn-hangzhou");
        send.putQueryParameter("PhoneNumbers", phoneNumber);
        send.putQueryParameter("SignName", "益启善");
        send.putQueryParameter("TemplateCode", "SMS_182672041");
        send.putQueryParameter("TemplateParam", json.toString());
        try {
            CommonResponse response = client.getCommonResponse(send);
            json=(JSONObject) JSONObject.parse(response.getData());
            if(!json.getString("Message").equals("OK"))	throw new LogicException(502,json.getString("Message"));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
		//记录验证码
        HttpSession session=request.getSession();
        session.setAttribute("verificationCode", verification);
        session.setAttribute("verificationTime", new Date());
		return json;
	}
	//修改密码，需要先获取验证码
	public JSONObject modifyPassword(String phoneNumber, String newPassword, String verification) throws LogicException {
		verificationIsEqual(request.getSession(),phoneNumber+"_"+verification);
		long phoneNumber_long=phoneNumberFormatCheck(phoneNumber);
		User user=userMapper.selectByPhoneNumber(phoneNumber_long);
		if(user==null)	throw new LogicException(502,"未找到该用户");
		user.setPassword(newPassword);
		userMapper.updateByPrimaryKeySelective(user);
		return new JSONObject();
	}
	//注销登录
	public JSONObject logout() {
		request.getSession().removeAttribute("id");
		return new JSONObject();
	}
	
	//判断验证码是否相符
	private boolean verificationIsEqual(HttpSession session,String verification) throws LogicException {
		String verificationCode=String.valueOf(session.getAttribute("verificationCode"));
		Date date=(Date)session.getAttribute("verificationTime");
		long intervalTime=60000*5;//验证码有效期为五分钟
		if(new Date().getTime()-date.getTime()>intervalTime)	throw new LogicException(503,"验证码已过期");
		if(!verification.equals(verificationCode)) throw new LogicException(503,"验证码错误");
		return true;
	}
	//判断手机号格式是否正确
		private long phoneNumberFormatCheck(String phoneNumber) throws LogicException {
			String formatCheck = "1{1}\\d{10}";
			if(!phoneNumber.matches(formatCheck)) throw new  LogicException(501,"手机号格式错误");
			long  phoneNumber_long=Long.parseLong(phoneNumber);
			return phoneNumber_long;
		}
}
