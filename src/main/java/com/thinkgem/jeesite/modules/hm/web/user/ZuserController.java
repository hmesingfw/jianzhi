/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.web.user;

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
import com.thinkgem.jeesite.modules.hm.entity.user.Zuser;
import com.thinkgem.jeesite.modules.hm.service.user.ZuserService;

/**
 * 用户信息Controller
 * @author hm
 * @version 2018-03-09
 */
@Controller
@RequestMapping(value = "${adminPath}/hm/user/zuser")
public class ZuserController extends BaseController {

	@Autowired
	private ZuserService zuserService;
	
	@ModelAttribute
	public Zuser get(@RequestParam(required=false) String id) {
		Zuser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zuserService.get(id);
		}
		if (entity == null){
			entity = new Zuser();
		}
		return entity;
	}
	
	@RequiresPermissions("hm:user:zuser:view")
	@RequestMapping(value = {"list", ""})
	public String list(Zuser zuser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Zuser> page = zuserService.findPage(new Page<Zuser>(request, response), zuser); 
		model.addAttribute("page", page);
		return "modules/hm/user/zuserList";
	}

	@RequiresPermissions("hm:user:zuser:view")
	@RequestMapping(value = "form")
	public String form(Zuser zuser, Model model) {
		model.addAttribute("zuser", zuser);
		return "modules/hm/user/zuserForm";
	}

	@RequiresPermissions("hm:user:zuser:edit")
	@RequestMapping(value = "save")
	public String save(Zuser zuser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zuser)){
			return form(zuser, model);
		}
		zuserService.save(zuser);
		addMessage(redirectAttributes, "保存用户信息成功");
		return "redirect:"+Global.getAdminPath()+"/hm/user/zuser/?repage";
	}
	
	@RequiresPermissions("hm:user:zuser:edit")
	@RequestMapping(value = "delete")
	public String delete(Zuser zuser, RedirectAttributes redirectAttributes) {
		zuserService.delete(zuser);
		addMessage(redirectAttributes, "删除用户信息成功");
		return "redirect:"+Global.getAdminPath()+"/hm/user/zuser/?repage";
	}

}