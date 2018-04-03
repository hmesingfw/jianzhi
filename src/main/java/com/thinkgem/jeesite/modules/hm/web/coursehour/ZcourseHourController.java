/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.web.coursehour;

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
import com.thinkgem.jeesite.modules.hm.entity.coursehour.ZcourseHour;
import com.thinkgem.jeesite.modules.hm.service.coursehour.ZcourseHourService;

/**
 * 课时管理Controller
 * @author hm
 * @version 2018-04-03
 */
@Controller
@RequestMapping(value = "${adminPath}/hm/coursehour/zcourseHour")
public class ZcourseHourController extends BaseController {

	@Autowired
	private ZcourseHourService zcourseHourService;
	
	@ModelAttribute
	public ZcourseHour get(@RequestParam(required=false) String id) {
		ZcourseHour entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zcourseHourService.get(id);
		}
		if (entity == null){
			entity = new ZcourseHour();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(ZcourseHour zcourseHour, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZcourseHour> page = zcourseHourService.findPage(new Page<ZcourseHour>(request, response), zcourseHour); 
		model.addAttribute("page", page);
		
		model.addAttribute("courseid", zcourseHour.getCourseid());
		return "modules/hm/coursehour/zcourseHourList";
	}

	@RequestMapping(value = "form")
	public String form(ZcourseHour zcourseHour, Model model) {
		model.addAttribute("zcourseHour", zcourseHour);
		model.addAttribute("courseid", zcourseHour.getCourseid());
		return "modules/hm/coursehour/zcourseHourForm";
	}

	@RequestMapping(value = "save")
	public String save(ZcourseHour zcourseHour, Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("courseid", zcourseHour.getCourseid());

		if (!beanValidator(model, zcourseHour)){
			return form(zcourseHour, model);
		}
		zcourseHourService.save(zcourseHour);
		addMessage(redirectAttributes, "保存课时管理成功");
		return "redirect:"+Global.getAdminPath()+"/hm/coursehour/zcourseHour/list?courseid="+zcourseHour.getCourseid();
	}
	
	@RequestMapping(value = "delete")
	public String delete(ZcourseHour zcourseHour, RedirectAttributes redirectAttributes) {
		zcourseHourService.delete(zcourseHour);
		addMessage(redirectAttributes, "删除课时管理成功");
		return "redirect:"+Global.getAdminPath()+"/hm/coursehour/zcourseHour/list?courseid="+zcourseHour.getCourseid();
	}

}