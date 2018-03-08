package com.thinkgem.jeesite.modules.hm.utils.offeic.filetools;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;


  
public class PDFtoIMG {  
	public static String pdftoimg(String rootpath,String pdffilename,String imagefilename)   {
		
		String filename = imagefilename + ".jpg";
		try{
			//String p="C:/csworkspace/dms/service/upload" + "/"+"12341.pdf";     
	          
	        PDDocument doc = PDDocument.load(pdffilename);  
	        int pageCount = doc.getNumberOfPages();  
	        System.out.println(pageCount);  
	        Date start = new Date();  
	        try {  
	            List pages = doc.getDocumentCatalog().getAllPages();  
	            for(int i=0;i<pages.size()&& i<1;i++){  
	                PDPage page = (PDPage) pages.get(i);  
	                @SuppressWarnings("unused")  
	                int width = new Float(page.getTrimBox().getWidth()).intValue();  
	                @SuppressWarnings("unused")  
	                int height = new Float(page.getTrimBox().getHeight()).intValue();  
	                BufferedImage image = page.convertToImage();  
	                ImageIO.write(image, "jpg", new File(rootpath + "/" + imagefilename + ".jpg"));  
	                System.out.println("image in the page -->"+(filename));  
	            }  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }finally{  
	            if(doc != null){  
	                doc.close();  
	            }  
	        }  
	        Date end = new Date();  
	        System.out.println(end.getTime()-start.getTime());  
	        System.out.println("over");  
		}catch(Exception e){
			e.getStackTrace();
		}
        return filename;
    }  
	
	
    @SuppressWarnings("rawtypes")  
    public static void main(String[] args) throws IOException {  
    	
    	/*
        String p="C:/csworkspace/dms/service/upload" + "/"+"12341.pdf";     
         
        
        
        PDDocument doc = PDDocument.load(p);  
        int pageCount = doc.getNumberOfPages();  
        System.out.println(pageCount);  
        Date start = new Date();  
        try {  
            List pages = doc.getDocumentCatalog().getAllPages();  
            for(int i=0;i<pages.size()&& i<1;i++){  
                PDPage page = (PDPage) pages.get(i);  
                @SuppressWarnings("unused")  
                int width = new Float(page.getTrimBox().getWidth()).intValue();  
                @SuppressWarnings("unused")  
                int height = new Float(page.getTrimBox().getHeight()).intValue();  
                BufferedImage image = page.convertToImage();  
                ImageIO.write(image, "jpg", new File("c:/img" + File.separator + (i + 1) + ".jpg"));  
                System.out.println("image in the page -->"+(i+1));  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }finally{  
            if(doc != null){  
                doc.close();  
            }  
        }  
        Date end = new Date();  
        System.out.println(end.getTime()-start.getTime());  
        System.out.println("over");  */
    }  
      
}  
