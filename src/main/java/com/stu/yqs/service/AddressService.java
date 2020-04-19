package com.stu.yqs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.dao.AddressMapper;

@Service
public class AddressService {
	@Autowired
	private AddressMapper adressMapper;
	
	public JSONObject addAction(String content) throws LogicException {
		return null;
	}
	public JSONObject updateAction(String content) throws LogicException {
		return null;
	}
	public JSONArray getListAction() throws LogicException {
		return null;
	}
	public JSONObject deleteAction(Integer addressId) throws LogicException {
		return null;
	}
}
