package com.thinkgem.jeesite.modules.hm.utils.offeic.filetools;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;


/**
 * doc docx格式转换
 */
public class DocConverter {
	private static final int environment = 1;// 环境 1：windows 2:linux
	private String fileString;// (只涉及pdf2swf路径问题)
	private String outputPath = "";// 输入路径 ，如果不设置就输出在默认的位置
	private String fileName;
	private File pdfFile;
	private File swfFile;
	private File docFile;
	
	public DocConverter(String fileString,String viewfilename) {
		ini(fileString,viewfilename);
	}

	/**
	 * 重新设置file
	 * 
	 * @param fileString
	 */
	public void setFile(String fileString,String viewfilename) {
		ini(fileString,viewfilename);
	}

	/**
	 * 初始化
	 * 
	 * @param fileString
	 */
	private void ini(String fileString,String viewfilename) {
		this.fileString = fileString;
		fileName = fileString.substring(0, fileString.lastIndexOf("."));
		docFile = new File(fileString);
		
		pdfFile = new File(docFile.getParent()+"/"+viewfilename + ".pdf");
		swfFile = new File(docFile.getParent()+"/"+viewfilename + ".swf");
	}
	
	/**
	 * 转为PDF
	 * 
	 * @param file
	 */
	private void doc2pdf(String viewfilename) throws Exception {
		if (docFile.exists()) {
			if (!pdfFile.exists()) {
				OpenOfficeConnection connection = new SocketOpenOfficeConnection("127.0.0.1",8100);
//				OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
				try {
					connection.connect();
					DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
					converter.convert(docFile, pdfFile);
					//pdfFile.renameTo(new File(pdfFile.getParent()+"/"+viewfilename+".pdf"));
					
					// close the connection
					connection.disconnect();
					System.out.println("****pdf转换成功，PDF输出：" + pdfFile.getPath()+ "****"+pdfFile.getName());
				} catch (java.net.ConnectException e) {
					e.printStackTrace();
					System.out.println("****swf转换器异常，openoffice服务未启动！****");
					throw e;
				} catch (com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException e) {
					e.printStackTrace();
					System.out.println("****swf转换器异常，读取转换文件失败****");
					throw e;
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
			} else {
				System.out.println("****已经转换为pdf，不需要再进行转化****");
			}
		} else {
			System.out.println("****swf转换器异常，需要转换的文档不存在，无法转换****");
		}
	}
	
	
	/**
	 * 转为PDF
	 * 
	 * @param file
	 */
	private void pdf2pdf2(String viewfilename) throws Exception {
		if (docFile.exists()) {
			if (!pdfFile.exists()) {
				OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
				try {
					connection.connect();
					DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
					converter.convert(docFile, pdfFile);
					//pdfFile.renameTo(new File(pdfFile.getParent()+"/"+viewfilename+".pdf"));
					
					// close the connection
					connection.disconnect();
					System.out.println("****pdf转换成功，PDF输出：" + pdfFile.getPath()+ "****"+pdfFile.getName());
				} catch (java.net.ConnectException e) {
					e.printStackTrace();
					System.out.println("****swf转换器异常，openoffice服务未启动！****");
					throw e;
				} catch (com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException e) {
					e.printStackTrace();
					System.out.println("****swf转换器异常，读取转换文件失败****");
					throw e;
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
			} else {
				System.out.println("****已经转换为pdf，不需要再进行转化****");
			}
		} else {
			System.out.println("****swf转换器异常，需要转换的文档不存在，无法转换****");
		}
	}
	
	
	/**
	 * 转换成 swf
	 */
	@SuppressWarnings("unused")
	private void pdf2swf(String viewfilename) throws Exception {
		Runtime r = Runtime.getRuntime();
		if (!swfFile.exists()) {
			System.out.println("****pdf2swfpdf2swfpdf2swfpdf2swf****:"+pdfFile.getName());
			if (pdfFile.exists()) {
				if (environment == 1) {// windows环境处理
					try {
						Process p = r.exec("C:/tools/SWFTools/pdf2swf.exe "+ pdfFile.getPath() + " -o "+ swfFile.getPath() + " -T 9");
						System.out.print(loadStream(p.getInputStream()));
						System.err.print(loadStream(p.getErrorStream()));
						System.out.print(loadStream(p.getInputStream()));
						//swfFile.renameTo(new File(swfFile.getParent()+"/"+viewfilename+".swf"));
						
						System.err.println("****swf转换成功，文件输出："
								+ swfFile.getPath() + "****");
						if (pdfFile.exists()) {
							pdfFile.delete();
						}

					} catch (IOException e) {
						e.printStackTrace();
						throw e;
					}
				} else if (environment == 2) {// linux环境处理
					try {
						Process p = r.exec("pdf2swf " + pdfFile.getPath()
								+ " -o " + swfFile.getPath() + " -T 9");
						System.out.print(loadStream(p.getInputStream()));
						System.err.print(loadStream(p.getErrorStream()));
						System.err.println("****swf转换成功，文件输出："
								+ swfFile.getPath() + "****");
						if (pdfFile.exists()) {
							pdfFile.delete();
						}
					} catch (Exception e) {
						e.printStackTrace();
						throw e;
					}
				}
			} else {
				System.out.println("****pdf不存在,无法转换****");
			}
		} else {
			System.out.println("****swf已经存在不需要转换****");
		}
	}
	
	/**
	 * 转换成 swf
	 */
	@SuppressWarnings("unused")
	private void pdf2swf(File thepdffile,String viewfilename) throws Exception {
		Runtime r = Runtime.getRuntime();
		if (!swfFile.exists()) {
			System.out.println("****pdf2swfpdf2swfpdf2swfpdf2swf****:"+thepdffile.getName());
			if (thepdffile.exists()) {
				if (environment == 1) {// windows环境处理
					try {
						Process p = r.exec("C:/tools/SWFTools/pdf2swf.exe "+ thepdffile.getPath() + " -o "+ swfFile.getPath() + " -T 9");
						System.out.print(loadStream(p.getInputStream()));
						System.err.print(loadStream(p.getErrorStream()));
						System.out.print(loadStream(p.getInputStream()));
						//swfFile.renameTo(new File(swfFile.getParent()+"/"+viewfilename+".swf"));
						
						System.err.println("****swf转换成功，文件输出："
								+ swfFile.getPath() + "****");
						if (thepdffile.exists()) {
							thepdffile.delete();
						}

					} catch (IOException e) {
						e.printStackTrace();
						throw e;
					}
				} else if (environment == 2) {// linux环境处理
					try {
						Process p = r.exec("thepdffile " + pdfFile.getPath()
								+ " -o " + swfFile.getPath() + " -T 9");
						System.out.print(loadStream(p.getInputStream()));
						System.err.print(loadStream(p.getErrorStream()));
						System.err.println("****swf转换成功，文件输出："
								+ swfFile.getPath() + "****");
						
					} catch (Exception e) {
						e.printStackTrace();
						throw e;
					}
				}
			} else {
				System.out.println("****pdf不存在,无法转换****");
			}
		} else {
			System.out.println("****swf已经存在不需要转换****");
		}
	}

	static String loadStream(InputStream in) throws IOException {

		int ptr = 0;
		in = new BufferedInputStream(in);
		StringBuffer buffer = new StringBuffer();

		while ((ptr = in.read()) != -1) {
			buffer.append((char) ptr);
		}

		return buffer.toString();
	}
	/**
	 * 转换主方法
	 */
	@SuppressWarnings("unused")
	public String conver(String viewfilename) {
		String fileimage = "";
		if (swfFile.exists()) {
			System.out.println("****swf转换器开始工作，该文件已经转换为swf****");
			return null;
		}

		if (environment == 1) {
			System.out.println("****swf转换器开始工作，当前设置运行环境windows****");
		} else {
			System.out.println("****swf转换器开始工作，当前设置运行环境linux****");
		}
		try {
			//转换至PDF
			doc2pdf(viewfilename);
			//获取截图
			//fileimage = getImageByPdf(viewfilename);
			//String rootpath = this.getServletContext().getRealPath("upload");
        	
        	//String thisfilename = rootpath+"/"+filename;
        	
        	fileimage = PDFtoIMG.pdftoimg(pdfFile.getParent(),pdfFile.getPath(),viewfilename);
        	
        	fileimage = pdfFile.getPath();
			System.out.println("fileimage :"+fileimage);
			//转换swf
			//pdf2swf(viewfilename);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
/*
		if (swfFile.exists()) {
			return fileimage;
		} else {
			return null;
		}*/
		return fileimage;
	}
	
	/**
	 * 转换主方法
	 */
	@SuppressWarnings("unused")
	public String converpdf(File thepdffile,String viewfilename) {
		String fileimage = "";
		

		if (environment == 1) {
			System.out.println("****swf转换器开始工作，当前设置运行环境windows****");
		} else {
			System.out.println("****swf转换器开始工作，当前设置运行环境linux****");
		}
		try {
			//转换至PDF
			//doc2pdf(viewfilename);
			//获取截图
//			fileimage = getImageByPdf(thepdffile,viewfilename);
			System.out.println("fileimage :"+fileimage);
			//转换swf
			//pdf2swf(thepdffile,viewfilename);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		if (swfFile.exists()) {
			return fileimage;
		} else {
			return null;
		}
	}

	/**
	 * 返回文件路径
	 * 
	 * @param s
	 */
	public String getswfPath() {
		if (swfFile.exists()) {
			String tempString = swfFile.getPath();
			tempString = tempString.replaceAll("\\\\", "/");
			return tempString;
		} else {
			return "";
		}

	}
	/**
	 * 设置输出路径
	 */
	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
		if (!outputPath.equals("")) {
			String realName = fileName.substring(fileName.lastIndexOf("/"),
					fileName.lastIndexOf("."));
			if (outputPath.charAt(outputPath.length()) == '/') {
				swfFile = new File(outputPath + realName + ".swf");
			} else {
				swfFile = new File(outputPath + realName + ".swf");
			}
		}
	}
	
	
	/**
	 * 设置PDF 文件截图
	 */
	public String getImageByPdf(String viewfilename) {
        if (!pdfFile.exists()) {  
            System.out.println("路径对应的pdf文件不存在!");  
            return null;  
        }  
        try{  
            RandomAccessFile raf = new RandomAccessFile(pdfFile, "r");    
            FileChannel channel = raf.getChannel();    
            ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());    
            PDFFile pdffile = new PDFFile(buf);    
            int num = pdffile.getNumPages();  
            for(int i = 1; i < num; i++){  
                 PDFPage page = pdffile.getPage(1);    
                   
                    // get the width and height for the doc at the default zoom    
                    Rectangle rect = new Rectangle(0, 0, (int) page.getBBox()    
                            .getWidth(), (int) page.getBBox().getHeight());    
           
                    // generate the image    
                    Image img = page.getImage(rect.width, rect.height, // width &    
                            rect, // clip rect    
                            null, // null for the ImageObserver    
                            true, // fill background with white    
                            true // block until drawing is done    
                            );    
           
                    BufferedImage tag = new BufferedImage(rect.width, rect.height,     BufferedImage.TYPE_INT_RGB);    
                    tag.getGraphics().drawImage(img, 0, 0, rect.width, rect.height,null);   
                    
                    String imagefilepath = pdfFile.getParent()+"/"+viewfilename+".jpg";
//                    FileOutputStream out = new FileOutputStream(imagefilepath);  
//                    JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);    
//                    encoder.encode(tag); // JPEG编码    
//                    out.close();   
                    System.out.println("imagefilepath :"+imagefilepath);
                    return viewfilename+".jpg";
            }  
          
        }catch(Exception e){  
            e.printStackTrace();  
            
        }  
		return "";
	}
	
	/**
	 * 设置PDF 文件截图
	 */
	public String getImageByPdf(File thepdffile,String viewfilename) {
		
		System.out.println("获取PDC图片  文件路径："+thepdffile.getParent());
		System.out.println("获取PDC图片  文件路径："+thepdffile.getName());
        if (!thepdffile.exists()) {  
            System.out.println("路径对应的pdf文件不存在!");  
            return null;  
        }  
        try{  
        	//File changepdffile = pdf2pdf(thepdffile,viewfilename);
        	if(!thepdffile.exists()){
        		System.out.println("路径对应的pdf文件不存在!");  
                return null;  
        	}
            RandomAccessFile raf = new RandomAccessFile(thepdffile, "r");    
            FileChannel channel = raf.getChannel();    
            ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());    
            PDFFile pdffile = new PDFFile(buf);    
            int num = pdffile.getNumPages();  
            for(int i = 1; i < num; i++){  
                 PDFPage page = pdffile.getPage(1);    
                   
                    // get the width and height for the doc at the default zoom    
                    Rectangle rect = new Rectangle(0, 0, (int) page.getBBox()    
                            .getWidth(), (int) page.getBBox().getHeight());    
           
                    // generate the image    
                    Image img = page.getImage(rect.width, rect.height, // width &    
                            rect, // clip rect    
                            null, // null for the ImageObserver    
                            true, // fill background with white    
                            true // block until drawing is done    
                            );    
           
                    BufferedImage tag = new BufferedImage(rect.width, rect.height,     BufferedImage.TYPE_INT_RGB);    
                    tag.getGraphics().drawImage(img, 0, 0, rect.width, rect.height,null);   
                    
//                    String imagefilepath = thepdffile.getParent()+"/"+viewfilename+".jpg";
//                    FileOutputStream out = new FileOutputStream(imagefilepath);  
//                    JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);    
//                    encoder.encode(tag); // JPEG编码    
//                    out.close();   
//                    System.out.println("imagefilepath :"+imagefilepath);
                    return viewfilename+".jpg";
            }  
          
        }catch(Exception e){  
            e.printStackTrace();  
            
        }  
		return "";
	}

