package com.stu.yqs.domain.EnumPackage;

import com.stu.yqs.aspect.LogicException;

public enum IdType {
	游客,
	微信用户,
	注册用户;
	
	public static String format(String value) throws LogicException{
		if(value==null)		return null;
		for(IdType type:IdType.values()) {
			if(type.toString().equals(value))		return value;
		}
		throw new LogicException(501,"参数错误，没有相匹配的身份类型");
	}
	
	public String toString() {
		return this.name();
	}
   
}
