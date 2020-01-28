package com.stu.yqs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stu.yqs.dao.GoodMapper;
import com.stu.yqs.domain.Good;
import com.stu.yqs.domain.GoodSearch;
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
//			testCode,找前端多个文件的代码太麻烦，所以这样模拟多个文件
//			MultipartFile temp=file[0];
//			file=new MultipartFile[2];
//			file[0]=temp;
//			file[1]=temp;
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
		good.setGoodDescribe(describe);
		good.setImages(url);
		goodMapper.insertSelective(good);
		JSONObject json=(JSONObject)JSONObject.toJSON(good);
		json.remove("image");
		return json;
	}
	//删除一个交易
	public JSONObject deleteTransaction(Integer transactionId) throws LogicException {
		int id=identityUtil.isLogin();
		Good good=goodMapper.selectByPrimaryKey(transactionId);
		if(good==null)		throw new LogicException(501,"未找到该id");
		if(good.getOwnerId()!=id)	throw new LogicException(502,"只能操作对自已发起的交易");
		good.setState("4");
		goodMapper.updateByPrimaryKeySelective(good);
		return  new JSONObject();
	}
	//获取一些交易，可选择按书院或关键字筛选
	public JSONObject getTransaction(Integer startId, Integer range, String academy,String keyword) throws LogicException {
		if(range<=0)	throw new  LogicException(501,"参数格式异常");
		GoodSearch search=new GoodSearch();
		search.setStartId(startId);
		search.setAcademy(academy);
		search.setRange(range);
		search.setKeyword(keyword);
		List<Good> good=goodMapper.searchGoods(search);
		JSONObject json=new JSONObject();
		JSONArray arr=(JSONArray)JSON.toJSON(good);
		json.put("length", good.size());
		boolean isEnd=false;
		if((range==null && good.size()<15) || (range!=null && good.size()<range))	isEnd=true;
		json.put("isEnd",isEnd);
		if(!isEnd)		json.put("nextId",good.get(good.size()-1).getId());
		json.put("arr", arr);
		return json;
	}

	
}
