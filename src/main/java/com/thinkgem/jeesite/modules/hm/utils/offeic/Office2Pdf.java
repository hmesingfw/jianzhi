package com.thinkgem.jeesite.modules.hm.utils.offeic;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;  
  
public class Office2Pdf {  
  
 static final int wdDoNotSaveChanges = 0;// 不保存待定的更改。  
 static final int wdFormatPDF = 17;// word转PDF 格式  
 static final int ppSaveAsPDF = 32;// ppt 转PDF 格式  
  
 
 
 public static void main(String[] args) throws IOException {  
  //String source1 = "f:\\test.doc";  
  String source1 = "f:\\zhuanhuan\\金鲁班电梯培训学校教务系统解决方案.docx";  
  String source2 = "f:\\zhuanhuan\\4.xlsx";  
  String source3 = "f:\\zhuanhuan\\教育解决方案2016.pptx";  
  String target1 = "f:\\zhuanhuan\\金鲁班电梯培训学校教务系统解决方案.pdf";  
  String target2 = "f:\\zhuanhuan\\4.pdf";  
  String target3 = "f:\\zhuanhuan\\教育解决方案2016.pdf";  
    
  Office2Pdf pdf = new Office2Pdf();  
  officetopdf("f:\\zhuanhuan\\金鲁班电梯培训学校教务系统解决方案1.docx","f:\\zhuanhuan\\金鲁班电梯培训学校教务系统解决方案12.pdf");
  //pdf.word2pdf(source1, target1);  
 //pdf.excel2pdf(source2, target2);  
 //pdf.ppt2pdf(source3, target3);  
//  pdf.imgToPdf("e:/12345.jpg","e:/xu.pdf");  
 }  
  
 public static String officetopdf(String prefilepath ,String newfilepath) throws IOException{
	 if(prefilepath!=null && prefilepath.length()>0 && prefilepath.indexOf(".")!=-1){
		 String filetype = prefilepath.substring(prefilepath.lastIndexOf(".")+1,prefilepath.length());
		 System.out.println("filetype :"+filetype);;
		 Office2Pdf pdf = new Office2Pdf();
		 if("doc".equals(filetype) || "docx".equals(filetype)){
			 pdf.word2pdf(prefilepath, newfilepath);  
		 }else if("xls".equals(filetype) || "xlsx".equals(filetype)){
			 pdf.excel2pdf(prefilepath, newfilepath);  
		 }else if("ppt".equals(filetype) || "pptx".equals(filetype)){
			 pdf.ppt2pdf(prefilepath, newfilepath);  
		 }else if("jpg".equals(filetype) || "png".equals(filetype) || "gif".equals(filetype)){
			 pdf.imgToPdf(prefilepath,newfilepath);  
		 }
		 
	 }else{
		 return newfilepath;
	 }
	 return newfilepath;
 }
 
 
 public void word2pdf(String source,String target){  
  System.out.println("启动Word");  
  long start = System.currentTimeMillis();  
  ActiveXComponent app = null;  
  try {  
   app = new ActiveXComponent("Word.Application");  
   app.setProperty("Visible", false);  
  
   Dispatch docs = app.getProperty("Documents").toDispatch();  
   System.out.println("打开文档" + source);  
   Dispatch doc = Dispatch.call(docs,//  
     "Open", //  
     source,// FileName  
     false,// ConfirmConversions  
     true // ReadOnly  
     ).toDispatch();  
  
   System.out.println("转换文档到PDF " + target);  
   File tofile = new File(target);  
   if (tofile.exists()) {  
    tofile.delete();  
   }  
   Dispatch.call(doc,//  
     "SaveAs", //  
     target, // FileName  
     wdFormatPDF);  
  
   Dispatch.call(doc, "Close", false);  
   long end = System.currentTimeMillis();  
   System.out.println("转换完成..用时：" + (end - start) + "ms.");  
  } catch (Exception e) {  
   System.out.println("========Error:文档转换失败：" + e.getMessage());  
  } finally {  
   if (app != null)  
    app.invoke("Quit", wdDoNotSaveChanges);  
  }  
 }  
  
