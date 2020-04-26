package com.stu.yqs.ControllerTest;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.stu.yqs.YqsBootApplicationTests;
import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.service.GoodService;

public class GoodServiceTest extends YqsBootApplicationTests{
	@Autowired
	private GoodService goodService;
	
	@Test
	public void newTransactionTest() throws FileNotFoundException {
		String path="classpath:file/test.png";
		File testFile=ResourceUtils.getFile(path);
		MultipartFile multipartFile=this.getMulFileByPath(testFile,path);
		MultipartFile[] file=new MultipartFile[] {multipartFile};
		String name="testName4.26";
		String describe="testDescribe4.26";
		try {
			goodService.newTransaction(file, name, describe, null, null, null, null, null);
			System.out.println("dsad");
		} catch (LogicException e) {
			e.printStackTrace();
		}
	}
	private MultipartFile getMulFileByPath(File f,String picPath) {  
        FileItem fileItem = createFileItem(f,picPath);  
        MultipartFile mfile = new CommonsMultipartFile(fileItem);  
        return mfile;  
    }  
  
    private FileItem createFileItem(File f,String filePath)  
    {  
        FileItemFactory factory = new DiskFileItemFactory(16, null);  
        String textFieldName = "textField";  
        int num = filePath.lastIndexOf(".");  
        String extFile = filePath.substring(num);  
        FileItem item = factory.createItem(textFieldName, "text/plain", true,  
            "MyFileName" + extFile);  
        File newfile =f;
        int bytesRead = 0;  
        byte[] buffer = new byte[8192];  
        try  
        {  
            FileInputStream fis = new FileInputStream(newfile);  
            OutputStream os = item.getOutputStream();  
            while ((bytesRead = fis.read(buffer, 0, 8192))  
                != -1)  
            {  
                os.write(buffer, 0, bytesRead);  
            }  
            os.close();  
            fis.close();  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
        return item;  
    }  
  
}
