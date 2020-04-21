package com.stu.yqs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.dao.AddressMapper;
import com.stu.yqs.domain.Address;
import com.stu.yqs.utils.IdentityUtil;

@Service
public class AddressService {
	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private IdentityUtil identityUtil;
	
	@Transactional
	public JSONObject addAction(String content,Short isDefault) throws LogicException {
		int id=identityUtil.isLogin();
		if(isDefault!=null && isDefault!=0 && isDefault!=1)	throw new LogicException(501,"isDefalt参数异常，0或1");
		Address address=new Address();
		address.setUserId(id);
		address.setContent(content);
		address.setIsDefault(isDefault);
		addressMapper.insertSelective(address);
		System.out.println(address.getId());
		if(isDefault!=null && isDefault==1)		changeDefault(id,address.getId());
		return new JSONObject();
	}
	public JSONObject updateAction(Integer id,String content,Short isDefault) throws LogicException {
		int userId=identityUtil.isLogin();
		if(isDefault!=null && isDefault!=0 && isDefault!=1)	throw new LogicException(501,"isDefalt参数异常，0或1");
		Address address=addressMapper.selectByPrimaryKey(id);
		if(address==null)	throw new LogicException(501,"该地址不存在");
		if(userId!=address.getUserId())	throw  new LogicException(501,"只能操作自己的地址");
		
		address.setContent(content);
		address.setIsDefault(isDefault);
		addressMapper.updateByPrimaryKeySelective(address);
		if(isDefault!=null && isDefault==1)		changeDefault(userId,id);
		return new JSONObject();
	}
	public JSONArray getListAction() throws LogicException {
		int id=identityUtil.isLogin();
		List<Address> list=addressMapper.selectByUserId(id);
		return (JSONArray)JSONArray.toJSON(list);
	}
	public JSONObject deleteAction(Integer addressId) throws LogicException {
		int userId=identityUtil.isLogin();
		Address address=addressMapper.selectByPrimaryKey(addressId);
		if(address==null)	throw new LogicException(501,"该地址不存在");
		if(userId!=address.getUserId())	throw  new LogicException(501,"只能操作自己的地址");
		addressMapper.deleteByPrimaryKey(addressId);
		return new JSONObject();
	}
	
	private void changeDefault(int userId,int addressId) throws LogicException {
		List<Address> list=addressMapper.selectByUserId(userId);
		if(list.size()>10)		throw new LogicException(501,"超过最大地址数限制（最大10条）");
		for(Address a:list) {
			if(a.getId()!=addressId && a.getIsDefault()==1) {
				Short s=0;
				a.setIsDefault(s);
				addressMapper.updateByPrimaryKeySelective(a);
				break;
			}
		}
	}
}
