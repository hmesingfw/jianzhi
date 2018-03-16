package com.thinkgem.jeesite.front.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.hm.dao.test_question.ZtestQuestionDao;
import com.thinkgem.jeesite.modules.hm.entity.course.Zcourse;
import com.thinkgem.jeesite.modules.hm.entity.course_sort.ZcourseSort;
import com.thinkgem.jeesite.modules.hm.entity.course_user.ZcourseUser;
import com.thinkgem.jeesite.modules.hm.entity.doc.Zdoc;
import com.thinkgem.jeesite.modules.hm.entity.docsort.ZdocSort;
import com.thinkgem.jeesite.modules.hm.entity.news.Znew;
import com.thinkgem.jeesite.modules.hm.entity.order.ZcourseOrder;
import com.thinkgem.jeesite.modules.hm.entity.question.Zquestion;
import com.thinkgem.jeesite.modules.hm.entity.question_answer.ZquestionAnswer;
import com.thinkgem.jeesite.modules.hm.entity.test.Ztest;
import com.thinkgem.jeesite.modules.hm.entity.test_question.ZtestQuestion;
import com.thinkgem.jeesite.modules.hm.entity.user.Zuser;
import com.thinkgem.jeesite.modules.hm.entity.userdoc.ZuserDoc;
import com.thinkgem.jeesite.modules.hm.entity.usertest.ZuserTest;
import com.thinkgem.jeesite.modules.hm.service.aboutus.TaboutUsService;
import com.thinkgem.jeesite.modules.hm.service.banner.ZbannerService;
import com.thinkgem.jeesite.modules.hm.service.course.ZcourseService;
import com.thinkgem.jeesite.modules.hm.service.course_sort.ZcourseSortService;
import com.thinkgem.jeesite.modules.hm.service.course_user.ZcourseUserService;
import com.thinkgem.jeesite.modules.hm.service.doc.ZdocService;
import com.thinkgem.jeesite.modules.hm.service.docsort.ZdocSortService;
import com.thinkgem.jeesite.modules.hm.service.news.ZnewService;
import com.thinkgem.jeesite.modules.hm.service.order.ZcourseOrderService;
import com.thinkgem.jeesite.modules.hm.service.question.ZquestionService;
import com.thinkgem.jeesite.modules.hm.service.question_answer.ZquestionAnswerService;
import com.thinkgem.jeesite.modules.hm.service.test.ZtestService;
import com.thinkgem.jeesite.modules.hm.service.user.ZuserService;
import com.thinkgem.jeesite.modules.hm.service.userdoc.ZuserDocService;
import com.thinkgem.jeesite.modules.hm.service.usertest.ZuserTestService;

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
//	试题管理
	@Autowired
	private ZquestionService zquestionService;
//	試題答案管理
	@Autowired
	private ZquestionAnswerService zquestionAnswerService;
//	试题卷管理
	@Autowired
	private ZtestService ztestService;
//	用户测试记录
	@Autowired
	private ZuserTestService zuserTestService;
//	试卷试题管理
	@Autowired
	private ZtestQuestionDao ztestQuestionDao;
