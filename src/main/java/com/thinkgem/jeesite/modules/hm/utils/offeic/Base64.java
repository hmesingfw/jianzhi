package com.thinkgem.jeesite.modules.hm.utils.offeic;

import java.io.UnsupportedEncodingException;  

import sun.misc.*;  
  
public class Base64 {  
    // 加密  
    public static String getBase64(String str) {  
        byte[] b = null;  
        String s = null;  
        try {  
            b = str.getBytes("utf-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        if (b != null) {  
            s = new BASE64Encoder().encode(b);  
        }  
        return s;  
    }  
  
    // 解密  
    public static String getFromBase64(String s) {  
        byte[] b = null;  
        String result = null;  
        if (s != null) {  
            BASE64Decoder decoder = new BASE64Decoder();  
            try {  
                b = decoder.decodeBuffer(s);  
                result = new String(b, "utf-8");  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return result;  
    }  
    
    public static void main(String[] args){
    	String tt = "fileidtype=1&fileid=2&gongxiangtype=1";
    	String tt2 = "ZmlsZWlkPTYmZ29uZ3hpYW5ndHlwZT0x";
    	System.out.println("加密:"+getBase64(tt));;
    	System.out.println("解密:"+getFromBase64(tt2));;
    	
    	String tt3 = "fileid=111&gongxiangtype=2";
    	String[] tt4 = tt3.split("&");
    	System.out.println("fileid:"+tt4[0].substring(tt4[0].indexOf("=")+1,tt4[0].length()));;
    	System.out.println("fileid:"+tt4[1].substring(tt4[1].indexOf("=")+1,tt4[1].length()));;
    	
    	String s = "ZmlsZWlkPTYmZ29uZ3hpYW5ndHlwZT0x";
    	byte[] b = null;  
    	String result = null;  
        if (s != null) {  
            BASE64Decoder decoder = new BASE64Decoder();  
            try {  
                b = decoder.decodeBuffer(s);  
                result = new String(b, "utf-8");  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        
        System.out.println("result:"+result);;
    }
} 
