package com.thinkgem.jeesite.front.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.modules.hm.entity.news.Znew;
import com.thinkgem.jeesite.modules.hm.service.aboutus.TaboutUsService;
import com.thinkgem.jeesite.modules.hm.service.banner.ZbannerService;
import com.thinkgem.jeesite.modules.hm.service.news.ZnewService;

/**
 * 前端展示页面
 * 
 * @author hm
 * @version 2018-03-05
 */
@Controller
@RequestMapping(value = "${frontPath}")
public class FrontController {

	// 关于我们
	@Autowired
	private TaboutUsService taboutUsService;
	// 轮播图
	@Autowired
	private ZbannerService zbannerService;
	// 新闻资讯管理
	@Autowired
	private ZnewService znewService;

	/**
	 * 首页管理
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("mendId", "1");
		// 刷新关于我们
		taboutUsService.flashAboutConfig();
		// 轮播图
		zbannerService.flashBanner();
		// 新闻资讯
		Page<Znew> znewpage = new Page<Znew>();
		znewpage.setOrderBy("a.update_date DESC LIMIT 0,5");
		Znew znew = new Znew();
		znew.setDelFlag("0");
		Page<Znew> pageNewList = znewService.findPage(znewpage, znew);
		model.addAttribute("pageNewList", pageNewList);

		return "front/index";
	}
	/**
	 * 新闻列表
	 * @param znew
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "news")
	public String news(Znew znew,HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("mendId", "5");
//		推荐新闻
		Znew news = new Znew();
		news.setDelFlag("0");
		news.setIscommend("1");
		List<Znew> list = znewService.findList(news);
		model.addAttribute("comlist", list);
		
//		列表
		Page<Znew> page = znewService.findPage(new Page<Znew>(request, response), znew); 
		model.addAttribute("page", page);
		return "front/news";
	}
	
	/**
	 * 新闻详情
	 * @param znew
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "newdetail")
	public String newdetail(Znew znew, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("mendId", "5");
//		推荐新闻
		Znew news = new Znew();
		news.setDelFlag("0");
		news.setIscommend("1");
		List<Znew> list = znewService.findList(news);
		model.addAttribute("comlist", list);
		
		model.addAttribute("newinfo", znewService.get(znew.getId()));
		return "front/newsDetail";
	}

}
