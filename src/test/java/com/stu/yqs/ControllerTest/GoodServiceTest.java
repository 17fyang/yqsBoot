package com.stu.yqs.ControllerTest;




import com.stu.yqs.YqsBootApplicationTests;

public class GoodServiceTest extends YqsBootApplicationTests{
	
//	@Test
//	@Ignore
//	public void newTransactionTest() throws FileNotFoundException {
//		String path="classpath:file/test.png";
//		File testFile=ResourceUtils.getFile(path);
//		MultipartFile multipartFile=this.getMulFileByPath(testFile,path);
////		MultipartFile[] file=new MultipartFile[] {multipartFile};
////		String name="testName4.26";
////		String describe="testDescribe4.26";
////		try {
////			goodService.newTransaction(file, name, describe, null, null, null, null, null);
////		} catch (LogicException e) {
////			e.printStackTrace();
////		}
//	}
//	private MultipartFile getMulFileByPath(File f,String picPath) {  
//        FileItem fileItem = createFileItem(f,picPath);  
//        MultipartFile mfile = new CommonsMultipartFile(fileItem);  
//        return mfile;  
//    }  
//  
//    private FileItem createFileItem(File f,String filePath)  
//    {  
//        FileItemFactory factory = new DiskFileItemFactory(16, null);  
//        String textFieldName = "textField";  
//        int num = filePath.lastIndexOf(".");  
//        String extFile = filePath.substring(num);  
//        FileItem item = factory.createItem(textFieldName, "text/plain", true,  
//            "MyFileName" + extFile);  
//        File newfile =f;
//        int bytesRead = 0;  
//        byte[] buffer = new byte[8192];  
//        try  
//        {  
//            FileInputStream fis = new FileInputStream(newfile);  
//            OutputStream os = item.getOutputStream();  
//            while ((bytesRead = fis.read(buffer, 0, 8192))  
//                != -1)  
//            {  
//                os.write(buffer, 0, bytesRead);  
//            }  
//            os.close();  
//            fis.close();  
//        }  
//        catch (IOException e)  
//        {  
//            e.printStackTrace();  
//        }  
//        return item;  
//    }  
//  
}
