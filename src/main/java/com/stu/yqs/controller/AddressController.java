package com.stu.yqs.controller;

import com.stu.yqs.aspect.LogicException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
@RequestMapping("/yqs/address")
public interface AddressController {
    @RequestMapping("/addAction")
    public @ResponseBody
    String addAction(String name, String phoneNumber, String academy, String content, Short isDefault) throws LogicException;

    @RequestMapping("/updateAction")
    public @ResponseBody
    String updateAction(Integer id, String name, String phoneNumber, String academy, String content, Short isDefault) throws LogicException;

    @RequestMapping("/getListAction")
    public @ResponseBody
    String getListAction() throws LogicException;

    @RequestMapping("/getDefaultAction")
    public @ResponseBody
    String getDefaultAction() throws LogicException;
	
    @RequestMapping("/deleteAction")
    public @ResponseBody
    String deleteAction(Integer addressId) throws LogicException;
}
