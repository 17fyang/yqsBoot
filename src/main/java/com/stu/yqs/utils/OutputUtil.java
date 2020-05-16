package com.stu.yqs.utils;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.domain.search.GoodSearch;

@Component
public class OutputUtil {
	final static int DEFAULT_RANGE=GoodSearch.getDefaultNumber();
	final static String verifyCodeTemplateCode="SMS_182672041";
	final static String addressTemplateCode="SMS_189840834";
	final static String accessKeyId="LTAI91MlauzR6ZnZ";
	final static String accessSecret="GSdt21HHx7bqOyUB6QXWn596OBjnY1";
	public JSONObject lazyLoading(JSONArray list,Integer range) {
		//截取掉多余的部分
		if((range==null && list.size()>DEFAULT_RANGE))	
			while(list.size()>DEFAULT_RANGE)		
				list.remove(DEFAULT_RANGE);

		JSONObject json=new JSONObject();
		JSONArray arr=(JSONArray)JSON.toJSON(list);
		json.put("length", list.size());
		boolean isEnd=false;
		if((range==null && list.size()<DEFAULT_RANGE) || (range!=null && list.size()<range))	isEnd=true;
		json.put("isEnd",isEnd);
		//获取最小id
		if(!isEnd) {
			int min=Integer.MAX_VALUE;
			for(int i=list.size()-1;i>=0;i--) {
				JSONObject temp=(JSONObject) list.get(i);
				if(temp.getIntValue("id")<min)		min=temp.getIntValue("id");
			}
			json.put("nextId",min);
		}
		json.put("arr", arr);
		return json;
	}

	//发送地址信息
	public void addressImfomation(Long sellerPhoneNumber,String sellerName,String customerName,
			Long customerPhoneNumber,String academy) throws LogicException {
		JSONObject json =new JSONObject();
		json.put("n", sellerName);
		json.put("cN", customerName);
		json.put("p", customerPhoneNumber);
		json.put("a", academy);
		String phoneNumber=String.valueOf(sellerPhoneNumber);
		System.out.println("test");
		this.sendMessage( phoneNumber,json, addressTemplateCode);
	}

	//发送验证码短信
	public void verifyCode(String phoneNumber,int code) throws LogicException {
		JSONObject json =new JSONObject();
		json.put("code", code);
		this.sendMessage(phoneNumber, json, verifyCodeTemplateCode);
	}

	//发送短信
	public void sendMessage(String phoneNumber,JSONObject json,String templateCode) throws LogicException {
		new Thread() {
			public void run() {
				DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessSecret);
				IAcsClient client = new DefaultAcsClient(profile);
				CommonRequest send = new CommonRequest();
				send.setMethod(MethodType.POST);
				send.setDomain("dysmsapi.aliyuncs.com");
				send.setVersion("2017-05-25");
				send.setAction("SendSms");
				send.putQueryParameter("RegionId", "cn-hangzhou");
				send.putQueryParameter("PhoneNumbers", phoneNumber);
				send.putQueryParameter("SignName", "益启善");
				send.putQueryParameter("TemplateCode", templateCode);
				send.putQueryParameter("TemplateParam", json.toString());
				try {
//					CommonResponse response = null;
					CommonResponse response = client.getCommonResponse(send);
					JSONObject newJson=(JSONObject) JSONObject.parse(response.getData());
					if(!newJson.getString("Message").equals("OK"))	System.out.println(newJson.getString("Message"));
				} catch (ServerException e) {
					e.printStackTrace();
				} catch (ClientException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}
