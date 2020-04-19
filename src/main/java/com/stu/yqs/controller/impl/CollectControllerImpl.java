package com.stu.yqs.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.aspect.NecessaryPara;
import com.stu.yqs.controller.CollectController;
import com.stu.yqs.service.CollectService;

@Controller
public class CollectControllerImpl implements CollectController {
	@Autowired
	private CollectService  collectService;
	@Override
	public String addCollect(@NecessaryPara Integer goodId) throws LogicException {
		return collectService.addCollect(goodId).toJSONString();
	}
	@Override
	public String listAction() throws LogicException {
		return collectService.listAction().toJSONString();
	}
	@Override
	public String deleteCollect(@NecessaryPara Integer goodId) throws LogicException {
		return collectService.deleteCollect(goodId).toJSONString();
	}
}
