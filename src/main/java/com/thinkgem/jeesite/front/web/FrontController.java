package com.thinkgem.jeesite.front.web;

import java.io.File;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.pay.alipay.config.AlipayConfig;
import com.thinkgem.jeesite.common.utils.sms.tool.SmsAliTool;
import com.thinkgem.jeesite.front.utils.AliyunRecords;
import com.thinkgem.jeesite.modules.hm.dao.test_question.ZtestQuestionDao;
import com.thinkgem.jeesite.modules.hm.entity.course.Zcourse;
import com.thinkgem.jeesite.modules.hm.entity.course_sort.ZcourseSort;
import com.thinkgem.jeesite.modules.hm.entity.course_user.ZcourseUser;
import com.thinkgem.jeesite.modules.hm.entity.coursehour.ZcourseHour;
import com.thinkgem.jeesite.modules.hm.entity.doc.Zdoc;
import com.thinkgem.jeesite.modules.hm.entity.docsort.ZdocSort;
import com.thinkgem.jeesite.modules.hm.entity.news.Znew;
import com.thinkgem.jeesite.modules.hm.entity.order.ZcourseOrder;
import com.thinkgem.jeesite.modules.hm.entity.question.Zquestion;
import com.thinkgem.jeesite.modules.hm.entity.question_answer.ZquestionAnswer;
import com.thinkgem.jeesite.modules.hm.entity.test.Ztest;
import com.thinkgem.jeesite.modules.hm.entity.test_question.ZtestQuestion;
import com.thinkgem.jeesite.modules.hm.entity.testrandom.ZtestRandom;
import com.thinkgem.jeesite.modules.hm.entity.user.Zuser;
import com.thinkgem.jeesite.modules.hm.entity.userdoc.ZuserDoc;
import com.thinkgem.jeesite.modules.hm.entity.usertest.ZuserTest;
import com.thinkgem.jeesite.modules.hm.service.aboutus.TaboutUsService;
import com.thinkgem.jeesite.modules.hm.service.banner.ZbannerService;
import com.thinkgem.jeesite.modules.hm.service.course.ZcourseService;
import com.thinkgem.jeesite.modules.hm.service.course_sort.ZcourseSortService;
import com.thinkgem.jeesite.modules.hm.service.course_user.ZcourseUserService;
import com.thinkgem.jeesite.modules.hm.service.coursehour.ZcourseHourService;
import com.thinkgem.jeesite.modules.hm.service.doc.ZdocService;
import com.thinkgem.jeesite.modules.hm.service.docsort.ZdocSortService;
import com.thinkgem.jeesite.modules.hm.service.news.ZnewService;
import com.thinkgem.jeesite.modules.hm.service.order.ZcourseOrderService;
import com.thinkgem.jeesite.modules.hm.service.question.ZquestionService;
import com.thinkgem.jeesite.modules.hm.service.question_answer.ZquestionAnswerService;
import com.thinkgem.jeesite.modules.hm.service.test.ZtestService;
import com.thinkgem.jeesite.modules.hm.service.testrandom.ZtestRandomService;
import com.thinkgem.jeesite.modules.hm.service.user.ZuserService;
import com.thinkgem.jeesite.modules.hm.service.userdoc.ZuserDocService;
import com.thinkgem.jeesite.modules.hm.service.usertest.ZuserTestService;
import com.thinkgem.jeesite.modules.hm.utils.ZcourseSortUtils;

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
	// 文档内容管理
	@Autowired
	private ZdocService zdocService;
	// 文档分类管理
	@Autowired
	private ZdocSortService zdocSortService;
	// 用户信息管理
	@Autowired
	private ZuserService zuserService;
	// 用户文档下载记录管理
	@Autowired
	private ZuserDocService zuserDocService;
	// 课程分类管理
	@Autowired
	private ZcourseSortService zcourseSortService;
	// 课程信息管理
	@Autowired
	private ZcourseService zcourseService;
	// 课程订单信息
	@Autowired
	private ZcourseOrderService zcourseOrderService;
	// 试题管理
	@Autowired
	private ZquestionService zquestionService;
	// 試題答案管理
	@Autowired
	private ZquestionAnswerService zquestionAnswerService;
	// 试题卷管理
	@Autowired
	private ZtestService ztestService;
	// 用户测试记录
	@Autowired
	private ZuserTestService zuserTestService;
	// 试卷试题管理
	@Autowired
	private ZtestQuestionDao ztestQuestionDao;
	// 用户课程观看记录
	@Autowired
	private ZcourseUserService zcourseUserService;
	// 测试随机题配置
	@Autowired
	private ZtestRandomService ztestRandomService;

	// 课时管理
	@Autowired
	private ZcourseHourService zcourseHourService;

	/**
	 * 首页管理
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "index", "" })
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
		// 课程推荐
		Zcourse zcourse = new Zcourse();
		zcourse.setDelFlag("1");
		zcourse.setIscommend("1");
		Page<Zcourse> coursepage = new Page<Zcourse>();
		coursepage.setPageSize(8);
		Page<Zcourse> zcoursepage = zcourseService.findPage(coursepage, zcourse);
		model.addAttribute("zcoursepage", zcoursepage);
		// 文档推荐
		List<Zdoc> zoctoplist = zdocService.findDownTop();
		model.addAttribute("zoctoplist", zoctoplist);
		return "front/index";
	}

	@RequestMapping(value = { "reginfo" })
	public String reginfo(HttpServletRequest request, HttpServletResponse response, Model model) {

		return "front/reginfo";
	}

	/**
	 * 新闻列表
	 * 
	 * @param znew
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "news")
	public String news(Znew znew, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("mendId", "5");
		// 推荐新闻
		Znew news = new Znew();
		news.setDelFlag("0");
		news.setIscommend("1");
		List<Znew> list = znewService.findList(news);
		model.addAttribute("comlist", list);

		// 列表
		Page<Znew> page = znewService.findPage(new Page<Znew>(request, response), znew);
		model.addAttribute("page", page);

		model.addAttribute("znew", znew);
		return "front/news";
	}

	/**
	 * 新闻详情
	 * 
	 * @param znew
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "newdetail")
	public String newdetail(Znew znew, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("mendId", "5");
		// 推荐新闻
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
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "sortlist")
	public String sortlist(Zdoc zdoc, HttpServletRequest request, HttpServletResponse response, Model model) {
		Zuser user = (Zuser) request.getSession().getAttribute("sessionMyinfo");

		model.addAttribute("mendId", "4");
		// 选择的分类的列表
		List<String> sortlist = new ArrayList<String>();

		// 前台选择的分类ID
		String sort1 = request.getParameter("classflyone");
		String sort2 = request.getParameter("classflytwo");
		String sort3 = request.getParameter("classflythr");

		/** 分类信息列表 */
		// 一级分类
		ZdocSort zdocSort = new ZdocSort();
		zdocSort.setParent("0");
		List<ZdocSort> docsort = zdocSortService.findList(zdocSort);
		model.addAttribute("docsort", docsort);

		// 将分类信息导入
		if (sort1 != null && !"".equals(sort1)) {
			// 二级分类
			zdocSort.setParent(sort1);
			docsort = zdocSortService.findList(zdocSort);
			model.addAttribute("docsort2", docsort);
			if (StringUtils.isBlank(sort2)) {
				for (ZdocSort zdocSort2 : docsort) {
					sortlist.add(zdocSort2.getId());

					// 第三级
					zdocSort.setParent(zdocSort2.getId());
					List<ZdocSort> sortlist12 = zdocSortService.findList(zdocSort);
					if (StringUtils.isBlank(sort3)) {
						for (ZdocSort zcourseSort2 : sortlist12) {
							sortlist.add(zcourseSort2.getId());
						}
					}
				}
			}
			sortlist.add(sort1);
		}
		if (sort2 != null && !"".equals(sort2)) {
			// 三级分类
			zdocSort.setParent(sort2);
			docsort = zdocSortService.findList(zdocSort);
			model.addAttribute("docsort3", docsort);
			for (ZdocSort zdocSort2 : docsort) {
				sortlist.add(zdocSort2.getId());
			}
			sortlist.add(sort2);
		}
		if (sort3 != null && !"".equals(sort3)) {
			sortlist.add(sort3);
		}

		zdoc.setSortlist(sortlist);
		if (user == null || StringUtils.isBlank(user.getId())) {
			zdoc.setUsertype("2");
		} else {
			// 当前用户是不是注册用户
			if ("2".equals(user.getType())) {
				zdoc.setUsertype("2");
			}
		}
		// 主体内容
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
	public String doclook(Zdoc zdoc, HttpServletRequest request, HttpServletResponse response, Model model) {
		zdoc = zdocService.get(zdoc);
		zdocService.updateLook(zdoc);
		model.addAttribute("zdoc", zdoc);
		return "front/gotolookdoc";
	}

	/**
	 * 下载文件-本地
	 * 
	 * @param path
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "docDown")
	public String docDown(@RequestParam(required = true, value = "docId") String docId, HttpServletRequest request,
			HttpServletResponse response) {
		Zuser user = (Zuser) request.getSession().getAttribute("sessionMyinfo");
		if (user == null || StringUtils.isBlank(user.getId())) {
			return "请登录";
		}

		Zdoc zdoc = zdocService.get(docId);
		zdocService.updateDown(zdoc);

		ZuserDoc zuserDoc = new ZuserDoc();
		zuserDoc.setUserid(user.getId());
		zuserDoc.setDocid(docId);
		zuserDoc.setDelFlag("0");
		List<ZuserDoc> userdoclist = zuserDocService.findList(zuserDoc);
		if (userdoclist != null && userdoclist.size() > 0) {

		} else {
			zuserDocService.save(zuserDoc);
		}

		String path = zdoc.getFiles();
		try {
			if (!path.contains("http") && !path.contains("HTTP")) {
				path = request.getSession().getServletContext().getRealPath(path);
			}
			FileUtils.downFile(new File(path), request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "docDownUsermodel")
	public String docDownUsermodel(HttpServletRequest request, HttpServletResponse response) {

		String path = "/static/userModel.xlsx";
		try {
			if (!path.contains("http") && !path.contains("HTTP")) {
				path = request.getSession().getServletContext().getRealPath(path);
			}
			FileUtils.downFile(new File(path), request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "docDownTestmodel")
	public String docDownTestmodel(HttpServletRequest request, HttpServletResponse response) {

		String path = "/static/testModel.xlsx";
		try {
			if (!path.contains("http") && !path.contains("HTTP")) {
				path = request.getSession().getServletContext().getRealPath(path);
			}
			FileUtils.downFile(new File(path), request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 跳转登陆页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "gotologin")
	public String gotologin(HttpServletRequest request, HttpServletResponse response, Model model) {
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
	@RequestMapping(value = "reg")
	public String reg(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "front/reg";
	}

	/**
	 * 注册下一步
	 */
	@RequestMapping(value = "reg1")
	public String reg1(Zuser zuser, HttpServletRequest request, HttpServletResponse response, Model model) {

		Zuser queryuser = new Zuser();
		queryuser.setIdcode(zuser.getIdcode());
		queryuser.setDelFlag("0");
		List<Zuser> userlist = zuserService.findList(zuser);
		if (userlist != null && userlist.size() > 0) {
			model.addAttribute("msg", "当前身份证已使用");
			return "front/reg";
		}
		model.addAttribute("zuser", zuser);
		return "front/reg1";
	}

	/**
	 * 注册信息填写录入
	 * 
	 * @param zuser
	 * @return
	 */
	@RequestMapping(value = "register")
	public String register(Zuser zuser, String infotype,
			@RequestParam(required = false, value = "file") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		try {
			// 判断用户是否上传头像，如果上传头像，则保存头像信息
			long uuid = new Date().getTime();
			String fileName = file.getOriginalFilename();
			if (file != null && !StringUtils.isBlank(fileName)) {
				System.out.println(fileName);
				int index = fileName.lastIndexOf(".");
				fileName = fileName.substring(0, index) + "_" + uuid + fileName.substring(index);
				String filePath = request.getSession().getServletContext().getRealPath("/static/userfile");
				File savefile = new File(filePath, fileName);
				file.transferTo(savefile);
				zuser.setImg("/static/userfile/" + fileName);
			}
			// 判断当前用户是注册还是修改个人信息
			if ("2".equals(infotype)) {

				Zuser updateuser = zuserService.get(zuser.getId());
				updateuser.setPassword(zuser.getPassword());
				updateuser.setImg(zuser.getImg());
				updateuser.setXmajor(zuser.getXmajor());
				updateuser.setTruename(zuser.getTruename());
				updateuser.setEducation(zuser.getEducation());
				updateuser.setEmployer(zuser.getEmployer());
				updateuser.setWorklength(zuser.getWorklength());

				zuserService.save(updateuser);
				request.getSession().setAttribute("sessionMyinfo", updateuser);
				return "redirect:myinfo?isreg=2";

			} else {
				zuserService.save(zuser);
				request.getSession().setAttribute("sessionMyinfo", zuser);
				// model.addAttribute("msg", "注册成功");
				// model.addAttribute("sessionMyinfo", zuser);
				// return "redirect:myinfo";

				// 判断是否列表页过来还是详情页 2为列表页面
				model.addAttribute("id", zuser.getMajor());
				return "front/pay";
			}

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "修改失败.");
			return "front/reg1";
		}
	}

	/**
	 * 登陆
	 * 
	 * @param zuser
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "login")
	public String login(Zuser zuser, HttpServletRequest request, HttpServletResponse response, Model model) {

		List<Zuser> userlist = zuserService.findidcode(zuser);
		if (userlist != null && userlist.size() == 1) {

			Zuser user = userlist.get(0);
			if ("1".equals(user.getDelFlag())) {
				model.addAttribute("msg", "账户不存在");
				return "front/login";
			}

			if ("2".equals(user.getStatus())) {
				model.addAttribute("msg", "账号停用中");
				return "front/login";
			}

			if ("3".equals(user.getStatus())) {
				model.addAttribute("msg", "账号审核中");
				return "front/login";
			}
			request.getSession().setAttribute("sessionMyinfo", user);
		} else {
			model.addAttribute("msg", "账户密码错误，请重新输入");
			return "front/login";
		}
		return "redirect:myCourseUser";
	}

	/**
	 * 个人中心
	 * 
	 * @param zuser
	 * @return
	 */
	@RequestMapping(value = "myinfo")
	public String myinfo(Zuser zuser, String isreg, HttpServletRequest request, HttpServletResponse response,
			Model model) {

		Zuser user = (Zuser) request.getSession().getAttribute("sessionMyinfo");
		if (user == null || StringUtils.isBlank(user.getId())) {
			model.addAttribute("msg", "请登陆.");
			return "front/login";
		}

		if ("2".equals(isreg)) {
			model.addAttribute("msg", "修改人个信息成功");
		}

		return "front/myinfo";
	}

	/**
	 * 我的下载记录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "myDoc")
	public String myDoc(HttpServletRequest request, HttpServletResponse response, Model model) {
		Zuser user = (Zuser) request.getSession().getAttribute("sessionMyinfo");
		if (user == null || StringUtils.isBlank(user.getId())) {
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
	 * 我的专业试题
	 */
	@RequestMapping(value = "myTest")
	public String myTest(HttpServletRequest request, HttpServletResponse response, Model model) {
		Zuser user = (Zuser) request.getSession().getAttribute("sessionMyinfo");
		if (user == null || StringUtils.isBlank(user.getId())) {
			model.addAttribute("msg", "请登陆.");
			return "front/login";
		}

		Ztest test = new Ztest();
		test.setParentid(user.getMajor());
		test.setDelFlag("0");

		Page<Ztest> page = ztestService.findPage(new Page<Ztest>(request, response), test);
		model.addAttribute("page", page);

		return "front/myTest";
	}

	/**
	 * 我的课程订单
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "myCourseOrder")
	public String myCourseOrder(HttpServletRequest request, HttpServletResponse response, Model model) {
		Zuser user = (Zuser) request.getSession().getAttribute("sessionMyinfo");
		if (user == null || StringUtils.isBlank(user.getId())) {
			model.addAttribute("msg", "请登陆.");
			return "front/login";
		}
		// 1为内部用户 为内部用户添加课程
		if ("1".equals(user.getType())) {
			ZcourseSort sort = zcourseSortService.get(user.getMajor());

			ZcourseOrder order = new ZcourseOrder();
			order.setUserid(user.getId());
			order.setPaystatus("4");
			order.setCourseid(user.getMajor());
			order.setPaytime(new Date());
			order.setExptime(sort.getValidity());

			List<ZcourseOrder> orderlist = zcourseOrderService.findList(order);
			if (orderlist != null && orderlist.size() > 0) {
			} else {
				zcourseOrderService.save(order);
			}
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
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "myCourseUser")
	public String myCourseUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		Zuser user = (Zuser) request.getSession().getAttribute("sessionMyinfo");
		if (user == null || StringUtils.isBlank(user.getId())) {
			model.addAttribute("msg", "请登陆.");
			return "front/login";
		}
		// ZcourseOrder order = new ZcourseOrder();
		// order.setUserid(user.getId());
		// order.setDelFlag("0");
		//// order.setPaystatus("2");
		// List<ZcourseOrder> status4list = zcourseOrderService.findList(order);
		// //添加课程学习记录
		// for (ZcourseOrder zcourseOrder : status4list) {
		// if("2".equals(zcourseOrder.getPaystatus()) ||
		// "4".equals(zcourseOrder.getPaystatus())){
		//
		// Zcourse zcourse = new Zcourse();
		// zcourse.setParentid(zcourseOrder.getCourseid());
		// zcourse.setDelFlag("0");
		// List<Zcourse> courselist = zcourseService.findList(zcourse);
		// //当前专业的课程
		//
		//// for (Zcourse zcourse2 : courselist) {
		//// // 课程观看时间记录
		//// ZcourseUser zcourseUser = new ZcourseUser();
		//// zcourseUser.setUserid(user.getId());
		//// zcourseUser.setCourseid(zcourse2.getId());
		//// zcourseUser.setDelFlag("0");
		//// zcourseUser.setUsertime("0");
		//// List<ZcourseUser> culist =
		// zcourseUserService.findList(zcourseUser);
		//// if (culist != null && culist.size() > 0) {
		//// } else {
		//// zcourseUserService.save(zcourseUser);
		//// }
		//// }
		// }
		// }

		ZcourseOrder zcourseOrder = new ZcourseOrder();
		zcourseOrder.setUserid(user.getId());
		Page<ZcourseOrder> pages = new Page<ZcourseOrder>();
		pages.setPageSize(10);

		Page<ZcourseOrder> page = zcourseOrderService.findPage(pages, zcourseOrder);
		for (ZcourseOrder order : page.getList()) {
			int sche = courseSchedule(order.getCourseid(), user.getId());
			order.setWidth(sche + "");
		}
		model.addAttribute("page", page);

		return "front/myCourseUser";
	}

	/**
	 * 课程学习进度
	 * 
	 * @param courseid
	 *            课程编号
	 * @param userid
	 *            用户编号
	 * @return 返回进度
	 */
	public int courseSchedule(String courseid, String userid) {
		double ctime = 0; // 课程学习总时长
		double utime = 0; // 课程学习时长

		ZcourseHour hour = new ZcourseHour();
		hour.setCourseid(courseid);
		hour.setDelFlag("0");
		List<ZcourseHour> hourlist = zcourseHourService.findListByCourseid(hour); // 当前课程的课时

		for (ZcourseHour zcourseHour : hourlist) {
			ZcourseUser cu = new ZcourseUser();
			cu.setCourseid(zcourseHour.getId());
			cu.setUserid(userid);
			cu.setDelFlag("0");
			List<ZcourseUser> userlist = zcourseUserService.findListByCourseUser(cu); // 当前课时的学习进度

			for (ZcourseUser zcourseUser : userlist) {
				double cctime = StringUtils.isNumeric(zcourseUser.getCoursetime())
						? Double.parseDouble(zcourseUser.getCoursetime()) : 0; // 当前课时总长度
				double uutime = StringUtils.isNumeric(zcourseUser.getUsertime())
						? Double.parseDouble(zcourseUser.getUsertime()) : 0; // 当前课时学习长度

				utime += uutime;
				ctime += cctime;
			}
		}
		double sche = utime / ctime * 100;
		sche = Math.floor(sche);
		System.out.println(sche + "__________________________________________");
		if (sche > 100) {
			return 100;
		} else {
			return (int) sche;
		}
	}

	/**
	 * 课程列表
	 * 
	 */
	@RequestMapping(value = "courseList")
	public String courseList(Zcourse zcourse, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("mendId", "2");

		Zuser user = (Zuser) request.getSession().getAttribute("sessionMyinfo");
		if (user == null || StringUtils.isBlank(user.getId())) {
			model.addAttribute("msg", "请登陆.");
			return "front/login";
		}
		// 选择的分类的列表
		List<String> sortlist = new ArrayList<String>();
		// 前台选择的分类ID
		String sort1 = request.getParameter("classflyone");
		String sort2 = request.getParameter("classflytwo");
		String sort3 = request.getParameter("classflythr");

		// 当前专业是否购买
		String currentclick = request.getParameter("currentclick"); // 当前点击的专业
		if (StringUtils.isNotBlank(currentclick)) {
			ZcourseOrder zcourseOrder = new ZcourseOrder();
			zcourseOrder.setCourseid(currentclick);
			zcourseOrder.setUserid(user.getId());
			zcourseOrder.setDelFlag("0");
			List<ZcourseOrder> orderlist = zcourseOrderService.findMyorderByid(zcourseOrder);
			if (orderlist != null && orderlist.size() == 1) {
				zcourseOrder = orderlist.get(0);
				int daynum = StringUtils.isNumeric(zcourseOrder.getExptime())
						? Integer.parseInt(zcourseOrder.getExptime()) : 0;
				// 当前专业是否到期
				if (ZcourseSortUtils.isExp(zcourseOrder.getPaytime(), daynum)) {
					if ("4".equals(zcourseOrder.getPaystatus())) { // 是否是内部用户的专业
						model.addAttribute("ispay", "yespay");
						System.out.println("_____________________________________1");
						// 是否支付成功
					} else if ("2".equals(zcourseOrder.getPaystatus())) {
						model.addAttribute("ispay", "yespay");
						System.out.println("_____________________________________2");
					} else {
						model.addAttribute("ispay", "nopay");
						System.out.println("_____________________________________3");
					}
				} else {
					model.addAttribute("ispay", "nopay");
					System.out.println("_____________________________________4");
				}
			} else {
				model.addAttribute("ispay", "nopay");
				System.out.println("_____________________________________5");
			}
		}
		model.addAttribute("currentclick", currentclick);
		// end

		ZcourseSort sort = new ZcourseSort();
		sort.setDelFlag("0");

		// 一级分类信息列表
		sort.setParentId("0");
		List<ZcourseSort> sortlist1 = zcourseSortService.findList(sort);
		model.addAttribute("sortlist1", sortlist1);

		// 将分类信息导入
		if (sort1 != null && !"".equals(sort1)) {
			// 二级分类信息列表
			sort.setParentId(sort1);
			sortlist1 = zcourseSortService.findList(sort);
			model.addAttribute("sortlist2", sortlist1);

			if (StringUtils.isBlank(sort2)) {
				for (ZcourseSort zcourseSort : sortlist1) {
					sortlist.add(zcourseSort.getId());

					// 第三级
					sort.setParentId(zcourseSort.getId());
					List<ZcourseSort> sortlist12 = zcourseSortService.findList(sort);
					if (StringUtils.isBlank(sort3)) {
						for (ZcourseSort zcourseSort2 : sortlist12) {
							sortlist.add(zcourseSort2.getId());
						}
					}
				}
			}

			sortlist.add(sort1);
		}
		if (sort2 != null && !"".equals(sort2)) {
			// 三级分类信息列表
			sort.setParentId(sort2);
			sortlist1 = zcourseSortService.findList(sort);
			model.addAttribute("sortlist3", sortlist1);

			for (ZcourseSort zcourseSort : sortlist1) {
				sortlist.add(zcourseSort.getId());
			}
			sortlist.add(sort2);
		}

		// 是否点击了第三级
		if (sort3 != null && !"".equals(sort3)) {
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
	 * 
	 * @param zcourse
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "coursedetail")
	public String coursedetail(Zcourse zcourse, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("mendId", "2");

		Zuser user = (Zuser) request.getSession().getAttribute("sessionMyinfo");
		if (user == null || StringUtils.isBlank(user.getId())) {
			model.addAttribute("msg", "请登陆.");
			return "front/login";
		}
		zcourse = zcourseService.get(zcourse.getId());
		// 当前专业是否购买
		ZcourseOrder zcourseOrder = new ZcourseOrder();
		zcourseOrder.setCourseid(zcourse.getParentid());
		zcourseOrder.setUserid(user.getId());
		zcourseOrder.setDelFlag("0");
		List<ZcourseOrder> orderlist = zcourseOrderService.findMyorderByid(zcourseOrder);
		System.out.println("___________________________________");
		if (orderlist != null && orderlist.size() == 1) {
			zcourseOrder = orderlist.get(0);

			int daynum = StringUtils.isNumeric(zcourseOrder.getExptime()) ? Integer.parseInt(zcourseOrder.getExptime())	: 0;

			System.out.println("paystatus_________________" + zcourseOrder.getPaystatus());
			// 当前专业是否到期
			if (ZcourseSortUtils.isExp(zcourseOrder.getPaytime(), daynum)) {
				if ("4".equals(zcourseOrder.getPaystatus())) { // 是否是内部用户的专业
					System.out.println("5zzzz");
					model.addAttribute("ispay", "yespay");

					// 是否支付成功
				} else if ("2".equals(zcourseOrder.getPaystatus())) {
					System.out.println("1zzzzzzz");
					model.addAttribute("ispay", "yespay");
				} else {
					System.out.println("2zzzzzzzzzzz");
					model.addAttribute("ispay", isCoursePay(zcourse.getId(), user.getId()));
				}
			} else if ("yespay".equals(isCoursePay(zcourse.getId(), user.getId()))) {
				model.addAttribute("ispay", "yespay");
			} else {
				System.out.println("3zzzzzzzz");
				model.addAttribute("ispay", isCoursePay(zcourse.getId(), user.getId()));
			}
		} else if ("yespay".equals(isCoursePay(zcourse.getId(), user.getId()))) {
			System.out.println("6zzzzzzzz");
			model.addAttribute("ispay", "yespay");

		} else {
			System.out.println("4zzzzzzzz");
			model.addAttribute("ispay", "nopay");
		}
		model.addAttribute("zcourse", zcourse);
		return "front/coursedetail";
	}

	/**
	 * 当前课程是否购买
	 * 
	 * @param zcourse
	 * @return
	 */
	public String isCoursePay(String courseid, String userid) {
		// 当前专业是否购买
		ZcourseOrder zcourseOrder = new ZcourseOrder();
		zcourseOrder.setCourseid(courseid);
		zcourseOrder.setUserid(userid);
		zcourseOrder.setDelFlag("0");
		zcourseOrder.setShoptype("2");
		List<ZcourseOrder> orderlist = zcourseOrderService.findList(zcourseOrder);
		if (orderlist != null && orderlist.size() == 1) {
			zcourseOrder = orderlist.get(0);

			int daynum = StringUtils.isNumeric(zcourseOrder.getExptime()) ? Integer.parseInt(zcourseOrder.getExptime())	: 0;

			System.out.println("paystatus_________________" + zcourseOrder.getPaystatus());
			// 当前专业是否到期
			if (ZcourseSortUtils.isExp(zcourseOrder.getPaytime(), daynum)) {
				if ("4".equals(zcourseOrder.getPaystatus())) { // 是否是内部用户的专业
					System.out.println("5");
					return "yespay";
					// 是否支付成功
				} else if ("2".equals(zcourseOrder.getPaystatus())) {
					System.out.println("1");
					return "yespay";
				} else {
					System.out.println("2");
					return "nopay";
				}
			} else {
				System.out.println("2");
				return "nopay";
			}
		} else {
			return "nopay";
		}
	}

	/**
	 * 进入课程购买
	 * 
	 * @param zcourse
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "gotopaycourse")
	public String gotopaycourse(Zcourse zcourse, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		model.addAttribute("mendId", "2");
		Zuser user = (Zuser) request.getSession().getAttribute("sessionMyinfo");
		if (user == null || StringUtils.isBlank(user.getId())) {
			model.addAttribute("msg", "请登陆.");
			return "front/login";
		}
		String type = request.getParameter("type");
		String id = request.getParameter("id");

		// 判断是否列表页过来还是详情页 2为列表页面
		if ("2".equals(type)) {
			model.addAttribute("id", id);
		} else {
			zcourse = zcourseService.get(zcourse.getId());
			model.addAttribute("type", "1");
			model.addAttribute("id", zcourse.getId());
			model.addAttribute("zcourse", zcourse);
		}
		return "front/pay";
	}

	/**
	 * 课程购买 id 专业ID
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "payCourse")
	public String payCourse(String id, HttpServletRequest request, HttpServletResponse response, Model model) {
		PrintWriter out = null;
		try {
			/** 验证是否登录 */
			Zuser user = (Zuser) request.getSession().getAttribute("sessionMyinfo");
			if (user == null || StringUtils.isBlank(user.getId())) {
				model.addAttribute("msg", "请登陆.");
				return "front/login";
			}
			// Zcourse zcourse = zcourseService.get(id);

			String type = request.getParameter("type");
			
			long orderid = new Date().getTime();
			String actualcost = "";   // 价格
			String body = "";		 //商品描述
			ZcourseOrder zcourseOrder = new ZcourseOrder();
			List<ZcourseOrder> orderlist = new ArrayList<>();
			//1为课程购买    2为专业购买
			if("1".equals(type)){			
				
				Zcourse zcourse = zcourseService.get(id);									//课程
				ZcourseSort sort = zcourseSortService.get(zcourse.getParentid());			//专业
				zcourseOrder.setCourseid(id);
				zcourseOrder.setShoptype("2");
				zcourseOrder.setUserid(user.getId());
				zcourseOrder.setDelFlag("0");
				zcourseOrder.setPayprice(zcourse.getPrice());
				zcourseOrder.setExptime(sort.getValidity());
				zcourseOrder.setCourseprice(zcourse.getPrice());
				actualcost = zcourse.getPrice(); 
				body = zcourse.getTitle();
				orderlist = zcourseOrderService.findMyorderByid(zcourseOrder);
				
				System.out.println(zcourse.getPrice()+"_____________________________________________");
				//这里是免费课程
				if("0".equals(zcourse.getPrice()) || "0.00".equals(zcourse.getPrice())){
					zcourseOrder.setPaystatus("2");
					zcourseOrder.setPayprice("0");
					zcourseOrder.setPaytime(new Date());
					zcourseOrder.setCourseprice("0");
					zcourseOrder.setPaytype("3");
					zcourseOrderService.save(zcourseOrder);
					return "redirect:myCourseUser";
				}
				
			}else{				
			
				ZcourseSort sort = zcourseSortService.get(id);			
				// 查询订单				
				zcourseOrder.setCourseid(id);
				zcourseOrder.setUserid(user.getId());
				zcourseOrder.setDelFlag("0");
				zcourseOrder.setPayprice(sort.getPrice());
				zcourseOrder.setExptime(sort.getValidity());
				zcourseOrder.setCourseprice(sort.getPrice());
				actualcost = sort.getPrice(); 
				body = sort.getName();
				orderlist = zcourseOrderService.findMyorderByid(zcourseOrder);
			}			
			if (orderlist != null && orderlist.size() > 0) {
				zcourseOrder = orderlist.get(0);

				// 当前专业是否到期
				int daynum = StringUtils.isNumeric(zcourseOrder.getExptime())
						? Integer.parseInt(zcourseOrder.getExptime()) : 0;
				if (ZcourseSortUtils.isExp(zcourseOrder.getPaytime(), daynum)) {
					zcourseOrder.setBuynum(zcourseOrder.getBuynum() + 1);
				}

				zcourseOrder.setPayid(orderid + "");
				zcourseOrder.setPaytype("1");
				zcourseOrderService.save(zcourseOrder);
			} else {
				zcourseOrder.setPayid(orderid + "");
				zcourseOrder.setPaytype("1");
				zcourseOrderService.save(zcourseOrder);
			}
//			String actualcost = sort.getPrice(); // 价格
			System.out.println("total__________________________" + actualcost);
			/** 支付宝支付 */
			// 获得初始化的AlipayClient
			AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
					AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
					AlipayConfig.sign_type);
			// 设置请求参数

			AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
			alipayRequest.setReturnUrl(AlipayConfig.return_url);
			alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
			alipayRequest.setBizContent("{\"out_trade_no\":\"" + orderid + "\"," + "\"total_amount\":\"" + actualcost
					+ "\"," + "\"subject\":\"" + body + "\"," + "\"body\":\"" + body + "\","
					+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
			// 请求
			String result = alipayClient.pageExecute(alipayRequest).getBody();
			/** 设置回传格式 */
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			out = response.getWriter();
			// 输出
			out.write(result);// 直接将完整的表单html输出到页面

			//
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != out) {
				out.flush();
				out.close();
			}
		}
		return null;
	}

	/**
	 * 支付宝充值回调
	 */

	@ResponseBody
	@RequestMapping(value = "alipayanotify")
	public String alipayaNotifyOrder(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("支付宝回调：----------------------------------");
		PrintWriter out = null;
		try {
			/** 设置回传格式 */
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			out = response.getWriter();
			Map<String, String> params = new HashMap<String, String>();
			Map<String, String[]> requestParams = request.getParameterMap();
			for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
			// boolean signVerified = AlipaySignature.rsaCheckV1(params,
			// AlipayConfig.alipay_public_key, AlipayConfig.charset,
			// AlipayConfig.sign_type); //调用SDK验证签名
			boolean signVerified = true;
			if (signVerified) {// 验证成功
				// 商户订单号
				// String out_trade_no = new
				// String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
				String out_trade_no = request.getParameter("out_trade_no");
				// 支付宝交易号
				// String trade_no = new
				// String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
				String trade_no = request.getParameter("trade_no");
				// 交易状态
				// String trade_status = new
				// String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
				String trade_status = request.getParameter("trade_status");
				// 交易状态
				// String body = new
				// String(request.getParameter("body").getBytes("ISO-8859-1"),"UTF-8");
				String body = request.getParameter("body");
				// 订单金额
				// String total_amount = new
				// String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
				String total_amount = request.getParameter("total_amount");
				System.out.println("支付宝通知信息：" + out_trade_no + "--------" + trade_no + "--------" + trade_status
						+ "--------" + body + "--------" + total_amount);
				/** 查询订单信息 */
				ZcourseOrder orders = new ZcourseOrder();
				orders.setPayid(out_trade_no);
				// 跟据订单号查询订单信息
				List<ZcourseOrder> list = zcourseOrderService.findList(orders);

				if (list != null && list.size() > 0) {
					orders = list.get(0);

					String total_fee = total_amount;
					if (total_fee != null && !"".equals(total_fee)) {
						// 支付金额
						BigDecimal fee = new BigDecimal(total_fee);
						orders.setPayprice(fee + "");
					}
					// 判断是否支付成功
					orders.setPaystatus("2");
					orders.setPaytime(new Date());
					zcourseOrderService.save(orders);
				}

				out.println("success");
			} else {// 验证失败
				out.println("fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}

		return null;
	}

	/**
	 * 支付宝通知返回
	 */
	@RequestMapping(value = "alipayReturn")
	public String alipayReturn(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 重定向到我的订单
		return "redirect:myCourseUser";
	}

	/**
	 * 课程观看
	 * 
	 * @param zcourse
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "gotolookcourse")
	public String gotolookcourse(Zcourse zcourse, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Zuser user = (Zuser) request.getSession().getAttribute("sessionMyinfo");
		if (user == null || StringUtils.isBlank(user.getId())) {
			model.addAttribute("msg", "请登陆.");
			return "front/login";
		}

		ZcourseOrder zcourseOrder = new ZcourseOrder();
		zcourseOrder.setCourseid(zcourse.getId());
		zcourseOrder.setUserid(user.getId());
		zcourseOrder.setDelFlag("0");
		List<ZcourseOrder> orderlist = zcourseOrderService.findMyorderByid(zcourseOrder);
		if (orderlist != null && orderlist.size() > 0) {

		} else {
			zcourseOrder.setPaystatus("2");
			zcourseOrder.setPayprice("0");
			zcourseOrder.setPaytime(new Date());
			zcourseOrder.setCourseprice("0");
			zcourseOrder.setPaytype("3");
			zcourseOrderService.save(zcourseOrder);
		}

		// 课时列表
		ZcourseHour zcourseHour = new ZcourseHour();
		zcourseHour.setCourseid(zcourse.getId());
		List<ZcourseHour> hourlist = zcourseHourService.findList(zcourseHour);
		for (ZcourseHour zcourseHour2 : hourlist) {
//			使用点播中的视频ID获取视频地址
			List<Map<String, Object>> list = AliyunRecords.getVideoPayUrl(zcourseHour2.getUrl());
			if (list != null && list.size() > 0){
				System.out.println(list.get(list.size()-1).get("PlayURL").toString().trim()+"_________________________________________>");
				zcourseHour2.setTrueUrl(list.get(list.size()-1).get("PlayURL").toString().trim());
			}
		}

		// 课程观看时间记录
		// ZcourseUser zcourseUser = new ZcourseUser();
		// zcourseUser.setUserid(user.getId());
		// zcourseUser.setCourseid(zcourse.getId());
		// zcourseUser.setDelFlag("0");
		// zcourseUser.setUsertime("0");
		// List<ZcourseUser> culist = zcourseUserService.findList(zcourseUser);
		// if (culist != null && culist.size() > 0) {
		// } else {
		// zcourseUserService.save(zcourseUser);
		// }
		zcourse = zcourseService.get(zcourse.getId());

		model.addAttribute("zcourse", zcourse);
		model.addAttribute("hourlist", hourlist);
		return "front/lookvideo";
	}

	/**
	 * 
	 * @param path
	 */
	@ResponseBody
	@RequestMapping(value = "createCourseuser")
	public String createCourseuser(@RequestParam(required = false, value = "courseid") String courseid,
			HttpServletRequest request, HttpServletResponse response) {
		Zuser user = (Zuser) request.getSession().getAttribute("sessionMyinfo");
		if (user != null && !StringUtils.isBlank(user.getId())) {
			// 课程观看时间记录
			ZcourseUser zcourseUser = new ZcourseUser();
			zcourseUser.setUserid(user.getId());
			zcourseUser.setCourseid(courseid);
			zcourseUser.setDelFlag("0");
			zcourseUser.setUsertime("0");
			List<ZcourseUser> culist = zcourseUserService.findList(zcourseUser);
			if (culist != null && culist.size() > 0) {
			} else {
				zcourseUserService.save(zcourseUser);
			}
		}
		return null;
	}

	/**
	 * 课程观看记录
	 * 
	 * @param courseid
	 *            课程编号
	 * @param current
	 *            当前播放时间
	 * @param duration
	 *            视频总时长
	 */
	@ResponseBody
	@RequestMapping(value = "courseLookRecord")
	public String courseLookRecord(@RequestParam(required = false, value = "courseid") String courseid,
			@RequestParam(required = false, value = "current") String current,
			@RequestParam(required = false, value = "duration") String duration, HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println("look......." + duration + "........" + current);

		Zuser user = (Zuser) request.getSession().getAttribute("sessionMyinfo");
		if (user != null && !StringUtils.isBlank(user.getId())) {

			ZcourseUser zcourseUser = new ZcourseUser();
			zcourseUser.setUserid(user.getId());
			zcourseUser.setCourseid(courseid);
			zcourseUser.setDelFlag("0");

			List<ZcourseUser> culist = zcourseUserService.findList(zcourseUser);
			if (culist != null && culist.size() > 0) {

				zcourseUser = new ZcourseUser();
				zcourseUser.setId(culist.get(0).getId());
				zcourseUser.setCoursetime(duration);
				zcourseUser.setLasttime(current);
				zcourseUser.setUpdateDate(new Date());
				zcourseUserService.updateUsertime(zcourseUser);

			}
		}

		return null;
	}

	/**
	 * 测试题列表信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "questionlist")
	public String questionlist(Ztest test, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("mendId", "3");

		// 选择的分类的列表
		List<String> sortlist = new ArrayList<String>();
		// 前台选择的分类ID
		String sort1 = request.getParameter("classflyone");
		String sort2 = request.getParameter("classflytwo");
		String sort3 = request.getParameter("classflythr");

		ZcourseSort sort = new ZcourseSort();
		sort.setDelFlag("0");

		// 一级分类信息列表
		sort.setParentId("0");
		List<ZcourseSort> sortlist1 = zcourseSortService.findList(sort);
		model.addAttribute("sortlist1", sortlist1);

		// 将分类信息导入
		if (sort1 != null && !"".equals(sort1)) {
			// 二级分类信息列表
			sort.setParentId(sort1);
			sortlist1 = zcourseSortService.findList(sort);
			model.addAttribute("sortlist2", sortlist1);

			if (StringUtils.isBlank(sort2)) {
				for (ZcourseSort zcourseSort : sortlist1) {
					sortlist.add(zcourseSort.getId());

					// 第三级
					sort.setParentId(zcourseSort.getId());
					List<ZcourseSort> sortlist12 = zcourseSortService.findList(sort);
					if (StringUtils.isBlank(sort3)) {
						for (ZcourseSort zcourseSort2 : sortlist12) {
							sortlist.add(zcourseSort2.getId());
						}
					}
				}
			}
			sortlist.add(sort1);
		}
		if (sort2 != null && !"".equals(sort2)) {
			// 三级分类信息列表
			sort.setParentId(sort2);
			sortlist1 = zcourseSortService.findList(sort);
			model.addAttribute("sortlist3", sortlist1);
			if (!StringUtils.isNoneBlank(sort3)) {
				for (ZcourseSort zcourseSort : sortlist1) {
					sortlist.add(zcourseSort.getId());
				}
			}
			sortlist.add(sort2);
		}

		// 是否点击了第三级
		if (sort3 != null && !"".equals(sort3)) {
			sortlist.add(sort3);
		}

		test.setSortlist(sortlist);
		test.setRemarks("yes");
		Page<Ztest> page = ztestService.findPage(new Page<Ztest>(request, response), test);
		model.addAttribute("page", page);
		model.addAttribute("test", test);

		model.addAttribute("classflyone", sort1);
		model.addAttribute("classflytwo", sort2);
		model.addAttribute("classflythr", sort3);
		
		String message = request.getParameter("message");
		if("notest".equals(message)){
			model.addAttribute("message", "当前专业下未分配测试题，请选择其它专业");
		}
		return "front/questionlist";
	}

	/**
	 * 进入考试
	 * 
	 * @param test
	 * @return
	 */
	@RequestMapping(value = "gotoquestion")
	public String gotoquestion(Ztest test, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("mendId", "3");

		Zuser user = (Zuser) request.getSession().getAttribute("sessionMyinfo");
		if (user == null || StringUtils.isBlank(user.getId())) {
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

		zuserTestService.deleteUesrtest(usertest); // 删除用户考试记录

		// 查找出当前试卷的所有题目
		ZtestQuestion ztestQuestion = new ZtestQuestion();
		ztestQuestion.setTestid(test.getId());
		ztestQuestion.setDelFlag("0");
		List<ZtestQuestion> testquestionlist = ztestQuestionDao.findListLeftquestion(ztestQuestion);

		if (testquestionlist != null && testquestionlist.size() > 0) {

		} else {
			model.addAttribute("isnottest", "yes");
			return "front/questionlist";
		}
		// 添加用户需要考试题的记录
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
		if (StringUtils.isNumeric(time)) {
			model.addAttribute("time", Integer.parseInt(time) * 60);
		}

		return "front/test";
	}

	/**
	 * 进入随机考试题
	 * 
	 * @param test
	 * @return
	 */
	@RequestMapping(value = "gotoquestionRandom")
	public String gotoquestionRandom(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("mendId", "3");

		Zuser user = (Zuser) request.getSession().getAttribute("sessionMyinfo");
		if (user == null || StringUtils.isBlank(user.getId())) {
			model.addAttribute("msg", "请登陆.");
			return "front/login";
		}

		String type = request.getParameter("type"); // 专业类型
		ZtestRandom ztestRandom = ztestRandomService.get(type);
		if (ztestRandom == null || "".equals(ztestRandom)) {
			model.addAttribute("message", "当前专业下未分配测试题，请选择其它专业");
//			return "front/questionlist";
			return "redirect:questionlist?message=notest";
		}
		Ztest test = new Ztest();
		test.setTitle("随机测试题");
		test.setTesttime("60");
		test.setType("4");
		List<Ztest> list = ztestService.findList(test);
		for (Ztest ztest : list) {
			ZuserTest usertest = new ZuserTest();
			usertest.setUserid(user.getId());
			usertest.setTestid(ztest.getId());
			zuserTestService.deleteUesrtest(usertest); // 删除用户考试记录
		}
		// test.setSum(value);
		test.setParentid(type);
		ztestService.save(test); // 创建试题

		model.addAttribute("testid", test.getId());
		model.addAttribute("test", test);

		List<Zquestion> questionlist = new ArrayList<>();
		// 查找出当前试卷的所有题目
		Zquestion zquestion = new Zquestion();
		zquestion.setParentid(type);

		// 单选题
		zquestion.setLimit(StringUtils.isNumeric(ztestRandom.getRadio()) ? Integer.parseInt(ztestRandom.getRadio()) : 0);
		zquestion.setType("1");
		List<Zquestion> qlist = zquestionService.findRandList(zquestion);
		for (Zquestion zquestion2 : qlist) {
			questionlist.add(zquestion2);
		}
		// 多选题
		zquestion.setLimit(StringUtils.isNumeric(ztestRandom.getCheckbox()) ? Integer.parseInt(ztestRandom.getCheckbox()) : 0);
		zquestion.setType("2");
		qlist = zquestionService.findRandList(zquestion);
		for (Zquestion zquestion2 : qlist) {
			questionlist.add(zquestion2);
		}
		// 判断题
		zquestion.setLimit(StringUtils.isNumeric(ztestRandom.getJudge()) ? Integer.parseInt(ztestRandom.getJudge()) : 0);
		zquestion.setType("3");
		qlist = zquestionService.findRandList(zquestion);
		for (Zquestion zquestion2 : qlist) {
			questionlist.add(zquestion2);
		}

		if (questionlist != null && questionlist.size() > 0) {
			// 添加用户需要考试题的记录
			int sort = 1;
			for (Zquestion ztestQuestion2 : questionlist) {
				ZuserTest insertTest = new ZuserTest();
				insertTest.setUserid(user.getId());
				insertTest.setTestid(test.getId());
				insertTest.setQuestionid(ztestQuestion2.getId());
				insertTest.setSort(sort);
				sort++;
				zuserTestService.save(insertTest);
			}

			myTestlist(test.getId(), user.getId(), model, "yes");

			String time = test.getTesttime();
			if (StringUtils.isNumeric(time)) {
				model.addAttribute("time", Integer.parseInt(time) * 60);
			}
			return "front/test";
		} else {
			model.addAttribute("message", "当前专业下未分配测试题，请选择其它专业");
			return "redirect:questionlist?message=notest";
		}

	}

	/**
	 * 用户需要考试的题目
	 * 
	 * @param testid
	 *            考试卷编号
	 * @param userid
	 *            用户编号
	 * @param isOne
	 *            是否为第一题 yes or no
	 * @param model
	 */
	public void myTestlist(String testid, String userid, Model model, String isOne) {
		ZuserTest usertest = new ZuserTest();
		usertest.setTestid(testid);
		usertest.setUserid(userid);
		usertest.setDelFlag("0");

		List<ZuserTest> list = zuserTestService.findList(usertest);
		model.addAttribute("mytestlist", list);

		if ("yes".equals(isOne)) {
			if (list != null && list.size() > 0) {
				currentTest(list.get(0).getId(), model);
			}
		}
	}

	/**
	 * 当前测试题
	 * 
	 * @param usertestid
	 *            当前用户测试记录的编号
	 * @param model
	 */
	public void currentTest(String usertestid, Model model) {
		// 当前用户测试记录的编号
		ZuserTest usertest = zuserTestService.get(usertestid);
		model.addAttribute("usertest", usertest);
		model.addAttribute("myusertestid", usertestid);

		Zquestion zquestion = zquestionService.get(usertest.getQuestionid());
		// 下一题的编号
		List<ZuserTest> nexttest = zuserTestService.findNextTest(usertest);
		if (nexttest != null && nexttest.size() > 0) {
			model.addAttribute("nextusertestid", nexttest.get(0).getId());
		}

		if (zquestion != null && StringUtils.isNotBlank(zquestion.getId())) {
			ZquestionAnswer zquestionAnswer = new ZquestionAnswer();
			zquestionAnswer.setQuesId(zquestion.getId());
			zquestionAnswer.setDelFlag("0");

			List<ZquestionAnswer> answerList = zquestionAnswerService.findList(zquestionAnswer);
			model.addAttribute("answerList", answerList);
		}

		model.addAttribute("zquestion", zquestion);

	}

	/**
	 * 跳转到某题
	 */
	@RequestMapping(value = "gotoQuestion")
	public String bleow(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("mendId", "3");

		Zuser user = (Zuser) request.getSession().getAttribute("sessionMyinfo");
		if (user == null || StringUtils.isBlank(user.getId())) {
			model.addAttribute("msg", "请登陆.");
			return "front/login";
		}

		String mytestid = request.getParameter("mytestid"); // 跳转到当前题
		String testid = request.getParameter("testid");

		model.addAttribute("testid", testid);
		model.addAttribute("test", ztestService.get(testid));
		model.addAttribute("time", request.getParameter("time"));

		myTestlist(testid, user.getId(), model, "");
		currentTest(mytestid, model);
		
		ZuserTest test = zuserTestService.get(mytestid);
		model.addAttribute("mytestdati", test);
		return "front/test";
	}

	/**
	 * 答题处理
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "answer")
	public String answer(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("mendId", "3");

		Zuser user = (Zuser) request.getSession().getAttribute("sessionMyinfo");
		if (user == null || StringUtils.isBlank(user.getId())) {
			model.addAttribute("msg", "请登陆.");
			return "front/login";
		}
		String testid = request.getParameter("testid"); // 当前试卷
		model.addAttribute("testid", testid);
		model.addAttribute("test", ztestService.get(testid));

		String currentanswer = request.getParameter("currentanswer"); // 当前答案
		String myusertestid = request.getParameter("myusertestid"); // 当前答题记录的编号
		String isCorrect = request.getParameter("isCorrect");

		String nextusertestid = request.getParameter("nextusertestid"); // 下一题题目的编号
		ZuserTest usertest = zuserTestService.get(myusertestid);
		usertest.setAnswerid(currentanswer);
		if(StringUtils.isNotBlank(currentanswer)){
			usertest.setIsselected("1");
		}
		usertest.setIstrue(isCorrect);
		zuserTestService.save(usertest);

		String time = request.getParameter("time");
		model.addAttribute("time", time);
		// 是否完成答卷
		if (nextusertestid != null && !"".equals(nextusertestid)) {
			myTestlist(testid, user.getId(), model, "");
			currentTest(nextusertestid, model);
			return "front/test";
		} else {
			System.out.println(testid);
			ZuserTest myusertest = new ZuserTest();
			myusertest.setTestid(testid);
			myusertest.setUserid(user.getId());
			myusertest.setDelFlag("0");
			System.out.println("------------------------usertest");
			List<ZuserTest> list = zuserTestService.findMyUserTest(myusertest);

			int iscorrect = 0; // 答对题数
			int fraction = 0; // 答对分数
			for (ZuserTest zuserTest : list) {
				if ("1".equals(zuserTest.getIstrue())) {
					iscorrect++;
					Zquestion question = zquestionService.get(zuserTest.getQuestionid());
					if (question != null && StringUtils.isNumeric(question.getDefaultFraction())) {
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

	/**
	 * 忘记密码
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "fotgetpassword")
	public String fotgetpassword(HttpServletRequest request, HttpServletResponse response, Model model) {

		return "front/fotgetpassword";
	}

	/**
	 * 找回密码
	 */
	@RequestMapping(value = "forgetPwd")
	public String forgetPwd(HttpServletRequest request, HttpServletResponse response, Model model) {
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");

		Zuser user = new Zuser();
		user.setPhone(phone);
		user.setDelFlag("0");

		List<Zuser> list = zuserService.findList(user);
		if (list != null && list.size() > 0) {
			user = list.get(0);
		}
		user.setPassword(password);
		zuserService.save(user);

		request.getSession().setAttribute("sessionMyinfo", user);
		return "front/myinfo";
	}

	/**
	 * 获取短信验证码
	 */
	@ResponseBody
	@RequestMapping(value = "getValidate")
	public String getValidate(HttpServletRequest request, HttpServletResponse response, Model model) {

		String phone = request.getParameter("phone");
		System.out.println(phone);
		Zuser finduser = new Zuser();
		finduser.setPhone(phone);
		finduser.setDelFlag("0");

		PrintWriter out = null;
		try {
			/** 设置回传格式 */
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			out = response.getWriter();
			String templateid = request.getParameter("templateid"); // 短信模版ID
			List<Zuser> list = zuserService.findList(finduser); // 根据手机号查询用户
			if (templateid.trim().equals(SmsAliTool.ZCYZ_MOULD_ID)
					|| templateid.trim().equals(SmsAliTool.BDSJ_MOULD_ID)) { // 注册/找回密码验证码
				if (list != null && list.size() > 0) {
					out.println("EXIST"); // 手机号已注册或者绑定
				} else {
					// 生成随机数验证码
					String random = StringUtils.getRandom(6);
					SmsAliTool.sendSms(templateid, phone, "{\"code\":\"" + random + "\"}");
					out.println(random);
				}
			} else { // 找回/重置密码验证码
				if (list == null || list.size() < 1) {
					out.println("NOEXIST"); // 手机号已注册或者绑定
				} else {
					// 生成随机数验证码
					String random = StringUtils.getRandom(6);
					SmsAliTool.sendSms(templateid, phone, "{\"code\":\"" + random + "\"}");
					out.println(random);
				}
			}
		} catch (Exception e) {
			out.println("ERROR");
			e.printStackTrace();
		} finally {
			if (null != out) {
				out.flush();
				out.close();
			}
		}
		return null;
	}

	/**
	 * 获取机构JSON数据。
	 * 
	 * @param extId
	 *            排除的ID
	 * @param type
	 *            类型（1：公司；2：部门/小组/其它：3：用户）
	 * @param grade
	 *            显示级别
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId,
			@RequestParam(required = false) String type, @RequestParam(required = false) Long grade,
			@RequestParam(required = false) Boolean isAll, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();

		ZcourseSort sort = new ZcourseSort();
		sort.setDelFlag("0");
		List<ZcourseSort> list = zcourseSortService.findList(sort);

		for (int i = 0; i < list.size(); i++) {
			ZcourseSort e = list.get(i);
			if ((StringUtils.isBlank(extId) || (extId != null && !extId.equals(e.getId())))) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("pIds", e.getParentIds());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}

	/**
	 * 树结构选择标签（treeselect.tag）
	 */
	@RequestMapping(value = "treeselect")
	public String treeselect(HttpServletRequest request, Model model) {
		model.addAttribute("url", request.getParameter("url")); // 树结构数据URL
		model.addAttribute("extId", request.getParameter("extId")); // 排除的编号ID
		model.addAttribute("checked", request.getParameter("checked")); // 是否可复选
		model.addAttribute("selectIds", request.getParameter("selectIds")); // 指定默认选中的ID
		model.addAttribute("isAll", request.getParameter("isAll")); // 是否读取全部数据，不进行权限过滤
		model.addAttribute("module", request.getParameter("module")); // 过滤栏目模型（仅针对CMS的Category树）
		return "modules/sys/tagTreeselectF";
	}

	/**
	 * 图标选择标签（iconselect.tag）
	 */
	@RequestMapping(value = "iconselect")
	public String iconselect(HttpServletRequest request, Model model) {
		model.addAttribute("value", request.getParameter("value"));
		return "modules/sys/tagIconselect";
	}

}
