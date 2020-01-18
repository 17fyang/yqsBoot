package com.stu.yqs.service;

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
	
	public JSONObject getInfo()  throws LogicException{
		String id=String.valueOf( request.getSession().getAttribute("id"));
		if(id==null)	throw new LogicException(501,"用户未登录");
		int id_int=Integer.parseInt(id);
		User user=userMapper.selectByPrimaryKey(id_int);
		if(user==null)	throw new LogicException(501,"未找到该用户");
		
		JSONObject json=(JSONObject)JSONObject.toJSON(user);
		json.remove("password");
		return json;
	}
	
	public JSONObject login(String number,String password)  throws LogicException{
		long number_long=Long.parseLong(number);
		User user=userMapper.selectByPhoneNumber(number_long);
		if(user==null)	throw new LogicException(502,"未找到该用户");
		else if(!user.getPassword().equals(password))	throw new LogicException(501,"输入密码错误");
		
		JSONObject json=(JSONObject)JSONObject.toJSON(user);
		json.remove("password");
		HttpSession session=request.getSession();
		session.setAttribute("id", user.getId());
		session.setMaxInactiveInterval(60*60*24*365*4);
		return json;
	}

	public JSONObject register(String number,String password,String academy,String verification) throws LogicException{
		String formatCheck = "1{1}\\d{10}";
		if(!number.matches(formatCheck)) throw new  LogicException(501,"手机号格式错误");
		String verificationCode=String.valueOf(request.getSession().getAttribute("verificationCode"));
		if(verificationCode==null) throw new LogicException(503,"验证码已过期");
		if(!verification.equals(verificationCode)) throw new LogicException(503,"验证码错误");
		
		User user = new User();
		long number_long=Integer.parseInt(number);
		user.setPhonenumber(number_long);
		user.setPassword(password);
		user.setIdtype(IdType.stringByValue("注册用户"));
		user.setAcademy(Academy.stringByValue(academy));
		
		User selectUser=userMapper.selectByPhoneNumber(number_long);
		if(selectUser!=null)	throw new LogicException(502,"该账号已存在");
		userMapper.insertSelective(user);
		
		JSONObject json=(JSONObject)JSONObject.toJSON(user);
		json.remove("password");
		return json;
	}

	public JSONObject verificationCode(String number) throws LogicException {
		String formatCheck = "1{1}\\d{10}";
		if(!number.matches(formatCheck)) throw new  LogicException(501,"手机号格式错误");
		
		//生成验证码
		int random=(int)(Math.random()*900000)+100000;
		String verification=String.valueOf(random);
		JSONObject json =new JSONObject();
		json.put("code", verification);
		
		//测试代码
		int test=1;
		if(test==1) {
			HttpSession session=request.getSession();
			System.out.println(session.getId());
	        session.setAttribute("verificationCode", "123456");
	        session.setMaxInactiveInterval(60*5);
	        return json;
		}
		
		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI91MlauzR6ZnZ", "GSdt21HHx7bqOyUB6QXWn596OBjnY1");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest send = new CommonRequest();
        send.setMethod(MethodType.POST);
        send.setDomain("dysmsapi.aliyuncs.com");
        send.setVersion("2017-05-25");
        send.setAction("SendSms");
        send.putQueryParameter("RegionId", "cn-hangzhou");
        send.putQueryParameter("PhoneNumbers", number);
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
        session.setMaxInactiveInterval(60*5);
		return json;
	}
}
