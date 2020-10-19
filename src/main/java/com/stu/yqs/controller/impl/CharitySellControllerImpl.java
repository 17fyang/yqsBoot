package com.stu.yqs.controller.impl;

import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.aspect.NecessaryPara;
import com.stu.yqs.controller.CharitySellController;
import com.stu.yqs.service.CharitySellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class CharitySellControllerImpl implements CharitySellController {
    @Autowired
    private CharitySellService charitySellService;

    @Override
    public String newTransaction(@NecessaryPara MultipartFile[] file, @NecessaryPara String name,
                                 @NecessaryPara String describe, String tag, Double price, Double originalPrice,
                                 Double postage, String isNew, String freeShipping) throws LogicException {

        return charitySellService.newTransaction(file, name, describe, tag, price, originalPrice, postage, freeShipping).toJSONString();
    }

    @Override
    public String deleteTransaction(@NecessaryPara Integer transactionId) throws LogicException {
        return charitySellService.deleteTransaction(transactionId).toJSONString();
    }
	
    @Override
    public String getTransaction(Integer startId, @NecessaryPara Integer range, String academy, String keyword, String tag) throws LogicException {
        return charitySellService.getTransaction(startId, range, academy, keyword, tag).toJSONString();
    }

    @Override
    public String getTransactionDetail(@NecessaryPara Integer id, Integer startId, Integer range) throws LogicException {
        return charitySellService.getTransactionDetail(id, startId, range).toJSONString();
    }
}
