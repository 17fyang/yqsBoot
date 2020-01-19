package com.stu.yqs.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

@Service
public class GoodService {

	//发布一个交易
	public JSONObject newTransaction(MultipartFile[] file, String name, String decribe) {
		// TODO Auto-generated method stub
		return null;
	}
	//删除一个交易
	public JSONObject deleteTransaction(int transactionId) {
		// TODO Auto-generated method stub
		return null;
	}
	//获取一些交易，可选择按书院筛选
	public JSONObject getTransaction(int startId, int range, String academy) {
		// TODO Auto-generated method stub
		return null;
	}
	//搜索交易物品
	public JSONObject searchTransaction(int startId, int range, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
