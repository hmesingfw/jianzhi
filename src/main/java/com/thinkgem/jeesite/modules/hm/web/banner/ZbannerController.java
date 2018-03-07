/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.web.banner;

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
import com.thinkgem.jeesite.modules.hm.entity.banner.Zbanner;
import com.thinkgem.jeesite.modules.hm.service.banner.ZbannerService;

/**
 * 轮播图Controller
 * @author hm
 * @version 2018-03-07
 */
@Controller
@RequestMapping(value = "${adminPath}/hm/banner/zbanner")
public class ZbannerController extends BaseController {

	@Autowired
	private ZbannerService zbannerService;
	
	@ModelAttribute
	public Zbanner get(@RequestParam(required=false) String id) {
		Zbanner entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zbannerService.get(id);
		}
		if (entity == null){
			entity = new Zbanner();
		}
		return entity;
	}
	
	@RequiresPermissions("hm:banner:zbanner:view")
	@RequestMapping(value = {"list", ""})
	public String list(Zbanner zbanner, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Zbanner> page = zbannerService.findPage(new Page<Zbanner>(request, response), zbanner); 
		model.addAttribute("page", page);
		return "modules/hm/banner/zbannerList";
	}

	@RequiresPermissions("hm:banner:zbanner:view")
	@RequestMapping(value = "form")
	public String form(Zbanner zbanner, Model model) {
		model.addAttribute("zbanner", zbanner);
		return "modules/hm/banner/zbannerForm";
	}

	@RequiresPermissions("hm:banner:zbanner:edit")
	@RequestMapping(value = "save")
	public String save(Zbanner zbanner, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zbanner)){
			return form(zbanner, model);
		}
		zbannerService.save(zbanner);
		zbannerService.flashBanner();
		addMessage(redirectAttributes, "保存轮播图成功");
		return "redirect:"+Global.getAdminPath()+"/hm/banner/zbanner/?repage";
	}
	
	@RequiresPermissions("hm:banner:zbanner:edit")
	@RequestMapping(value = "delete")
	public String delete(Zbanner zbanner, RedirectAttributes redirectAttributes) {
		zbannerService.delete(zbanner);
		addMessage(redirectAttributes, "删除轮播图成功");
		return "redirect:"+Global.getAdminPath()+"/hm/banner/zbanner/?repage";
	}

}