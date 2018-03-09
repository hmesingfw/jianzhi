/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.web.course_sort;

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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.hm.entity.course_sort.ZcourseSort;
import com.thinkgem.jeesite.modules.hm.service.course_sort.ZcourseSortService;

/**
 * 专业分类Controller
 * @author hm
 * @version 2018-03-09
 */
@Controller
@RequestMapping(value = "${adminPath}/hm/course_sort/zcourseSort")
public class ZcourseSortController extends BaseController {

	@Autowired
	private ZcourseSortService zcourseSortService;
	
	@ModelAttribute
	public ZcourseSort get(@RequestParam(required=false) String id) {
		ZcourseSort entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zcourseSortService.get(id);
		}
		if (entity == null){
			entity = new ZcourseSort();
		}
		return entity;
	}
	
	@RequiresPermissions("hm:course_sort:zcourseSort:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZcourseSort zcourseSort, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		List<ZcourseSort> list = new ArrayList<ZcourseSort>();
		List<ZcourseSort> sortlist = zcourseSortService.findList(zcourseSort);
		ZcourseSort.sortList(list, sortlist, "0", true);
		
		model.addAttribute("list", list);
		
		return "modules/hm/course_sort/zcourseSortList";
	}

	@RequiresPermissions("hm:course_sort:zcourseSort:view")
	@RequestMapping(value = "form")
	public String form(ZcourseSort zcourseSort, Model model) {
		model.addAttribute("zcourseSort", zcourseSort);
		return "modules/hm/course_sort/zcourseSortForm";
	}

	@RequiresPermissions("hm:course_sort:zcourseSort:edit")
	@RequestMapping(value = "save")
	public String save(ZcourseSort zcourseSort, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zcourseSort)){
			return form(zcourseSort, model);
		}
		zcourseSortService.save(zcourseSort);
		addMessage(redirectAttributes, "保存专业分类成功");
		return "redirect:"+Global.getAdminPath()+"/hm/course_sort/zcourseSort/?repage";
	}
	
	@RequiresPermissions("hm:course_sort:zcourseSort:edit")
	@RequestMapping(value = "delete")
	public String delete(ZcourseSort zcourseSort, RedirectAttributes redirectAttributes) {
		zcourseSortService.delete(zcourseSort);
		addMessage(redirectAttributes, "删除专业分类成功");
		return "redirect:"+Global.getAdminPath()+"/hm/course_sort/zcourseSort/?repage";
	}

}