 public void ppt2pdf(String source,String target){  
  System.out.println("启动PPT");  
  long start = System.currentTimeMillis();  
  ActiveXComponent app = null;  
  try {  
   app = new ActiveXComponent("Powerpoint.Application");  
   Dispatch presentations = app.getProperty("Presentations").toDispatch();  
   System.out.println("打开文档" + source);  
   Dispatch presentation = Dispatch.call(presentations,//  
     "Open",   
     source,// FileName  
     true,// ReadOnly  
     true,// Untitled 指定文件是否有标题。  
     false // WithWindow 指定文件是否可见。  
     ).toDispatch();  
  
   System.out.println("转换文档到PDF " + target);  
   File tofile = new File(target);  
   if (tofile.exists()) {  
    tofile.delete();  
   }  
   Dispatch.call(presentation,//  
     "SaveAs", //  
     target, // FileName  
     ppSaveAsPDF);  
  
   Dispatch.call(presentation, "Close");  
   long end = System.currentTimeMillis();  
   System.out.println("转换完成..用时：" + (end - start) + "ms.");  
  } catch (Exception e) {  
   System.out.println("========Error:文档转换失败：" + e.getMessage());  
  } finally {  
   if (app != null) app.invoke("Quit");  
  }  
 }  
  
 public void excel2pdf(String source, String target) {  
      System.out.println("启动Excel");  
      long start = System.currentTimeMillis();  
      ActiveXComponent app = new ActiveXComponent("Excel.Application"); // 启动excel(Excel.Application)  
      try {  
      app.setProperty("Visible", false);  
      Dispatch workbooks = app.getProperty("Workbooks").toDispatch();  
      System.out.println("打开文档" + source);  
      Dispatch workbook = Dispatch.invoke(workbooks, "Open", Dispatch.Method, new Object[]{source, new Variant(false),new Variant(false)}, new int[3]).toDispatch();  
      Dispatch.invoke(workbook, "SaveAs", Dispatch.Method, new Object[] {  
      target, new Variant(57), new Variant(false),  
      new Variant(57), new Variant(57), new Variant(false),  
      new Variant(true), new Variant(57), new Variant(true),  
      new Variant(true), new Variant(true) }, new int[1]);  
      Variant f = new Variant(false);  
      System.out.println("转换文档到PDF " + target);  
      Dispatch.call(workbook, "Close", f);  
      long end = System.currentTimeMillis();  
      System.out.println("转换完成..用时：" + (end - start) + "ms.");  
      } catch (Exception e) {  
       System.out.println("========Error:文档转换失败：" + e.getMessage());  
      }finally {  
       if (app != null){  
        app.invoke("Quit", new Variant[] {});  
       }  
      }  
 }  
   
   
   
 public boolean imgToPdf(String imgFilePath, String pdfFilePath)throws IOException {  
     File file=new File(imgFilePath);  
     if(file.exists()){  
     Document document = new Document();  
     FileOutputStream fos = null;  
     try {  
     fos = new FileOutputStream(pdfFilePath);  
     PdfWriter.getInstance(document, fos);  
  
     // 添加PDF文档的某些信息，比如作者，主题等等  
     document.addAuthor("arui");  
     document.addSubject("test pdf.");  
     // 设置文档的大小  
     document.setPageSize(PageSize.A4);  
     // 打开文档  
     document.open();  
     // 写入一段文字  
     //document.add(new Paragraph("JUST TEST ..."));  
     // 读取一个图片  
     Image image = Image.getInstance(imgFilePath);  
     float imageHeight=image.getScaledHeight();  
     float imageWidth=image.getScaledWidth();  
     int i=0;  
     while(imageHeight>500||imageWidth>500){  
     image.scalePercent(100-i);  
     i++;  
     imageHeight=image.getScaledHeight();  
     imageWidth=image.getScaledWidth();  
     System.out.println("imageHeight->"+imageHeight);  
     System.out.println("imageWidth->"+imageWidth);  
     }  
  
     image.setAlignment(Image.ALIGN_CENTER);   
//        //设置图片的绝对位置  
     // image.setAbsolutePosition(0, 0);  
     // image.scaleAbsolute(500, 400);  
     // 插入一个图片  
     document.add(image);  
     } catch (DocumentException de) {  
     System.out.println(de.getMessage());  
     } catch (IOException ioe) {  
     System.out.println(ioe.getMessage());  
     }  
     document.close();  
     fos.flush();  
     fos.close();  
     return true;  
     }else{  
     return false;  
     }  
     }  
} 