//	用户课程观看记录
	@Autowired
	private ZcourseUserService zcourseUserService;
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
	 * 注册下一步
	 */
	@RequestMapping(value ="reg1")
	public String reg1(Zuser zuser, HttpServletRequest request, HttpServletResponse response, Model model){
				
		Zuser queryuser = new Zuser();
		queryuser.setIdcode(zuser.getIdcode());		
		queryuser.setDelFlag("0");
		List<Zuser> userlist = zuserService.findList(zuser);
		if(userlist!=null && userlist.size()>0){
			model.addAttribute("msg", "当前身份证已使用");
			return "front/reg";
		}
		model.addAttribute("zuser", zuser);
		return "front/reg1";
	}
	
	/**
	 * 注册信息填写录入
	 * @param zuser
	 * @return
	 */
	@RequestMapping(value ="register")
	public String register(Zuser zuser,@RequestParam(required=false ,value="file")MultipartFile file, HttpServletRequest request, HttpServletResponse response, Model model){
		
		try {
			// 判断用户是否上传头像，如果上传头像，则保存头像信息
			long uuid = new Date().getTime();
			String fileName=file.getOriginalFilename();
			if(file!=null && !StringUtils.isBlank(fileName)) { 
				System.out.println(fileName);
				int index = fileName.lastIndexOf(".");
				fileName = fileName.substring(0,index)+"_"+uuid+fileName.substring(index);
				String filePath = request.getSession().getServletContext().getRealPath("/static/userfile");
				File savefile = new File(filePath, fileName);
				file.transferTo(savefile);
				zuser.setImg("/static/userfile/"+fileName);
			}
			zuserService.save(zuser);
			model.addAttribute("msg", "注册成功，请等待管理人员审核");
			model.addAttribute("sessionMyinfo", zuser);
			return "redirect:myinfo";
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "修改失败.");
			return "front/reg1";
		}						
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
		
		List<Zuser> userlist = zuserService.findidcode(zuser);
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
			model.addAttribute("msg", "账户密码错误，请重新输入");
			return "front/login";						
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
	 * 我的课程订单
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value ="myCourseOrder")
	public String myCourseOrder(HttpServletRequest request, HttpServletResponse response, Model model){
		Zuser user = (Zuser)request.getSession().getAttribute("sessionMyinfo");
		if(user==null || StringUtils.isBlank(user.getId())) {
			model.addAttribute("msg", "请登陆.");
			return "front/login";
		}
		ZcourseOrder order = new ZcourseOrder();
		order.setUserid(user.getId());
		order.setDelFlag("0");
		
		Page<ZcourseOrder> pages = new Page<ZcourseOrder>();
		pages.setPageSize(10);
		
		
		Page<ZcourseOrder> page = zcourseOrderService.findPage(pages, order);
		model.addAttribute("page", page);
		
		return "front/myCourseOrder";
	}
	
	/**
	 * 我的学习记录
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value ="myCourseUser")
	public String myCourseUser(HttpServletRequest request, HttpServletResponse response, Model model){
		Zuser user = (Zuser)request.getSession().getAttribute("sessionMyinfo");
		if(user==null || StringUtils.isBlank(user.getId())) {
			model.addAttribute("msg", "请登陆.");
			return "front/login";
		}
		ZcourseUser courseuser = new ZcourseUser();
		courseuser.setUserid(user.getId());
		courseuser.setDelFlag("0");
		
		Page<ZcourseUser> pages = new Page<ZcourseUser>();
		pages.setPageSize(10);
		
		
		Page<ZcourseUser> page = zcourseUserService.findPage(pages, courseuser);
		model.addAttribute("page", page);
		
		return "front/myCourseUser";
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
		zcourse = zcourseService.get(zcourse.getId());		
		//当前课程是否购买
		ZcourseOrder zcourseOrder = new ZcourseOrder();
		zcourseOrder.setCourseid(zcourse.getId());
		zcourseOrder.setUserid(user.getId());
		zcourseOrder.setDelFlag("0");
		List<ZcourseOrder> orderlist = zcourseOrderService.findMyorderByid(zcourseOrder);
		
		if(orderlist!=null && orderlist.size()==1){
			zcourseOrder = orderlist.get(0);
			if("2".equals(zcourseOrder.getPaystatus())){
				System.out.println("1");
				model.addAttribute("ispay", "yespay");
			}else{
				System.out.println("2");
				model.addAttribute("ispay", "nopay");
			}
		}else{
			if("1".equals(zcourse.getType())){
				System.out.println("3");
				//免费课程
				model.addAttribute("ispay", "yespay");
			}else{				
				System.out.println("4");
				model.addAttribute("ispay", "nopay");
			}
		}		
		model.addAttribute("zcourse", zcourse);		
		return "front/coursedetail";
	}
	
	
	/**
	 * 课程观看
	 * @param zcourse
	 * @param request
	 * @return
	 */
	@RequestMapping( value= "gotolookcourse")
	public String gotolookcourse(Zcourse zcourse, HttpServletRequest request, HttpServletResponse response, Model model){		
		Zuser user = (Zuser)request.getSession().getAttribute("sessionMyinfo");
		if(user==null || StringUtils.isBlank(user.getId())) {
			model.addAttribute("msg", "请登陆.");
			return "front/login";
		}
		
		ZcourseOrder zcourseOrder = new ZcourseOrder();
		zcourseOrder.setCourseid(zcourse.getId());
		zcourseOrder.setUserid(user.getId());
		zcourseOrder.setDelFlag("0");
		List<ZcourseOrder> orderlist = zcourseOrderService.findMyorderByid(zcourseOrder);
		if(orderlist!=null && orderlist.size()>0){
			
		}else{			
			zcourseOrder.setPaystatus("2");
			zcourseOrder.setPayprice("0");
			zcourseOrder.setPaytime(new Date());
			zcourseOrder.setCourseprice("0");
			zcourseOrder.setPaytype("3");
			zcourseOrderService.save(zcourseOrder);		
		}
		
		//课程观看时间记录
		ZcourseUser zcourseUser = new ZcourseUser();
		zcourseUser.setUserid(user.getId());
		zcourseUser.setCourseid(zcourse.getId());
		zcourseUser.setDelFlag("0");
		List<ZcourseUser> culist = zcourseUserService.findList(zcourseUser);
		if(culist!=null && culist.size()>0){				
		}else{
			zcourseUserService.save(zcourseUser);
		}
		
		zcourse = zcourseService.get(zcourse.getId());	
		model.addAttribute("zcourse", zcourse);	
		return "front/lookvideo";
	}
	
	
	/**
     * 课程观看记录
     * @param path
     */
    @ResponseBody
    @RequestMapping(value = "courseLookRecord")
    public String courseLookRecord(@RequestParam(required=false, value="courseid")String courseid,HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("looking........."+courseid);
    	Zuser user = (Zuser)request.getSession().getAttribute("sessionMyinfo");
		if(user!=null && StringUtils.isBlank(user.getId())) {
			
			 ZcourseUser zcourseUser = new ZcourseUser();
			 zcourseUser.setUserid(user.getId());
			 zcourseUser.setCourseid(courseid);
			 zcourseUser.setDelFlag("0");
			 List<ZcourseUser> culist = zcourseUserService.findList(zcourseUser);
			 if(culist!=null && culist.size()>0){
				 zcourseUser = new ZcourseUser();
				 zcourseUser.setId(culist.get(0).getId());
				 zcourseUserService.updateUsertime(zcourseUser);		
			 }
		}
		
		
        return null;
    }
	
	
	
	
	/**
	 * 测试题列表信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping( value= "questionlist")
	public String questionlist(Ztest test, HttpServletRequest request, HttpServletResponse response, Model model){
		model.addAttribute("mendId", "3");	
		
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
		
		test.setSortlist(sortlist);
		Page<Ztest> page = ztestService.findPage(new Page<Ztest>(request, response), test);
		model.addAttribute("page", page);
		model.addAttribute("test", test);
		
		model.addAttribute("classflyone", sort1);
		model.addAttribute("classflytwo", sort2);
		model.addAttribute("classflythr", sort3);
		return "front/questionlist";
	}

	/**
	 * 进入考试
	 * @param test
	 * @return
	 */
	@RequestMapping( value= "gotoquestion")
	public String gotoquestion(Ztest test, HttpServletRequest request, HttpServletResponse response, Model model){
		model.addAttribute("mendId", "3");	
		
		Zuser user = (Zuser)request.getSession().getAttribute("sessionMyinfo");
		if(user==null || StringUtils.isBlank(user.getId())) {
			model.addAttribute("msg", "请登陆.");
			return "front/login";
		}
		
		test = ztestService.get(test.getId());
		model.addAttribute("testid", test.getId());
		model.addAttribute("test", test);
		ZuserTest usertest = new ZuserTest();
		usertest.setUserid(user.getId());
		usertest.setTestid(test.getId());		
		usertest.setDelFlag("0");	
		
		zuserTestService.deleteUesrtest(usertest); 		//删除用户考试记录
		
		//查找出当前试卷的所有题目
		ZtestQuestion ztestQuestion = new ZtestQuestion();
		ztestQuestion.setTestid(test.getId());
		ztestQuestion.setDelFlag("0");
		List<ZtestQuestion> testquestionlist = ztestQuestionDao.findList(ztestQuestion);
		
		//添加用户需要考试题的记录
		int sort = 1;
		for (ZtestQuestion ztestQuestion2 : testquestionlist) {
			ZuserTest insertTest = new ZuserTest();
			insertTest.setUserid(user.getId());
			insertTest.setTestid(test.getId());
			insertTest.setQuestionid(ztestQuestion2.getQuestion());
			insertTest.setSort(sort);
			sort++;
			zuserTestService.save(insertTest);
		}
		
		myTestlist(test.getId(), user.getId(), model, "yes");		
		
		String time = test.getTesttime();
		if(StringUtils.isNumeric(time)){
			model.addAttribute("time", Integer.parseInt(time)*60);
		}
		
		return "front/test";
	}
	
	/**
	 * 用户需要考试的题目
	 * @param testid	考试卷编号
	 * @param userid	用户编号
	 * @param isOne		是否为第一题			yes or no
	 * @param model
	 */
	public void myTestlist(String testid, String userid, Model model, String isOne){
		ZuserTest usertest = new ZuserTest();
		usertest.setTestid(testid);		
		usertest.setUserid(userid);
		usertest.setDelFlag("0");	
		
		List<ZuserTest> list = zuserTestService.findList(usertest);
		model.addAttribute("mytestlist", list);
		
		if("yes".equals(isOne)){
			if(list!=null && list.size()>0){
				currentTest(list.get(0).getId(), model);				
			}
		}
	}
	
	/**
	 * 当前测试题
	 * @param usertestid
	 * @param model
	 */
	public void currentTest(String usertestid, Model model){	
		//当前用户测试记录的编号
		ZuserTest usertest = zuserTestService.get(usertestid);
		model.addAttribute("myusertestid", usertestid);
		
		
		Zquestion zquestion = zquestionService.get(usertest.getQuestionid());
		//下一题的编号
		List<ZuserTest> nexttest = zuserTestService.findNextTest(usertest);		
		if(nexttest!=null && nexttest.size()>0){
			model.addAttribute("nextusertestid", nexttest.get(0).getId());
		}
		
		
		
		ZquestionAnswer zquestionAnswer = new ZquestionAnswer();
		zquestionAnswer.setQuesId(zquestion.getId());
		zquestionAnswer.setDelFlag("0");
		
		List<ZquestionAnswer> answerList = zquestionAnswerService.findList(zquestionAnswer);
		
		model.addAttribute("zquestion", zquestion);
		model.addAttribute("answerList", answerList);
		
	}
	
	
	/**
	 *  跳转到某题
	 */
	@RequestMapping( value= "gotoQuestion")
	public String bleow(HttpServletRequest request, HttpServletResponse response, Model model){
		model.addAttribute("mendId", "3");	
		
		Zuser user = (Zuser)request.getSession().getAttribute("sessionMyinfo");
		if(user==null || StringUtils.isBlank(user.getId())) {
			model.addAttribute("msg", "请登陆.");
			return "front/login";
		}
		
		String mytestid = request.getParameter("mytestid");		//跳转到当前题
		String testid   = request.getParameter("testid");
		
		model.addAttribute("testid", testid);		
		model.addAttribute("test", ztestService.get(testid));
		model.addAttribute("time", request.getParameter("time"));
		
		myTestlist(testid, user.getId(), model, "");	
		currentTest(mytestid, model);
		
		return "front/test";
	}
	
	
	/**
	 * 答题处理
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping( value= "answer")
	public String answer(HttpServletRequest request, HttpServletResponse response, Model model){
		model.addAttribute("mendId", "3");	
		
		Zuser user = (Zuser)request.getSession().getAttribute("sessionMyinfo");
		if(user==null || StringUtils.isBlank(user.getId())) {
			model.addAttribute("msg", "请登陆.");
			return "front/login";
		}
		String testid = request.getParameter("testid"); 				//当前试卷
		model.addAttribute("testid", testid);		
		model.addAttribute("test", ztestService.get(testid));
		
		String currentanswer = request.getParameter("currentanswer");	//当前答案		
		String myusertestid = request.getParameter("myusertestid");		//当前答题记录的编号
		String isCorrect = request.getParameter("isCorrect");
		
		String nextusertestid = request.getParameter("nextusertestid");	//下一题题目的编号
		ZuserTest usertest = zuserTestService.get(myusertestid);		
		usertest.setAnswerid(currentanswer);
		usertest.setIsselected("1");
		usertest.setIstrue(isCorrect);
		zuserTestService.save(usertest);
		
		String time = request.getParameter("time");
		model.addAttribute("time", time);
		//是否完成答卷
		if(nextusertestid!=null && !"".equals(nextusertestid)){
			myTestlist(testid, user.getId(), model, "");	
			currentTest(nextusertestid, model);
			return "front/test";
		}else{
			System.out.println(testid);
			ZuserTest myusertest = new ZuserTest();
			usertest.setTestid(testid);		
			usertest.setUserid(user.getId());
			usertest.setDelFlag("0");	
			List<ZuserTest> list = zuserTestService.findList(myusertest);		
			
			int iscorrect = 0;			//答对题数
			int fraction = 0;			//答对分数	
			for (ZuserTest zuserTest : list) {
				if("1".equals(zuserTest.getIstrue())){
					iscorrect++;
					Zquestion question = zquestionService.get(zuserTest.getQuestionid());
					if(question!=null && StringUtils.isNumeric(question.getDefaultFraction())){
						fraction += Integer.parseInt(question.getDefaultFraction());
					}
				}				
			}	
			model.addAttribute("iscorrect", iscorrect);
			model.addAttribute("fraction", fraction);
				
			model.addAttribute("mytestlist", list);
			Ztest currenttest = ztestService.get(testid);
			model.addAttribute("test", currenttest);
			return "front/testcurrent";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
