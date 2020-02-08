package com.stu.yqs.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.aspect.NecessaryPara;
import com.stu.yqs.controller.ReviewController;
import com.stu.yqs.service.ReviewService;
@Controller
public class ReviewControllerImpl implements ReviewController {
	@Autowired
	private ReviewService reviewService;
	
	public String review(@NecessaryPara Integer goodId, @NecessaryPara String content) throws LogicException {
		return reviewService.review(goodId,content).toJSONString();
	}
	public String deleteReview(@NecessaryPara Integer id) throws LogicException {
		return reviewService.deleteReview(id).toJSONString();
	}
	public String thumb(@NecessaryPara Integer goodId) throws LogicException {
		return reviewService.thumb(goodId).toJSONString();
	}
	public String deleteThumb(@NecessaryPara Integer id) throws LogicException {
		return reviewService.deleteThumb(id).toJSONString();
	}

}
