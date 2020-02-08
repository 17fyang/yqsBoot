package com.stu.yqs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stu.yqs.aspect.LogicException;

@Controller
@CrossOrigin
@RequestMapping("/yqs")
public interface ReviewController {
	@RequestMapping("/review")
	 public @ResponseBody String review(Integer goodId,String content)throws LogicException;
	@RequestMapping("/deleteReview")
	 public @ResponseBody String deleteReview(Integer id)throws LogicException;
	@RequestMapping("/thumb")
	 public @ResponseBody String thumb(Integer goodId)throws LogicException;
	@RequestMapping("/deleteThumb")
	 public @ResponseBody String deleteThumb(Integer id)throws LogicException;
}
