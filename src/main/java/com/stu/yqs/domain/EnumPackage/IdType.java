package com.stu.yqs.domain.EnumPackage;

import java.util.HashMap;
import java.util.Map;

import com.stu.yqs.aspect.LogicException;

public enum IdType {
	游客("游客",0),
	微信用户("微信用户",1),
	注册用户("注册用户",2);
	
	private String key;
    private int value;
	private IdType(String key, int value){
        this.key = key;
        this.value = value;
    }
	//将数值1,2,3和MALE,FAMALE,SECRET一起封装到HashMap中
    private static Map<Integer, IdType> valueMap = new HashMap<Integer, IdType>();
    //静态代码块
    static{
        for (IdType item : IdType.values()) {
            valueMap.put(item.getValue(), item);
        }
    }
  //前台传进来的值通过这个方法来转换为IdType类型
    public static IdType getByValue(int idType) throws LogicException {
        IdType result = valueMap.get(idType);
        if(result == null) {
            throw new LogicException(501,"参数错误：没有相匹配的身份类型");
        }
        return result;
    }
    public static IdType getByValue(String idType) throws LogicException {
    	for (IdType item : IdType.values()) {
            if(idType.equals(item.toString()))		return item;
        }
    	throw new LogicException(501,"参数错误：没有相匹配的身份类型");
    }
    
    public static String stringByValue(int idType) throws LogicException {
        IdType result = valueMap.get(idType);
        if(result == null) {
            throw new LogicException(501,"参数错误：没有相匹配的身份类型");
        }
        return result.toString();
    }
    
    public static String stringByValue(String idType) throws LogicException {
    	for (IdType item : IdType.values()) {
            if(idType.equals(item.toString()))		return item.toString();
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
