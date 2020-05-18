package com.stu.yqs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.stu.yqs.aspect.LogicException;
import net.coobird.thumbnailator.Thumbnails;

@Component
public class ImageUtil {
	@Value("${staticFileUrl}")
	private String staticFileUrl;
	@Value("${serverUrl}")
	private String serverUrl;

	private double compressQuality=0.2;
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
	public boolean checkFileSize(MultipartFile file) throws LogicException {
		if(file.getSize()/1024/1024>5)	throw new LogicException(505,"文件大小过大，最大为5M");
		return true;
	}

	//压缩图片并将图片存到对应的路径中
	public boolean compressFile(MultipartFile file, String absolutePath)  {
		try {
			String originalFilename = file.getOriginalFilename();
			//获取要上传的文件后缀
			String fileExt = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
			//创建文件夹
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
	//生成文件名的方法
	public String[] newFileUrl(String sonFolder,String fileName) {
		String suffixName=fileName.substring(fileName.lastIndexOf("."));
		Calendar cal=Calendar.getInstance();
		String date=cal.get(Calendar.YEAR)+"_"+(cal.get(Calendar.MONTH)+1)+"_"+cal.get(Calendar.DAY_OF_MONTH);
		fileName=UUID.randomUUID()+suffixName;
		String dynamicUrl=sonFolder+"/"+date+"/"+fileName;
		String localFile=staticFileUrl+"/"+sonFolder+"/"+date+"/"+fileName;
		String httpFile=serverUrl+"/file/"+dynamicUrl;
		return new String[] {localFile,httpFile};
	}
	//文件转移
	public boolean fileTransfer(String startUrl,String aimUrl) {
		InputStream in=null;
		OutputStream out=null;
		try {
			in=new FileInputStream(startUrl);
			File outFile=new File(aimUrl);
			if(!outFile.exists())	outFile.getParentFile().mkdirs();
			out=new FileOutputStream(outFile);
			byte buf[]=new byte[2048];
			while((in.read(buf))!=-1) {
				out.write(buf);
			}
		} catch (Exception e) {
			return false;
		}finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	//删除一份文件
	public void deleteFile(String  url) {
		File f=new File(url);
		f.delete();
	}
	public String getStaticFileUrl() {
		return staticFileUrl;
	}
	public String getServerUrl() {
		return serverUrl;
	}
}
