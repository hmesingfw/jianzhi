/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.web.course_user;

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
import com.thinkgem.jeesite.modules.hm.entity.course_user.ZcourseUser;
import com.thinkgem.jeesite.modules.hm.service.course_user.ZcourseUserService;

/**
 * 用户课程学习记录Controller
 * @author hm
 * @version 2018-03-16
 */
@Controller
@RequestMapping(value = "${adminPath}/hm/course_user/zcourseUser")
public class ZcourseUserController extends BaseController {

	@Autowired
	private ZcourseUserService zcourseUserService;
	
	@ModelAttribute
	public ZcourseUser get(@RequestParam(required=false) String id) {
		ZcourseUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zcourseUserService.get(id);
		}
		if (entity == null){
			entity = new ZcourseUser();
		}
		return entity;
	}
	
	@RequiresPermissions("hm:course_user:zcourseUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZcourseUser zcourseUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZcourseUser> page = zcourseUserService.findPage(new Page<ZcourseUser>(request, response), zcourseUser); 
		model.addAttribute("page", page);
		return "modules/hm/course_user/zcourseUserList";
	}

	@RequiresPermissions("hm:course_user:zcourseUser:view")
	@RequestMapping(value = "form")
	public String form(ZcourseUser zcourseUser, Model model) {
		model.addAttribute("zcourseUser", zcourseUser);
		return "modules/hm/course_user/zcourseUserForm";
	}

	@RequiresPermissions("hm:course_user:zcourseUser:edit")
	@RequestMapping(value = "save")
	public String save(ZcourseUser zcourseUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zcourseUser)){
			return form(zcourseUser, model);
		}
		zcourseUserService.save(zcourseUser);
		addMessage(redirectAttributes, "保存用户课程学习记录成功");
		return "redirect:"+Global.getAdminPath()+"/hm/course_user/zcourseUser/?repage";
	}
	
	@RequiresPermissions("hm:course_user:zcourseUser:edit")
	@RequestMapping(value = "delete")
	public String delete(ZcourseUser zcourseUser, RedirectAttributes redirectAttributes) {
		zcourseUserService.delete(zcourseUser);
		addMessage(redirectAttributes, "删除用户课程学习记录成功");
		return "redirect:"+Global.getAdminPath()+"/hm/course_user/zcourseUser/?repage";
	}

}