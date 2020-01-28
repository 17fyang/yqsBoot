package com.stu.yqs.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.stu.yqs.exception.LogicException;

import net.coobird.thumbnailator.Thumbnails;
public class ImageUtil {
	private static String staticFileUrl=PropertiesRead.getStaticFileUrl();
	private static String serverUrl=PropertiesRead.getServerUrl();
	
	private static double compressQuality=0.2;
	private static List<String> fileTypeList=new ArrayList<String>();
	static {
		fileTypeList.add("jpg");
		fileTypeList.add("jpeg");
		fileTypeList.add("png");
		fileTypeList.add("JPG");
		fileTypeList.add("JPEG");
		fileTypeList.add("PNG");
	}
	//返回图片大小是否符合要求
	public static boolean checkFileSize(MultipartFile file) throws LogicException {
		if(file.getSize()/1024/1024>5)	throw new LogicException(505,"文件大小过大，最大为5M");
		return true;
	}
	
	
	//压缩图片并将图片存到对应的路径中
	public static boolean compressFile(MultipartFile file, String absolutePath)  {
		try {
			String originalFilename = file.getOriginalFilename();
			//获取要上传的文件后缀
			String fileExt = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
			File tempFile = new File(absolutePath);
			if(!tempFile.exists())	tempFile.getParentFile().mkdirs();
			if (fileTypeList.contains(fileExt)) {
				//如果文件类型符合，则只压缩就好了
				Thumbnails.of(file.getInputStream())
				.scale(1f)
				.outputQuality(compressQuality)
				.toFile(tempFile);
			} else {
				//如果文件类型不在指定的集合内。则要转格式，并压缩
				Thumbnails.of(file.getInputStream())
				.scale(1f)
				.outputFormat(fileTypeList.get(0))
				.outputQuality(compressQuality)
				.toFile(tempFile);
				originalFilename = originalFilename.replace("." + fileExt, "." + fileTypeList.get(0));
			}
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	private String localFile=null;
	private String httpFile=null;
	//生成文件名的方法
	public void newFileUrl(String sonFolder,MultipartFile file) {
		String fileName=file.getOriginalFilename();
		String suffixName=fileName.substring(fileName.lastIndexOf("."));
		Calendar cal=Calendar.getInstance();
		String date=cal.get(Calendar.YEAR)+"_"+cal.get(Calendar.MONTH)+"_"+cal.get(Calendar.DAY_OF_MONTH);
		fileName=UUID.randomUUID()+suffixName;
		this.localFile=staticFileUrl+"/"+sonFolder+"/"+date+"/"+fileName;
		String dynamicUrl=sonFolder+"/"+date+"/"+fileName;
		this.httpFile=serverUrl+"/file/"+dynamicUrl;
	}
	public String getLocalFile() {
		return localFile;
	}
	public String getHttpFile() {
		return httpFile;
	}


	public String getServerUrl() {
		return serverUrl;
	}

	
}
