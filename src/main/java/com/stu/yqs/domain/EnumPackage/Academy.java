package com.stu.yqs.domain.EnumPackage;

import com.stu.yqs.aspect.LogicException;

public enum Academy {
	至诚书院,
	德馨书院,
	弘毅书院,
	明德书院,
	修远书院,
	敬一书院,
	思源书院,
	知行书院,
	其他,
	未知;
	 
	public static String format(String value) throws LogicException{
		if(value==null)		return null;
		for(Academy academy:Academy.values()) {
			if(academy.toString().equals(value))		return value;
		}
		throw new LogicException(501,"参数错误，没有相匹配的书院类型");
	}
	
	public String toString() {
		return this.name();
	}
}
