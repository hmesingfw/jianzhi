/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.web.aboutus;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.hm.entity.aboutus.TaboutUs;
import com.thinkgem.jeesite.modules.hm.service.aboutus.TaboutUsService;

/**
 * 关于我们信息Controller
 * @author hm
 * @version 2018-03-05
 */
@Controller
@RequestMapping(value = "${adminPath}/hm/aboutus/taboutUs")
public class TaboutUsController extends BaseController {

	@Autowired
	private TaboutUsService taboutUsService;
	
	@ModelAttribute
	public TaboutUs get(@RequestParam(required=false) String id) {
		TaboutUs entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = taboutUsService.get(id);
		}
		if (entity == null){
			entity = new TaboutUs();
		}
		return entity;
	}
	
	@RequiresPermissions("hm:aboutus:taboutUs:view")
	@RequestMapping(value = {"list", ""})
	public String list(TaboutUs taboutUs, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TaboutUs> page = taboutUsService.findPage(new Page<TaboutUs>(request, response), taboutUs); 
		model.addAttribute("page", page);
		return "modules/hm/aboutus/taboutUsList";
	}

	@RequiresPermissions("hm:aboutus:taboutUs:view")
	@RequestMapping(value = "form")
	public String form(TaboutUs taboutUs, Model model) {
		model.addAttribute("taboutUs", taboutUs);
		return "modules/hm/aboutus/taboutUsForm";
	}

	@RequiresPermissions("hm:aboutus:taboutUs:edit")
	@RequestMapping(value = "save")
	public String save(TaboutUs taboutUs, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, taboutUs)){
			return form(taboutUs, model);
		}
		taboutUsService.save(taboutUs);
		taboutUsService.flashAboutConfig();
		addMessage(redirectAttributes, "保存关于我们信息成功");
		return "redirect:"+Global.getAdminPath()+"/hm/aboutus/taboutUs/?repage";
	}
	
	@RequiresPermissions("hm:aboutus:taboutUs:edit")
	@RequestMapping(value = "delete")
	public String delete(TaboutUs taboutUs, RedirectAttributes redirectAttributes) {
		taboutUsService.delete(taboutUs);
		addMessage(redirectAttributes, "删除关于我们信息成功");
		return "redirect:"+Global.getAdminPath()+"/hm/aboutus/taboutUs/?repage";
	}


	
	
}