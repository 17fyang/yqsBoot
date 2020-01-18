package com.stu.yqs.domain.EnumPackage;

import java.util.HashMap;
import java.util.Map;

import com.stu.yqs.exception.LogicException;

public enum Academy {
	至诚书院("志诚书院",0),
	德馨书院("德馨书院",1),
	弘毅书院("弘毅书院",2),
	明德书院("明德书院",3),
	修远书院("修远书院",4),
	敬一书院("敬一书院",5),
	思源书院("思源书院",6),
	知行书院("知行书院",7),
	其他("其他",8),
	未知("未知",9);
	 
	private String key;
    private int value;
	private Academy(String key, int value){
        this.key = key;
        this.value = value;
    }
    private static Map<Integer, Academy> valueMap = new HashMap<Integer, Academy>();
    //初始化的时候建立code和key的对应信息
    static{
        for (Academy item : Academy.values()) {
            valueMap.put(item.getValue(), item);
        }
    }
  //前台传进来的值通过这个方法来转换为IdType类型
    public static Academy getByValue(int academy) throws LogicException {
        Academy result = valueMap.get(academy);
        if(result == null) {
            throw new LogicException(501,"参数错误：没有相匹配的书院类型");
        }
        return result;
    }
    public static Academy getByValue(String academy) throws LogicException {
    	for (Academy item :Academy.values()) {
            if(academy.equals(item.toString()))		return item;
        }
    	throw new LogicException(501,"参数错误：没有相匹配的身份类型");
    }
    public static String stringByValue(int academy) throws LogicException {
        Academy result = valueMap.get(academy);
        if(result == null) {
            throw new LogicException(501,"参数错误：没有相匹配的书院类型");
        }
        return result.toString();
    }
    public static String stringByValue(String academy) throws LogicException {
    	for (Academy item :Academy.values()) {
            if(academy.equals(item.toString()))		return item.toString();
        }
    	throw new LogicException(501,"参数错误：没有相匹配的身份类型");
    }
    
    
    
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}
