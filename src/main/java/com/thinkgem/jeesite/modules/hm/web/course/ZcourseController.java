/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.web.course;

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
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.hm.entity.course.Zcourse;
import com.thinkgem.jeesite.modules.hm.service.course.ZcourseService;

/**
 * 课程信息管理Controller
 * @author hm
 * @version 2018-03-12
 */
@Controller
@RequestMapping(value = "${adminPath}/hm/course/zcourse")
public class ZcourseController extends BaseController {

	@Autowired
	private ZcourseService zcourseService;
	
	@ModelAttribute
	public Zcourse get(@RequestParam(required=false) String id) {
		Zcourse entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zcourseService.get(id);
		}
		if (entity == null){
			entity = new Zcourse();
		}
		return entity;
	}
	
	@RequiresPermissions("hm:course:zcourse:view")
	@RequestMapping(value = {"list", ""})
	public String list(Zcourse zcourse, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Zcourse> page = zcourseService.findPage(new Page<Zcourse>(request, response), zcourse); 
		model.addAttribute("page", page);
		return "modules/hm/course/zcourseList";
	}

	@RequiresPermissions("hm:course:zcourse:view")
	@RequestMapping(value = "form")
	public String form(Zcourse zcourse, Model model) {
		model.addAttribute("zcourse", zcourse);
		return "modules/hm/course/zcourseForm";
	}

	@RequiresPermissions("hm:course:zcourse:edit")
	@RequestMapping(value = "save")
	public String save(Zcourse zcourse, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zcourse)){
			return form(zcourse, model);
		}
		zcourseService.save(zcourse);
		addMessage(redirectAttributes, "保存课程信息管理成功");
		return "redirect:"+Global.getAdminPath()+"/hm/course/zcourse/?repage";
	}
	
	@RequiresPermissions("hm:course:zcourse:edit")
	@RequestMapping(value = "delete")
	public String delete(Zcourse zcourse, RedirectAttributes redirectAttributes) {
		zcourseService.delete(zcourse);
		addMessage(redirectAttributes, "删除课程信息管理成功");
		return "redirect:"+Global.getAdminPath()+"/hm/course/zcourse/?repage";
	}

}