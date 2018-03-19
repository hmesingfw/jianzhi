package com.thinkgem.jeesite.modules.hm.web.aboutus;

import java.util.HashMap;
import java.util.Map;

public class AboutConfig {
	
	public final static Map<String, String> map = new HashMap<String, String>();
	static{
//		电话
		map.put("aboutPhone", "1");
		
//		地址
		map.put("aboutAdress", "2");
		
//		二维码
		map.put("aboutCode", "3");
		
//		备案号
		map.put("aboutIpc", "4");
		
//		注册相关条约
		map.put("aboutReg", "5");
	}
	
	
}
