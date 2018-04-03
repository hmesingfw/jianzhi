/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.web.testrandom;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.hm.entity.course_sort.ZcourseSort;
import com.thinkgem.jeesite.modules.hm.entity.testrandom.ZtestRandom;
import com.thinkgem.jeesite.modules.hm.service.course_sort.ZcourseSortService;
import com.thinkgem.jeesite.modules.hm.service.testrandom.ZtestRandomService;
import com.thinkgem.jeesite.modules.hm.utils.ZcourseTestRandom;

/**
 * 随机测试题Controller
 * @author hm
 * @version 2018-04-03
 */
@Controller
@RequestMapping(value = "${adminPath}/hm/testrandom/ztestRandom")
public class ZtestRandomController extends BaseController {

	@Autowired
	private ZtestRandomService ztestRandomService;
	@Autowired
	private ZcourseSortService zcourseSortService;
	
	@ModelAttribute
	public ZtestRandom get(@RequestParam(required=false) String id) {
		ZtestRandom entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ztestRandomService.get(id);
		}
		if (entity == null){
			entity = new ZtestRandom();
		}
		return entity;
	}
	
	@RequiresPermissions("hm:testrandom:ztestRandom:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZtestRandom ztestRandom, HttpServletRequest request, HttpServletResponse response, Model model) {
		ZcourseSort zcourseSort = new ZcourseSort();
		
		List<ZcourseSort> list = new ArrayList<ZcourseSort>();
		List<ZcourseSort> sortlist = zcourseSortService.findList(zcourseSort);
		ZcourseSort.sortList(list, sortlist, "0", true);
		
		model.addAttribute("list", list);
		
		
		
		
		return "modules/hm/testrandom/ztestRandomList";
	}

	@RequiresPermissions("hm:testrandom:ztestRandom:view")
	@RequestMapping(value = "form")
	public String form(ZtestRandom ztestRandom, Model model) {
		model.addAttribute("ztestRandom", ztestRandom);
		return "modules/hm/testrandom/ztestRandomForm";
	}

	@RequiresPermissions("hm:testrandom:ztestRandom:edit")
	@RequestMapping(value = "save")
	public String save(ZtestRandom ztestRandom, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ztestRandom)){
			return form(ztestRandom, model);
		}
		ztestRandomService.save(ztestRandom);
		
		//刷新内存
		ZtestRandom sort = new ZtestRandom();
		sort.setDelFlag("0");
		List<ZtestRandom> courselist = ztestRandomService.findList(sort);
		CacheUtils.put(ZcourseTestRandom.CACHE_testRandom_LIST, courselist);
		
		addMessage(redirectAttributes, "保存随机测试题成功");
		return "redirect:"+Global.getAdminPath()+"/hm/testrandom/ztestRandom/?repage";
	}
	
	@RequiresPermissions("hm:testrandom:ztestRandom:edit")
	@RequestMapping(value = "delete")
	public String delete(ZtestRandom ztestRandom, RedirectAttributes redirectAttributes) {
		ztestRandomService.delete(ztestRandom);
		addMessage(redirectAttributes, "删除随机测试题成功");
		return "redirect:"+Global.getAdminPath()+"/hm/testrandom/ztestRandom/?repage";
	}

}