package com.thinkgem.jeesite.modules.hm.utils.offeic.filetools;
import java.io.File;

import com.thinkgem.jeesite.modules.hm.utils.offeic.UniqId;

/**
 * doc docx格式转换
 */
public class DocChangePdf {
	
	public static String changefiles(String saveDirectory,File f,String fileName){
		String image = "";
		if(f!=null && f.exists()){
			System.out.println("----------------------->1");
	         //获取上传文件的扩展名
	         String extName=fileName.substring(fileName.lastIndexOf(".")+1);
	         //文件全路径
	         String lastFileName= saveDirectory+"\\" + fileName;
	         //获取需要转换的文件名,将路径名中的'\'替换为'/'
	         String converfilename = saveDirectory.replaceAll("\\\\", "/")+fileName;
	         System.out.println(converfilename);
	         String viewfilename = UniqId.getInstance().getUniqID();
	         //调用转换类DocConverter,并将需要转换的文件传递给该类的构造方法
	         DocConverter d = new DocConverter(converfilename,viewfilename);
	         //调用conver方法开始转换，先执行doc2pdf()将office文件转换为pdf;再执行pdf2swf()将pdf转换为swf;
	         System.out.println("----------------26");
	         image = d.conver(viewfilename);
	     	 //调用getswfPath()方法，打印转换后的swf文件路径
	         //System.out.println(d.getswfPath());
	     	 //生成swf相对路径，以便传递给flexpaper播放器
	         //String swfpath = saveDirectory+d.getswfPath().substring(d.getswfPath().lastIndexOf("/"));
	        // System.out.println(swfpath);
	         System.out.println("image :"+image);
	         return image;
	       }
		return null;
	}
	public static String changeContractFiles(String saveDirectory,File f,String fileName){
		String image = "";
		if(f!=null && f.exists()){
	         //获取上传文件的扩展名
	         String extName=fileName.substring(fileName.lastIndexOf(".")+1);
	         //文件全路径
	         String lastFileName= saveDirectory+"\\" + fileName;
	         //获取需要转换的文件名,将路径名中的'\'替换为'/'
	         String converfilename = saveDirectory.replaceAll("\\\\", "/")+"/"+fileName;
	         System.out.println(converfilename);
	         String viewfilename = fileName.substring(0,fileName.lastIndexOf("."));
	         //调用转换类DocConverter,并将需要转换的文件传递给该类的构造方法
	         DocConverter d = new DocConverter(converfilename,viewfilename);
	         //调用conver方法开始转换，先执行doc2pdf()将office文件转换为pdf;再执行pdf2swf()将pdf转换为swf;
	         
	         image = d.conver(viewfilename);
	     	 //调用getswfPath()方法，打印转换后的swf文件路径
	         //System.out.println(d.getswfPath());
	     	 //生成swf相对路径，以便传递给flexpaper播放器
	         //String swfpath = saveDirectory+d.getswfPath().substring(d.getswfPath().lastIndexOf("/"));
	        // System.out.println(swfpath);
	         System.out.println("image :"+image);
	         return image;
	       }
		return null;
	}
	public static void main(String[] args) {
		File file = new File("C:\\周报（朱俊吉）.xlsx");
		DocChangePdf.changeContractFiles("C:\\", file, file.getName());
	}
	
	public static String changefilesswf(String saveDirectory,File f,String fileName){
		String image = "";
		if(f!=null && f.exists()){
	         //获取上传文件的扩展名
	         String extName=fileName.substring(fileName.lastIndexOf(".")+1);
	         //文件全路径
	         String lastFileName= saveDirectory+"\\" + fileName;
	         //获取需要转换的文件名,将路径名中的'\'替换为'/'
	         String converfilename = saveDirectory.replaceAll("\\\\", "/")+"/"+fileName;
	         System.out.println(converfilename);
	         String viewfilename = UniqId.getInstance().getUniqID();
	         //调用转换类DocConverter,并将需要转换的文件传递给该类的构造方法
	         DocConverter d = new DocConverter(converfilename,viewfilename);
	         //调用conver方法开始转换，先执行doc2pdf()将office文件转换为pdf;再执行pdf2swf()将pdf转换为swf;
	         
	         image = d.converpdf(f,viewfilename);
	     	 //调用getswfPath()方法，打印转换后的swf文件路径
	         System.out.println(d.getswfPath());
	     	 //生成swf相对路径，以便传递给flexpaper播放器
	         String swfpath = saveDirectory+d.getswfPath().substring(d.getswfPath().lastIndexOf("/"));
	         System.out.println(swfpath);
	         System.out.println("image :"+image);
	         return image;
	       }
		return null;
	}

}
