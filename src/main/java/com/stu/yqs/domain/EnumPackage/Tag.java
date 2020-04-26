package com.stu.yqs.domain.EnumPackage;

import com.stu.yqs.aspect.LogicException;

public enum Tag {
	交通出行,护肤化妆,服饰宝宝,家具电器,电子数码,二手图书,饰品,运动装备,零食,其他;
	
	public static String format(String value) throws LogicException{
		if(value==null)		return null;
		for(Tag tag:Tag.values()) {
			if(tag.toString().equals(value))		return value;
		}
		throw new LogicException(501,"参数错误，没有相匹配的Tag类型");
	}
	
	public String toString() {
		return this.name();
	}
}
