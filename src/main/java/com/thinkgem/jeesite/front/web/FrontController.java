package com.thinkgem.jeesite.front.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.hm.entity.course.Zcourse;
import com.thinkgem.jeesite.modules.hm.entity.course_sort.ZcourseSort;
import com.thinkgem.jeesite.modules.hm.entity.doc.Zdoc;
import com.thinkgem.jeesite.modules.hm.entity.docsort.ZdocSort;
import com.thinkgem.jeesite.modules.hm.entity.news.Znew;
import com.thinkgem.jeesite.modules.hm.entity.order.ZcourseOrder;
import com.thinkgem.jeesite.modules.hm.entity.user.Zuser;
import com.thinkgem.jeesite.modules.hm.entity.userdoc.ZuserDoc;
import com.thinkgem.jeesite.modules.hm.service.aboutus.TaboutUsService;
import com.thinkgem.jeesite.modules.hm.service.banner.ZbannerService;
import com.thinkgem.jeesite.modules.hm.service.course.ZcourseService;
import com.thinkgem.jeesite.modules.hm.service.course_sort.ZcourseSortService;
import com.thinkgem.jeesite.modules.hm.service.doc.ZdocService;
import com.thinkgem.jeesite.modules.hm.service.docsort.ZdocSortService;
import com.thinkgem.jeesite.modules.hm.service.news.ZnewService;
import com.thinkgem.jeesite.modules.hm.service.order.ZcourseOrderService;
import com.thinkgem.jeesite.modules.hm.service.user.ZuserService;
import com.thinkgem.jeesite.modules.hm.service.userdoc.ZuserDocService;

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
//	用户文档下载记录管理
	@Autowired
	private ZuserDocService zuserDocService;
//	课程分类管理
	@Autowired
	private ZcourseSortService zcourseSortService;
//	课程信息管理
	@Autowired
	private ZcourseService zcourseService;
