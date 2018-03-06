package com.thinkgem.jeesite.front.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.modules.hm.service.aboutus.TaboutUsService;

/**
 * 前端展示页面
 * @author hm
 * @version 2018-03-05
 */
@Controller
@RequestMapping(value = "${frontPath}")
public class FrontController {
	
	//关于我们
	@Autowired
	private TaboutUsService taboutUsService;
	
	@RequestMapping(value = {""})
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		
		//刷新关于我们
		taboutUsService.flashAboutConfig();
		
		return "front/index";
	}
	
	
}
