/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.web.news;

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
import com.thinkgem.jeesite.modules.hm.entity.news.Znew;
import com.thinkgem.jeesite.modules.hm.service.news.ZnewService;

/**
 * 新闻资讯Controller
 * @author hm
 * @version 2018-03-07
 */
@Controller
@RequestMapping(value = "${adminPath}/hm/news/znew")
public class ZnewController extends BaseController {

	@Autowired
	private ZnewService znewService;
	
	@ModelAttribute
	public Znew get(@RequestParam(required=false) String id) {
		Znew entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = znewService.get(id);
		}
		if (entity == null){
			entity = new Znew();
		}
		return entity;
	}
	
	@RequiresPermissions("hm:news:znew:view")
	@RequestMapping(value = {"list", ""})
	public String list(Znew znew, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Znew> page = znewService.findPage(new Page<Znew>(request, response), znew); 
		model.addAttribute("page", page);
		return "modules/hm/news/znewList";
	}

	@RequiresPermissions("hm:news:znew:view")
	@RequestMapping(value = "form")
	public String form(Znew znew, Model model) {
		model.addAttribute("znew", znew);
		return "modules/hm/news/znewForm";
	}

	@RequiresPermissions("hm:news:znew:edit")
	@RequestMapping(value = "save")
	public String save(Znew znew, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, znew)){
			return form(znew, model);
		}
		znewService.save(znew);
		addMessage(redirectAttributes, "保存新闻资讯成功");
		return "redirect:"+Global.getAdminPath()+"/hm/news/znew/?repage";
	}
	
	@RequiresPermissions("hm:news:znew:edit")
	@RequestMapping(value = "delete")
	public String delete(Znew znew, RedirectAttributes redirectAttributes) {
		znewService.delete(znew);
		addMessage(redirectAttributes, "删除新闻资讯成功");
		return "redirect:"+Global.getAdminPath()+"/hm/news/znew/?repage";
	}

}