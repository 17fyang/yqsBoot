package com.stu.yqs.controller.impl;

import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.aspect.NecessaryPara;
import com.stu.yqs.controller.SearchController;
import com.stu.yqs.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SearchControllerImpl implements SearchController {
    @Autowired
    private SearchService searchService;

    @Override
    public String indexAction() throws LogicException {
        return searchService.indexAction().toJSONString();
    }

    @Override
    public String helperAction(@NecessaryPara String input, String tag) throws LogicException {
        return searchService.helperAction(input, tag).toJSONString();
    }
	
    @Override
    public String clearHistoryAction() throws LogicException {
        return searchService.clearHistoryAction().toJSONString();
    }

}
