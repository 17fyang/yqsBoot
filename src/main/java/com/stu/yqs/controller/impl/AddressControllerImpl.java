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
	public String addAction(@NecessaryPara String content,Short isDefault) throws LogicException {
		return addressService.addAction(content,isDefault).toJSONString();
	}
	@Override
	public String updateAction(@NecessaryPara Integer id,@NecessaryPara String content,Short isDefault) throws LogicException {
		return addressService.updateAction(id,content,isDefault).toJSONString();
	}
	@Override
	public String getListAction() throws LogicException {
		return addressService.getListAction().toJSONString();
	}
	@Override
	public String deleteAction(@NecessaryPara Integer addressId) throws LogicException {
		return addressService.deleteAction(addressId).toJSONString();
	}
}
