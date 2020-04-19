package com.stu.yqs.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.controller.SearchController;
import com.stu.yqs.service.SearchService;

public class SearchControllerImpl implements SearchController{
	@Autowired
	private SearchService searchService;
	@Override
	public String indexAction() throws LogicException {
		return searchService.indexAction().toJSONString();
	}
	@Override
	public String helperAction() throws LogicException {
		return searchService.helperAction().toJSONString();
	}
	@Override
	public String clearHistoryAction() throws LogicException {
		return searchService.clearHistoryAction().toJSONString();
	}

}
