package com.stu.yqs.utils;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stu.yqs.aspect.LogicException;
@Component
public class SessionUtil {
	@Autowired
	private HttpServletRequest request;
	
	public String getLoginSession() {
		return String.valueOf(request.getSession().getAttribute("id"));
	}
	
	public void saveLoginSession(int id) {
		HttpSession session=request.getSession();
		session.setAttribute("id", id);
		session.setMaxInactiveInterval(60*60*24*365*4);
	}
	
	
	@SuppressWarnings("unchecked")
	public  synchronized void saveImageSession(String url) throws LogicException {
		HttpSession session=request.getSession();
		List<String>urlList=(List<String>) session.getAttribute("imageUrl");
		if(urlList==null) {
			urlList=new LinkedList<String>();
			session.setAttribute("imageUrl", urlList);
		}
		if(urlList.size()>=9)	throw new LogicException(501,"最多允许9张图片");
		session.setMaxInactiveInterval(30*60);//以秒为单位
		urlList.add(url);
	}
	@SuppressWarnings("unchecked")
	public List<String> getImageSession() {
		HttpSession session=request.getSession();
		List<String>urlList=(List<String>) session.getAttribute("imageUrl");
		return urlList;
	}
	public void removeImageSession() {
		request.getSession().removeAttribute("imageUrl");
	}
	
	public HttpSession getSession() {
		return request.getSession();
	}
}