	/**
	 * 转为PDF
	 * 
	 * @param file
	 */
	private File pdf2pdf(File thepdffile,String viewfilename) throws Exception {
		if (thepdffile.exists()) {
			File changepdfFile = new File(thepdffile.getParent()+"/"+viewfilename + ".pdf");
			if (!changepdfFile.exists()) {
				OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
				try {
					connection.connect();
					DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
					converter.convert(thepdffile, changepdfFile);
					//pdfFile.renameTo(new File(pdfFile.getParent()+"/"+viewfilename+".pdf"));
					
					// close the connection
					connection.disconnect();
					System.out.println("****pdf转换成功，PDF输出：" + changepdfFile.getPath()+ "****"+changepdfFile.getName());
				} catch (java.net.ConnectException e) {
					e.printStackTrace();
					System.out.println("****swf转换器异常，openoffice服务未启动！****");
					throw e;
				} catch (com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException e) {
					e.printStackTrace();
					System.out.println("****swf转换器异常，读取转换文件失败****");
					throw e;
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
			} else {
				System.out.println("****已经转换为pdf，不需要再进行转化****");
			}
			return changepdfFile;
		} else {
			System.out.println("****swf转换器异常，需要转换的文档不存在，无法转换****");
			return null;
		}
		
	}
	
}
