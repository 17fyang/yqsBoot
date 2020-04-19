package com.stu.yqs.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.aspect.NecessaryPara;
import com.stu.yqs.controller.IndexController;
import com.stu.yqs.service.IndexService;
@Controller
public class IndexControllerImpl  implements IndexController {
	@Autowired
	private IndexService indexService;
	@Override
	public String recommend() throws LogicException {
		return indexService.recommend().toJSONString();
	}
	@Override
	public String feedback(@NecessaryPara String content) throws LogicException {
		return indexService.feedback(content).toJSONString();
	}
}