//	课程订单信息
	@Autowired
	private ZcourseOrderService zcourseOrderService;
	/**
	 * 首页管理
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"index", ""})
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
		//课程推荐
		Zcourse zcourse = new Zcourse();
		zcourse.setDelFlag("1");
		zcourse.setIscommend("1");
		Page<Zcourse> coursepage = new Page<Zcourse>();
		coursepage.setPageSize(8);
		Page<Zcourse> zcoursepage = zcourseService.findPage(coursepage, zcourse);
		model.addAttribute("zcoursepage", zcoursepage);
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
		Zuser user = (Zuser)request.getSession().getAttribute("sessionMyinfo");
		
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
		if(user==null || StringUtils.isBlank(user.getId())) {
			zdoc.setUsertype("2");
		}else{
			//当前用户是不是注册用户
			if("2".equals(user.getType())){
				zdoc.setUsertype("2");
			}
		}
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
     * 下载文件-本地
     * @param path
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "docDown")
    public String docDown(@RequestParam(required=true, value="docId")String docId,HttpServletRequest request, HttpServletResponse response) {
    	Zuser user = (Zuser)request.getSession().getAttribute("sessionMyinfo");
		if(user==null || StringUtils.isBlank(user.getId())) {
			return "请登录";
		}
		
		Zdoc zdoc = zdocService.get(docId);
		zdocService.updateDown(zdoc);
		
		ZuserDoc zuserDoc = new ZuserDoc();
		zuserDoc.setUserid(user.getId());
		zuserDoc.setDocid(docId);
		zuserDoc.setDelFlag("0");
		List<ZuserDoc> userdoclist = zuserDocService.findList(zuserDoc);
		if(userdoclist!=null && userdoclist.size()>0){
			
		}else{
			zuserDocService.save(zuserDoc);
		}
		
		String path = zdoc.getFiles();
    	try{
            if(!path.contains("http") && !path.contains("HTTP")) {
                path = request.getSession().getServletContext().getRealPath(path);
            }
            FileUtils.downFile(new File(path), request, response);            
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
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
	 * 系统退出
	 */
	@RequestMapping(value = "logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
		request.getSession().removeAttribute("sessionMyinfo");
		// 登录完成后，进入登录界面
		return "redirect:index";
	}
	
	/**
	 * 跳转用户注册
	 */
	@RequestMapping(value ="reg")
	public String reg(HttpServletRequest request, HttpServletResponse response, Model model){
		return "front/reg";
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
			request.getSession().setAttribute("sessionMyinfo", user);
		}else{
			if(userlist!=null && userlist.size()>1){
				model.addAttribute("msg", "输入异常，请重新输入");
				return "front/login";
			}else{
				model.addAttribute("msg", "账户密码错误，请重新输入");
				return "front/login";
			}			
		}		
		return "redirect:myinfo";
	}
	
	/**
	 * 个人中心
	 * @param zuser
	 * @return
	 */
	@RequestMapping(value ="myinfo")
	public String myinfo(Zuser zuser,HttpServletRequest request, HttpServletResponse response, Model model){
		
		Zuser user = (Zuser)request.getSession().getAttribute("sessionMyinfo");
		if(user==null || StringUtils.isBlank(user.getId())) {
			model.addAttribute("msg", "请登陆.");
			return "front/login";
		}
		
		return "front/myinfo";
	}
	
	
	/**
	 * 我的下载记录
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="myDoc")
	public String myDoc(HttpServletRequest request, HttpServletResponse response, Model model){
		Zuser user = (Zuser)request.getSession().getAttribute("sessionMyinfo");
		if(user==null || StringUtils.isBlank(user.getId())) {
			model.addAttribute("msg", "请登陆.");
			return "front/login";
		}
		
		ZuserDoc doc = new ZuserDoc();
		doc.setUserid(user.getId());
		doc.setDelFlag("0");
		
		Page<ZuserDoc> pages = new Page<ZuserDoc>();
		pages.setPageSize(10);
		
		Page<ZuserDoc> page = zuserDocService.findPage(pages, doc); 
		model.addAttribute("page", page);	
		
		return "front/myDoc";
	}
	
	
	
	
	/**
	 * 课程列表
	 * 
	 */
	@RequestMapping(value ="courseList")
	public String courseList(Zcourse zcourse, HttpServletRequest request, HttpServletResponse response, Model model){
		model.addAttribute("mendId", "2");	
		
		//选择的分类的列表
		List<String> sortlist = new ArrayList<String>();
		//前台选择的分类ID
		String sort1 = request.getParameter("classflyone");
		String sort2 = request.getParameter("classflytwo");
		String sort3 = request.getParameter("classflythr");
		
		ZcourseSort sort = new ZcourseSort();
		sort.setDelFlag("0");
		
		//一级分类信息列表
		sort.setParentId("0");
		List<ZcourseSort> sortlist1 = zcourseSortService.findList(sort);
		model.addAttribute("sortlist1", sortlist1);
		
		//将分类信息导入
		if(sort1!=null && !"".equals(sort1)){
			//二级分类信息列表
			sort.setParentId(sort1);
			sortlist1 = zcourseSortService.findList(sort);
			model.addAttribute("sortlist2", sortlist1);
			
			sortlist.add(sort1);
		}
		if(sort2!=null && !"".equals(sort2)){
			//三级分类信息列表
			sort.setParentId(sort2);
			sortlist1 = zcourseSortService.findList(sort);
			model.addAttribute("sortlist3", sortlist1);
			sortlist.add(sort2);	
		}
		
		//是否点击了第三级
		if(sort3!=null && !"".equals(sort3)){
			sortlist.add(sort3);
		}
		
		zcourse.setSortlist(sortlist);
		Page<Zcourse> page = zcourseService.findPage(new Page<Zcourse>(request, response), zcourse);
		model.addAttribute("page", page);
		model.addAttribute("zcourse", zcourse);
		
		model.addAttribute("classflyone", sort1);
		model.addAttribute("classflytwo", sort2);
		model.addAttribute("classflythr", sort3);
		
		return "front/courselist";
	}
	
	/**
	 * 课程详细信息
	 * @param zcourse
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping( value= "coursedetail")
	public String coursedetail(Zcourse zcourse, HttpServletRequest request, HttpServletResponse response, Model model){
		model.addAttribute("mendId", "2");	
		
		Zuser user = (Zuser)request.getSession().getAttribute("sessionMyinfo");
		if(user==null || StringUtils.isBlank(user.getId())) {
			model.addAttribute("msg", "请登陆.");
			return "front/login";
		}
		
		//当前课程是否购买
		ZcourseOrder zcourseOrder = new ZcourseOrder();
		zcourseOrder.setCourseid(zcourse.getId());
		zcourseOrder.setUserid(user.getId());
		List<ZcourseOrder> orderlist = zcourseOrderService.findList(zcourseOrder);
		if(orderlist!=null && orderlist.size()==1){
			zcourseOrder = orderlist.get(0);
			if("2".equals(zcourseOrder.getPaystatus())){
				model.addAttribute("ispay", "yespay");
			}else{
				model.addAttribute("ispay", "nopay");
			}
		}else{
			model.addAttribute("ispay", "nopay");
		}
		
		zcourse = zcourseService.get(zcourse.getId());
		model.addAttribute("zcourse", zcourse);
		
		return "front/coursedetail";
	}
	


}
