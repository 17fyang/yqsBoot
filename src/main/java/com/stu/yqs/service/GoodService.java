package com.stu.yqs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.stu.yqs.dao.GoodMapper;
import com.stu.yqs.domain.Good;
import com.stu.yqs.exception.LogicException;
import com.stu.yqs.utils.IdentityUtil;
import com.stu.yqs.utils.ImageUtil;

@Service
public class GoodService {
	@Autowired
	private GoodMapper goodMapper;
	@Autowired
	private IdentityUtil identityUtil;
	//发布一个交易
	public JSONObject newTransaction(MultipartFile[] file, String name, String describe) throws LogicException {
		int id=identityUtil.isLogin();
		if(name==null) throw new LogicException(501,"请填写物品名");
		if(describe==null) throw new LogicException(501,"请填写物品描述");
		String[] url=null;
		if(file!=null) {
			List<String> urlList=new ArrayList<String>();
			ImageUtil imageUtil=new ImageUtil();
			for(int i=0;i<file.length;i++) {
				ImageUtil.checkFileSize(file[i]);
				imageUtil.newFileUrl( "goodImage", file[i]);
				String httpUrl=imageUtil.getHttpFile();
				String localUrl=imageUtil.getLocalFile();
				ImageUtil.compressFile(file[i], localUrl);
				urlList.add(httpUrl);
			}
			url=urlList.toArray(new String[urlList.size()]);
		}
		
		Good good=new Good();
		good.setOwnerId(id);
		good.setName(name);
		good.setDescribe(describe);
		good.setImages(url);
		goodMapper.insertSelective(good);
		return (JSONObject)JSONObject.toJSON(good);
	}
	//删除一个交易
	public JSONObject deleteTransaction(int transactionId) {
		
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
