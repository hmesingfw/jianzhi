package com.thinkgem.jeesite.front.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.modules.hm.entity.doc.Zdoc;
import com.thinkgem.jeesite.modules.hm.entity.docsort.ZdocSort;
import com.thinkgem.jeesite.modules.hm.entity.news.Znew;
import com.thinkgem.jeesite.modules.hm.entity.user.Zuser;
import com.thinkgem.jeesite.modules.hm.service.aboutus.TaboutUsService;
import com.thinkgem.jeesite.modules.hm.service.banner.ZbannerService;
import com.thinkgem.jeesite.modules.hm.service.doc.ZdocService;
import com.thinkgem.jeesite.modules.hm.service.docsort.ZdocSortService;
import com.thinkgem.jeesite.modules.hm.service.news.ZnewService;
import com.thinkgem.jeesite.modules.hm.service.user.ZuserService;

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
//	文档内容管理
	@Autowired
	private ZdocService zdocService;
//	文档分类管理
	@Autowired
	private ZdocSortService zdocSortService;
//	用户信息管理
	@Autowired
	private ZuserService zuserService;
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
		//文档推荐
		List<Zdoc> zoctoplist = zdocService.findDownTop();
		model.addAttribute("zoctoplist", zoctoplist);
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
		
		model.addAttribute("znew", znew);
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

	/**
	 * 文档分类
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "sortlist")
	public String sortlist(Zdoc zdoc, HttpServletRequest request, HttpServletResponse response, Model model){
		model.addAttribute("mendId", "4");	
		//选择的分类的列表
		List<String> sortlist = new ArrayList<String>();
		
		//前台选择的分类ID
		String sort1 = request.getParameter("classflyone");
		String sort2 = request.getParameter("classflytwo");
		String sort3 = request.getParameter("classflythr");
		
		/**分类信息列表*/
		//一级分类
		ZdocSort zdocSort = new ZdocSort();
		zdocSort.setParent("0");
		List<ZdocSort> docsort = zdocSortService.findList(zdocSort);
		model.addAttribute("docsort", docsort);
		
		//将分类信息导入
		if(sort1!=null && !"".equals(sort1)){
			//二级分类
			zdocSort.setParent(sort1);
			docsort = zdocSortService.findList(zdocSort);
			model.addAttribute("docsort2", docsort);
			
			sortlist.add(sort1);
		}
		if(sort2!=null && !"".equals(sort2)){
			//三级分类
			zdocSort.setParent(sort2);
			docsort = zdocSortService.findList(zdocSort);
			model.addAttribute("docsort3", docsort);
			
			sortlist.add(sort2);	
		}
		if(sort3!=null && !"".equals(sort3)){
			sortlist.add(sort3);
		}
		
		zdoc.setSortlist(sortlist);
		//主体内容
		Page<Zdoc> page = zdocService.findPage(new Page<Zdoc>(request, response), zdoc); 
		model.addAttribute("page", page);	
		
		
		model.addAttribute("zdoc", zdoc);
		
		model.addAttribute("classflyone", sort1);
		model.addAttribute("classflytwo", sort2);
		model.addAttribute("classflythr", sort3);
		return "front/sortlist";
	}
	
	
	/**
	 * 增加浏览量
	 */
	@RequestMapping(value = "doclook")
	public String doclook(Zdoc zdoc, HttpServletRequest request, HttpServletResponse response, Model model){
		zdoc = zdocService.get(zdoc);
		zdocService.updateLook(zdoc);
		model.addAttribute("zdoc", zdoc);
		return "front/gotolookdoc";
	}
	
	/**
	 * 跳转登陆页面 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value ="gotologin")
	public String gotologin(HttpServletRequest request, HttpServletResponse response, Model model){
		return "front/login";
	}
	
	
	/**
	 * 登陆
	 * @param zuser
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value ="login")
	public String login(Zuser zuser,HttpServletRequest request, HttpServletResponse response, Model model){
		List<Zuser> userlist = zuserService.findList(zuser);
		if(userlist!=null && userlist.size()==1){
			Zuser user = userlist.get(0);
			if("1".equals(user.getDelFlag())){
				model.addAttribute("msg", "账户不存在");
				return "front/login";
			}
			
			if("2".equals(user.getStatus())){
				model.addAttribute("msg", "账号停用中");
				return "front/login";
			}
			
			if("3".equals(user.getStatus())){
				model.addAttribute("msg", "账号审核中");
				return "front/login";
			}
			request.getSession().setAttribute("myinfo", user);
		}else{
			if(userlist!=null && userlist.size()>1){
				model.addAttribute("msg", "输入异常，请重新输入");
				return "front/login";
			}else{
				model.addAttribute("msg", "账户密码错误，请重新输入");
				return "front/login";
			}			
		}		
		return "front/myinfo";
	}
}
