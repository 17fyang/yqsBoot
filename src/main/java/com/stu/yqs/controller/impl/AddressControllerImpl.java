package com.stu.yqs.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.aspect.NecessaryPara;
import com.stu.yqs.controller.AddressController;
import com.stu.yqs.service.AddressService;
@Controller
public class AddressControllerImpl implements AddressController{
	@Autowired
	private AddressService addressService;
	
	@Override
	public String addAction(@NecessaryPara String name,@NecessaryPara String phoneNumber,
			@NecessaryPara String academy,@NecessaryPara String content,Short isDefault) throws LogicException {
		return addressService.addAction(name,phoneNumber,academy,content,isDefault).toJSONString();
	}
	@Override
	public String updateAction(@NecessaryPara Integer id,String name,String phoneNumber,
			String academy,String content,Short isDefault) throws LogicException {
		return addressService.updateAction(id,name,phoneNumber,academy,content,isDefault).toJSONString();
	}
	@Override
	public String getListAction() throws LogicException {
		return addressService.getListAction().toJSONString();
	}
	@Override
	public String getDefaultAction() throws LogicException {
		return addressService.getDefaultAction().toJSONString();
	}
	@Override
	public String deleteAction(@NecessaryPara Integer addressId) throws LogicException {
		return addressService.deleteAction(addressId).toJSONString();
	}
	
}
