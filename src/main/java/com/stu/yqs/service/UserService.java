package com.stu.yqs.service;

import com.alibaba.fastjson.JSONObject;
import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.dao.UserMapper;
import com.stu.yqs.domain.EnumPackage.Academy;
import com.stu.yqs.domain.EnumPackage.IdType;
import com.stu.yqs.domain.User;
import com.stu.yqs.utils.FormatUtil;
import com.stu.yqs.utils.IdentityUtil;
import com.stu.yqs.utils.ImageUtil;
import com.stu.yqs.utils.OutputUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/*
 * date：2020.1.17
 * author：yf
 * detail：
 */
@Service
public class UserService {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private IdentityUtil identityUtil;
    @Autowired
    private OutputUtil outputUtil;
    @Autowired
    private ImageUtil imageUtil;
    @Autowired
    private FormatUtil formatUtil;
    @Autowired
    private UserMapper userMapper;

    //获取已登录用户个人信息
    public JSONObject getInfo() throws LogicException {
        int id = identityUtil.isLogin();
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) throw new LogicException(502, "此id不存在");
        JSONObject json = (JSONObject) JSONObject.toJSON(user);
        json.remove("password");
        return json;
    }

    //用户登录
    public JSONObject login(String phoneNumber, String password) throws LogicException {
        formatUtil.phoneNumber(phoneNumber);
        long phoneNumber_long = Long.parseLong(phoneNumber);
        User user = userMapper.selectByPhoneNumber(phoneNumber_long);
        if (user == null) throw new LogicException(502, "未找到该用户");
        else if (!user.getPassword().equals(password)) throw new LogicException(503, "输入密码错误");
        JSONObject json = (JSONObject) JSONObject.toJSON(user);
        json.remove("password");
        HttpSession session = request.getSession();
        session.setAttribute("id", user.getId());
        session.setMaxInactiveInterval(60 * 60 * 24 * 365 * 4);
        return json;
    }
	
    //用户注册，需要先获取验证码
    public JSONObject register(String phoneNumber, String password, String academy, String verification) throws LogicException {
        formatUtil.phoneNumber(phoneNumber);
        long phoneNumber_long = Long.parseLong(phoneNumber);
        identityUtil.verificationIsEqual(phoneNumber, verification);
        User user = new User();
        user.setPhoneNumber(phoneNumber_long);
        user.setPassword(password);
        user.setIdType(IdType.format("注册用户"));
        user.setAcademy(Academy.format(academy));
        user.setName(academy + "_" + phoneNumber_long % 10000);
        user.setHeadImage("http://120.79.175.145:80/file/headImage/default.png");
        User selectUser = userMapper.selectByPhoneNumber(phoneNumber_long);
        if (selectUser != null) throw new LogicException(502, "该账号已存在");
        userMapper.insertSelective(user);
        JSONObject json = (JSONObject) JSONObject.toJSON(user);
        json.remove("password");
        return json;
    }

    //获取验证码
    public JSONObject verificationCode(String phoneNumber) throws LogicException {
        formatUtil.phoneNumber(phoneNumber);
        //生成验证码
        int random = (int) (Math.random() * 900000) + 100000;
        String verification = String.valueOf(random);
        JSONObject json = new JSONObject();
        json.put("code", verification);

        //测试代码
//		int test=1;
//		if(test==1) {
//			HttpSession session=request.getSession();
//			session.setAttribute("verificationCode", phoneNumber+"_123456");
//			session.setAttribute("verificationTime", new Date());
//			return new JSONObject();
//		}

        outputUtil.verifyCode(phoneNumber, Integer.parseInt(verification));

        //记录验证码
        HttpSession session = request.getSession();
        session.setAttribute("verificationCode", phoneNumber + "_" + verification);
        session.setAttribute("verificationTime", new Date());
        return new JSONObject();
    }

    //修改密码，需要先获取验证码
    public JSONObject modifyPassword(String phoneNumber, String newPassword, String verification) throws LogicException {
        formatUtil.phoneNumber(phoneNumber);
        long phoneNumber_long = Long.parseLong(phoneNumber);
        identityUtil.verificationIsEqual(phoneNumber, verification);
        User user = userMapper.selectByPhoneNumber(phoneNumber_long);
        if (user == null) throw new LogicException(502, "未找到该用户");
        user.setPassword(newPassword);
        userMapper.updateByPrimaryKeySelective(user);
        return new JSONObject();
    }

    //注销登录
    public JSONObject logout() {
        request.getSession().removeAttribute("id");
        return new JSONObject();
    }

    //修改用户头像
    public JSONObject modifyHeadImage(MultipartFile file) throws LogicException {
        int id = identityUtil.isLogin();
        if (file == null || file.isEmpty()) throw new LogicException(503, "上传文件为空");

        String newUrl[] = imageUtil.newFileUrl("headImage", file);
        String localUrl = newUrl[0];
        String httpUrl = newUrl[1];
        boolean compressSuccess = imageUtil.compressFile(file, localUrl);
        if (!compressSuccess) throw new LogicException(504, "图片格式异常");
        //数据库处理
        User user = userMapper.selectByPrimaryKey(id);
        user.setHeadImage(httpUrl);
        userMapper.updateByPrimaryKeySelective(user);
        JSONObject json = new JSONObject();
        json.put("headImage", httpUrl);
        return json;
    }

    //修改用户信息
    public JSONObject modifyInfo(String name, String emailNumber, String academy) throws LogicException {
        int id = identityUtil.isLogin();
        User user = userMapper.selectByPrimaryKey(id);
        if (name != null) user.setName(name);
        if (emailNumber != null) user.setEmailNumber(emailNumber);
        if (academy != null) user.setAcademy(Academy.format(academy));
        userMapper.updateByPrimaryKeySelective(user);
        return (JSONObject) JSONObject.toJSON(user);
    }
}
