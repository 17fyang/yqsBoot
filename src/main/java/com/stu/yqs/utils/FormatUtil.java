package com.stu.yqs.utils;

import org.springframework.stereotype.Component;

import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.domain.EnumPackage.Academy;
import com.stu.yqs.domain.EnumPackage.IdType;
import com.stu.yqs.domain.EnumPackage.Tag;

/*
 * date:2020-5-10
 * author:yf
 * detail:参数格式校验的工具类
 */
@Component
public class FormatUtil {
	//地址是否默认参数校验
	public void addressDefault(Short isDefault) throws LogicException {
		if(isDefault==null)	return;
		if(isDefault!=0 && isDefault!=1)	throw new LogicException(501,"isDefalt参数异常，0或1");
	}
	//手机号格式校验
	public void phoneNumber(String phoneNumber) throws LogicException {
		if(phoneNumber==null)	return;
		String formatCheck = "1{1}\\d{10}";
		if(!phoneNumber.matches(formatCheck)) throw new  LogicException(501,"手机号格式错误");
	}
	//书院参数校验
	public void academy(String academy) throws LogicException {
		Academy.format(academy);
	}
	//用户身份校验
	public void idType(String idType) throws LogicException {
		IdType.format(idType);
	}
	//标签校验
	public void tag(String tag) throws LogicException {
		Tag.format(tag);
	}
}
