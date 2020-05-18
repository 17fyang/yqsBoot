package com.stu.yqs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.dao.BrowseMapper;
import com.stu.yqs.dao.GoodMapper;
import com.stu.yqs.dao.ReviewMapper;
import com.stu.yqs.dao.UserMapper;
import com.stu.yqs.domain.Browse;
import com.stu.yqs.domain.Good;
import com.stu.yqs.domain.User;
import com.stu.yqs.domain.search.GoodSearch;
import com.stu.yqs.domain.search.ReviewSearch;
import com.stu.yqs.utils.FormatUtil;
import com.stu.yqs.utils.GoodUtil;
import com.stu.yqs.utils.IdentityUtil;
import com.stu.yqs.utils.ImageUtil;
import com.stu.yqs.utils.OutputUtil;
import com.stu.yqs.utils.SessionUtil;

@Service
public class FatherGoodService {
	@Autowired
	private GoodMapper goodMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private BrowseMapper browseMapper;
	@Autowired
	private ReviewMapper reviewMapper;
	@Autowired
	private IdentityUtil identityUtil;
	@Autowired
	private ImageUtil imageUtil;
	@Autowired
	private SessionUtil sessionUtil;
	@Autowired
	private GoodUtil goodUtil;
	@Autowired
	private OutputUtil outputUtil;
	@Autowired
	private FormatUtil formatUtil;
	//发布一个交易
	public JSONObject newTransaction(String name, String describe, String tag, Double price, Double originalPrice, Double postage, String freeShipping,int goodType) throws LogicException {
		int id=identityUtil.isLogin();
		List<String>urlList=sessionUtil.getImageSession();
		sessionUtil.removeImageSession();
		if(name==null) throw new LogicException(501,"请填写物品名");
		if(describe==null) throw new LogicException(501,"请填写物品描述");
		formatUtil.tag(tag);
		if(urlList==null || urlList.isEmpty())	throw new LogicException(501,"请上传图片");
		List<String> httpUrls=new ArrayList<String>();
		for(int i=0;i<urlList.size();i++) {
			String newUrl[]=imageUtil.newFileUrl( "goodImage", urlList.get(i));
			String localUrl=newUrl[0];
			String httpUrl=newUrl[1];
			if(!imageUtil.fileTransfer(urlList.get(i), localUrl))	continue;//文件复制失败则跳过
			httpUrls.add(httpUrl);
		}
		User user=userMapper.selectPartByPrimaryKey(id);
		Good good=new Good();
		good.setOwnerId(id);
		good.setAcademy(user.getAcademy());
		good.setName(name);
		good.setGoodDescribe(describe);
		good.setImages(httpUrls.toArray(new String[httpUrls.size()]));
		good.setTag(tag);
		good.setPrice(price);
		good.setOriginalPrice(originalPrice);
		good.setFreeShipping(freeShipping);
		good.setGoodType(goodType);
		good.setPostage(postage);
		goodMapper.insertSelective(good);
		JSONObject json=(JSONObject)JSONObject.toJSON(good);
		json.remove("image");
		return json;
	}
	//单个图片上传接口
	public JSON fileUpload(MultipartFile file) throws LogicException {
		identityUtil.isLogin();
		if(file.isEmpty())	throw new LogicException(501,"未找到图片数据");
		imageUtil.checkFileSize(file);
		//存储图片数据
		String newUrl[]=imageUtil.newFileUrl("temp", file.getOriginalFilename());
		String localUrl=newUrl[0];
		imageUtil.compressFile(file, localUrl);
		//设置session
		sessionUtil.saveImageSession(localUrl);
		return new JSONObject();
	}
	//删除一个交易
	public JSONObject deleteTransaction(Integer transactionId) throws LogicException {
		int id=identityUtil.isLogin();
		Good good=goodMapper.selectByPrimaryKey(transactionId);
		if(good==null)		throw new LogicException(501,"未找到该id");
		if(good.getOwnerId()!=id)	throw new LogicException(503,"只能操作自已发起的交易");
		good.setState(String.valueOf(GoodUtil.DELETED));
		goodMapper.updateByPrimaryKeySelective(good);
		return  new JSONObject();
	}

	//获取待售出的商品交易，可选择按书院或关键字筛选
	public JSONObject getTransaction(Integer startId, Integer range, String academy,String keyword, String tag,int goodType) throws LogicException {
		GoodSearch search=new GoodSearch();
		search.setStartId(startId);
		search.setRange(range);
		formatUtil.academy(academy);
		search.setAcademy(academy);
		search.setKeyword(keyword);
		search.setTag(tag);
		search.setGoodType(goodType);
		search.setState(GoodUtil.ON_SALE);

		List<Good> goodList=this.searchGood(search);
		JSONArray arr=(JSONArray) JSONArray.toJSON(goodList);
		arr=goodUtil.addOwnerMessageAll(arr);
		arr=goodUtil.addThumbConditionAll(arr);
		return outputUtil.lazyLoading(arr, range);
	}

	//详细搜索商品
	public List<Good> searchGood(GoodSearch goodSearch) throws LogicException{
		if(goodSearch.getRange()!=null && goodSearch.getRange()<=0)	throw new  LogicException(501,"range参数格式异常");
		return goodMapper.searchGoods(goodSearch);
	}

	//查看一个交易的全部详细信息
	public JSON getTransactionDetail(Integer id, Integer startId, Integer range) throws LogicException {
		Good good=goodMapper.selectByPrimaryKey(id);
		if(good==null) throw new LogicException(503,"该帖子不存在");
		//如果是第一次访问则给该帖子加上一次访问量
		if(startId==null)	addBrowse(good);
		JSONObject json=(JSONObject) JSONObject.toJSON(good);
		User seller=userMapper.selectPartByPrimaryKey(good.getOwnerId());
		json.put("sellerName", seller.getName());
		json.put("sellerImage", seller.getHeadImage());
		//添加是否点赞数据
		JSONArray arr=new JSONArray();
		arr.add(json);
		arr=goodUtil.addThumbConditionAll(arr);
		json=(JSONObject) arr.get(0);

		//添加评论数据
		ReviewSearch reviewSearch=new ReviewSearch();
		reviewSearch.setRange(range);
		reviewSearch.setStartId(startId);
		reviewSearch.setGoodId(good.getId());
		JSONArray reviewArr=(JSONArray)JSONArray.toJSON(reviewMapper.searchReview(reviewSearch));
		JSONArray array=goodUtil.addReviewerMessageAll(reviewArr);
		json.put("review", outputUtil.lazyLoading(array, range));
		return json;
	}
	//给该交易加上一次访问量
	private void addBrowse(Good good) {
		Good newGood=new Good();
		newGood.setId(good.getId());
		newGood.setBrowseNumber(good.getBrowseNumber()+1);
		goodMapper.updateByPrimaryKeySelective(newGood);

		//如果用户已登录则记录下该用户的访问记录
		try {
			int id = identityUtil.isLogin();
			Browse browse=new Browse();
			browse.setGoodId(good.getId());
			browse.setBrowserId(id);
			browseMapper.insertSelective(browse);
		} catch (LogicException e) {
		}
	}
}
