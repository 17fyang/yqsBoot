package com.stu.yqs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.util.ResourceUtils;

public class PropertiesRead {
	private static String staticFileUrl;
	private static String serverUrl;
	static {
		try {
			File f = ResourceUtils.getFile("classpath:yqs.properties");
			InputStream in=new FileInputStream(f);
			Properties p=new Properties();
			p.load(in);
			staticFileUrl=p.getProperty("staticFileUrl");
			serverUrl=p.getProperty("serverUrl");
		} catch (Exception e) {
			System.out.println("[ERROR],启动失败！！！读取自定义配置文件类失败");
			e.printStackTrace();
		}
	}
	public static String getStaticFileUrl() {
		return staticFileUrl;
	}
	public static void setStaticFileUrl(String staticFileUrl) {
		PropertiesRead.staticFileUrl = staticFileUrl;
	}
	public static String getServerUrl() {
		return serverUrl;
	}
	public static void setServerUrl(String serverUrl) {
		PropertiesRead.serverUrl = serverUrl;
	}
}
