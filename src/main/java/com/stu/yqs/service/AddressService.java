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
import com.stu.yqs.utils.FormatUtil;
import com.stu.yqs.utils.IdentityUtil;

@Service
public class AddressService {
	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private IdentityUtil identityUtil;
	@Autowired
	private FormatUtil formatUtil;
	
	//新建一个地址
	@Transactional(rollbackFor = {Exception.class, Error.class})
	public JSONObject addAction(String name,String phoneNumber,String academy,String content,Short isDefault) throws LogicException {
		int id=identityUtil.isLogin();
		formatUtil.addressDefault(isDefault);
		formatUtil.academy(academy);
		formatUtil.phoneNumber(phoneNumber);
		long phoneNumber_long=Long.parseLong(phoneNumber);
		Address address=new Address();
		address.setUserId(id);
		address.setName(name);
		address.setPhoneNumber(phoneNumber_long);
		address.setAcademy(academy);
		address.setContent(content);
		address.setIsDefault(isDefault);
		addressMapper.insertSelective(address);
		changeDefault(id,address.getId(),isDefault);
		return new JSONObject();
	}
	//修改地址信息
	public JSONObject updateAction(Integer id,String name,String phoneNumber,String academy,String content,Short isDefault) throws LogicException {
		int userId=identityUtil.isLogin();
		formatUtil.addressDefault(isDefault);
		formatUtil.academy(academy);
		formatUtil.phoneNumber(phoneNumber);
		Long phoneNumber_long=null;
		if(phoneNumber!=null)	phoneNumber_long=Long.parseLong(phoneNumber);
		Address address=addressMapper.selectByPrimaryKey(id);
		if(address==null)	throw new LogicException(501,"该地址不存在");
		if(userId!=address.getUserId())	throw  new LogicException(501,"只能操作自己的地址");
		
		if(name!=null)	address.setName(name);
		if(phoneNumber!=null)	address.setPhoneNumber(phoneNumber_long);
		if(academy!=null)	address.setAcademy(academy);
		if(content!=null)	address.setContent(content);
		if(isDefault!=null)		address.setIsDefault(isDefault);
		addressMapper.updateByPrimaryKeySelective(address);
		changeDefault(userId,id,isDefault);
		return new JSONObject();
	}
	//获取当前登录账号下的所有地址
	public JSONArray getListAction() throws LogicException {
		int id=identityUtil.isLogin();
		List<Address> list=addressMapper.selectByUserId(id);
		return (JSONArray)JSONArray.toJSON(list);
	}
	//获取当前登录账号的默认地址
	public JSONObject getDefaultAction() throws LogicException {
		int id=identityUtil.isLogin();
		Address address=addressMapper.selectDefaultByUserId(id);
		if(address==null)		throw new LogicException(501,"暂无默认地址");
		return (JSONObject)JSONObject.toJSON(address);
	}
	//删除一个地址
	public JSONObject deleteAction(Integer addressId) throws LogicException {
		int userId=identityUtil.isLogin();
		Address address=addressMapper.selectByPrimaryKey(addressId);
		if(address==null)	throw new LogicException(501,"该地址不存在");
		if(userId!=address.getUserId())	throw  new LogicException(501,"只能操作自己的地址");
		addressMapper.deleteByPrimaryKey(addressId);
		return new JSONObject();
	}
	/*
	 * 私有：默认地址调整
	 * 策略：
	 * 1、如果当前账号没有默认地址，则当前地址为默认地址
	 * 2、如果有默认地址且此次请求修改默认地址，则修改
	 */
	private void changeDefault(int userId,int addressId, Short isDefault) throws LogicException {
		List<Address> list=addressMapper.selectByUserId(userId);
		if(list.size()>10)		throw new LogicException(501,"超过最大地址数限制（最大10条）");
		boolean  flag=false;
		Address precentAddress=null;
		for(Address address:list) {
			if(address.getId()==addressId)		precentAddress=address;
			if(address.getId()!=addressId && address.getIsDefault()==1) {
				Short s=0;
				address.setIsDefault(s);
				addressMapper.updateByPrimaryKeySelective(address);
				flag=true;
				break;
			}
		}
		if(!flag) {
			precentAddress.setIsDefault((short)1);
			addressMapper.updateByPrimaryKeySelective(precentAddress);
		}
	}
	
}
