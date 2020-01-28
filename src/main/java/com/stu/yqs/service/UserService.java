package com.stu.yqs.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
import com.stu.yqs.utils.IdentityUtil;
import com.stu.yqs.utils.ImageUtil;
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
	private IdentityUtil identityUtil;
	@Autowired
	private UserMapper userMapper;
	
	//获取已登录用户个人信息
	public JSONObject getInfo()  throws LogicException{
		int id=identityUtil.isLogin();
		User user=userMapper.selectByPrimaryKey(id);
		if(user==null)	throw new LogicException(502,"此id不存在");
		JSONObject json=(JSONObject)JSONObject.toJSON(user);
		json.remove("password");
		return json;
	}
	//用户登录
	public JSONObject login(String phoneNumber,String password)  throws LogicException{
		long phoneNumber_long=identityUtil.phoneNumberFormatCheck(phoneNumber);
		User user=userMapper.selectByPhoneNumber(phoneNumber_long);
		if(user==null)	throw new LogicException(502,"未找到该用户");
		else if(!user.getPassword().equals(password))	throw new LogicException(501,"输入密码错误");
		JSONObject json=(JSONObject)JSONObject.toJSON(user);
		json.remove("password");
		HttpSession session=request.getSession();
		session.setAttribute("id", user.getId());
		session.setMaxInactiveInterval(60*60*24*365*4);
		return json;
	}
	//用户注册，需要先获取验证码
	public JSONObject register(String phoneNumber,String password,String academy,String verification) throws LogicException{
		long phoneNumber_long=identityUtil.phoneNumberFormatCheck(phoneNumber);
		identityUtil.verificationIsEqual(phoneNumber,verification);
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
		identityUtil.phoneNumberFormatCheck(phoneNumber);
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
		long phoneNumber_long=identityUtil.phoneNumberFormatCheck(phoneNumber);
		identityUtil.verificationIsEqual(phoneNumber,verification);
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
	//修改用户头像
	public JSONObject modifyHeadImage(MultipartFile file) throws LogicException{
		int id=identityUtil.isLogin();
		if(file==null || file.isEmpty())	throw new LogicException(503,"上传文件为空");
		
		ImageUtil imageUtils=new ImageUtil();
		imageUtils.newFileUrl("headImage", file);
		String localUrl=imageUtils.getLocalFile();
		String httpUrl=imageUtils.getHttpFile();
		boolean compressSuccess=ImageUtil.compressFile(file, localUrl);
		if(!compressSuccess)	throw new LogicException(504,"图片格式异常");
		//数据库处理
		User user=userMapper.selectByPrimaryKey(id);
		user.setHeadImage(httpUrl);
		userMapper.updateByPrimaryKeySelective(user);
		JSONObject json=new JSONObject();
		json.put("headImage", httpUrl);
		return json;
	}
	
	//修改用户信息
	public JSONObject modifyInfo(String name, String emailNumber, String academy) throws LogicException {
		int id=identityUtil.isLogin();
		User user=userMapper.selectByPrimaryKey(id);
		if(name!=null)	user.setName(name);
		if(emailNumber!=null)	user.setEmailNumber(emailNumber);
		if(academy!=null)	user.setAcademy(academy);
		userMapper.updateByPrimaryKeySelective(user);
		return (JSONObject)JSONObject.toJSON(user);
	}
}
