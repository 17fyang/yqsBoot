package com.stu.yqs.controller.impl;

import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.aspect.NecessaryPara;
import com.stu.yqs.controller.CollectController;
import com.stu.yqs.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CollectControllerImpl implements CollectController {
    @Autowired
    private CollectService collectService;
	
    @Override
    public String addCollect(@NecessaryPara Integer goodId) throws LogicException {
        return collectService.addCollect(goodId).toJSONString();
    }

    @Override
    public String listAction(Integer startId, Integer range) throws LogicException {
        return collectService.listAction(startId, range).toJSONString();
    }

    @Override
    public String deleteCollect(@NecessaryPara Integer goodId) throws LogicException {
        return collectService.deleteCollect(goodId).toJSONString();
    }
}
