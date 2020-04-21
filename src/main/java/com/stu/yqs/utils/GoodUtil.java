package com.stu.yqs.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.dao.GoodMapper;
import com.stu.yqs.domain.Good;

@Service
public class GoodUtil {
	@Autowired
	private GoodMapper goodMapper;
	
	public int stateNormal(Integer goodId) throws LogicException {
		Good good=goodMapper.selectByPrimaryKey(goodId);
		if(good==null)	throw new LogicException(501,"不存在该商品");
		if(good.getState().equals("2"))	throw new LogicException(501,"该商品已被购买");
		if(good.getState().equals("4"))	throw new LogicException(501,"该商品已下架");
		return good.getOwnerId();
	}
	public int hasGood(Integer goodId) throws LogicException {
		Good good=goodMapper.selectByPrimaryKey(goodId);
		if(good==null)	throw new LogicException(501,"不存在该商品");
		return good.getOwnerId();
	}
}
