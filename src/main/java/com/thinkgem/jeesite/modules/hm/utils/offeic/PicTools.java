package com.thinkgem.jeesite.modules.hm.utils.offeic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class PicTools {

private Font font = new Font("", Font.PLAIN, 20);// 添加字体的属性设置

private Graphics2D g = null;

private int fontsize = 0;

private int x = 0;

private int y = 0;

/**

* 导入本地图片到缓冲区

*/

public BufferedImage loadImageLocal(String imgName) {

try {

return ImageIO.read(new File(imgName));

} catch (IOException e) {

System.out.println(e.getMessage());

}

return null;

}

/**

* 导入网络图片到缓冲区

*/

public BufferedImage loadImageUrl(String imgName) {

try {

URL url = new URL(imgName);

return ImageIO.read(url);

} catch (IOException e) {

System.out.println(e.getMessage());

}

return null;

}

/**

* 生成新图片到本地

*/

public void writeImageLocal(String newImage, BufferedImage img) {

if (newImage != null && img != null) {

try {

File outputfile = new File(newImage);

ImageIO.write(img, "jpg", outputfile);

} catch (IOException e) {

System.out.println(e.getMessage());

}

}

}

/**

* 设定文字的字体等

*/

public void setFont(String fontStyle, int fontSize) {

this.fontsize = fontSize;

this.font = new Font(fontStyle, Font.PLAIN, fontSize);

}

/**

* 修改图片,返回修改后的图片缓冲区（只输出一行文本）

*/

public BufferedImage modifyImage(BufferedImage img, Object content, int x,

int y) {

try {

int w = img.getWidth();

int h = img.getHeight();

g = img.createGraphics();

g.setBackground(Color.WHITE);

g.setColor(Color.RED);

if (this.font != null)

g.setFont(this.font);

// 验证输出位置的纵坐标和横坐标

if (x >= h || y >= w) {

this.x = h - this.fontsize + 2;

this.y = w;

} else {

this.x = x;

this.y = y;

}

if (content != null) {

g.drawString(content.toString(), this.x, this.y);

}

g.dispose();

} catch (Exception e) {

System.out.println(e.getMessage());

}

return img;

}

/**

* 修改图片,返回修改后的图片缓冲区（输出多个文本段） xory：true表示将内容在一行中输出；false表示将内容多行输出

*/

public BufferedImage modifyImage(BufferedImage img, Object[] contentArr,

int x, int y, boolean xory) {

try {

int w = img.getWidth();

int h = img.getHeight();

g = img.createGraphics();

g.setBackground(Color.WHITE);

g.setColor(Color.RED);

if (this.font != null)

g.setFont(this.font);

// 验证输出位置的纵坐标和横坐标

if (x >= h || y >= w) {

this.x = h - this.fontsize + 2;

this.y = w;

} else {

this.x = x;

this.y = y;

}

if (contentArr != null) {

int arrlen = contentArr.length;

if (xory) {

for (int i = 0; i < arrlen; i++) {

g.drawString(contentArr[i].toString(), this.x, this.y)

;

this.x += contentArr[i].toString().length()

* this.fontsize / 2 + 5;// 重新计算文本输出位置

}

} else {

for (int i = 0; i < arrlen; i++) {

g.drawString(contentArr[i].toString(), this.x, this.y);

this.y += this.fontsize + 2;// 重新计算文本输出位置

}

}

}

g.dispose();

} catch (Exception e) {

System.out.println(e.getMessage());

}

return img;

}

/**

* 修改图片,返回修改后的图片缓冲区（只输出一行文本）

*

* 时间:2007-10-8

*

* @param img

* @return

*/

public BufferedImage modifyImageYe(BufferedImage img) {

try {

int w = img.getWidth();

int h = img.getHeight();

g = img.createGraphics();

g.setBackground(Color.WHITE);

g.setColor(Color.RED);

if (this.font != null)

g.setFont(this.font);

g.drawString("www.hi.baidu.com?xia_mingjian", w - 85, h - 5);

g.dispose();

} catch (Exception e) {

System.out.println(e.getMessage());

}

return img;

}

public BufferedImage modifyImagetogeter(BufferedImage b, BufferedImage d) {

try {
	
int w = b.getWidth();

int h = b.getHeight();

g = d.createGraphics();

g.drawImage(b, 267, 1010, w, h, null);

g.dispose();

} catch (Exception e) {

System.out.println(e.getMessage());

}

return d;

}



public static void disposeImage(BufferedImage src,  
        String outImgPath, int new_w, int new_h) {  
    // 得到图片  
    int old_w = src.getWidth();  
    // 得到源图宽  
    int old_h = src.getHeight();  
    // 得到源图长  
    BufferedImage newImg = null;  
    // 判断输入图片的类型  
    switch (src.getType()) {  
    case 13:  
        // png,gifnewImg = new BufferedImage(new_w, new_h,  
        // BufferedImage.TYPE_4BYTE_ABGR);  
        break;  
    default:  
        newImg = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);  
        break;  
    }  
    Graphics2D g = newImg.createGraphics();  
    // 从原图上取颜色绘制新图  
    g.drawImage(src, 0, 0, old_w, old_h, null);  
    g.dispose();  
    // 根据图片尺寸压缩比得到新图的尺寸  
    newImg.getGraphics().drawImage(  
            src.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0, 0,  
            null);  
    // 调用方法输出图片文件  
    OutImage(outImgPath, newImg);  
}  

/** 
 * * 将图片文件输出到指定的路径，并可设定压缩质量 * * @param outImgPath * @param newImg * @param 
 * per 
 */  
private static void OutImage(String outImgPath, BufferedImage newImg) {  
    // 判断输出的文件夹路径是否存在，不存在则创建  
    File file = new File(outImgPath);  
    if (!file.getParentFile().exists()) {  
        file.getParentFile().mkdirs();  
    }// 输出到文件流  
    try {  
        ImageIO.write(newImg, outImgPath.substring(outImgPath  
                .lastIndexOf(".") + 1), new File(outImgPath));  
    } catch (Exception e) {  
        e.printStackTrace();  
    } 
}  

public static void main(String[] args) {

	PicTools tt = new PicTools();
	//原图
	BufferedImage d = tt.loadImageLocal("C:\\dipian.jpg");
	//需要嵌入的二维码图片
	BufferedImage b = tt.loadImageLocal("C:\\erweima.png");
	//压缩二维码图片尺寸  方便嵌入
	disposeImage(b,"C:\\erweima2.png",188,188);
	//压缩后 可生成新图片
	BufferedImage c = tt.loadImageLocal("C:\\erweima2.png");
	
	//嵌入图片
	tt.writeImageLocal("D:\\cc.jpg", tt.modifyImagetogeter(c, d));
	//删除压缩后的临时图片
	File tempfile = new File("C:\\erweima2.png");
	tempfile.delete();
	
	

}

}
