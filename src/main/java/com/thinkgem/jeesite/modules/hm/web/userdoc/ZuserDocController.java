/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.web.userdoc;

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
import com.thinkgem.jeesite.modules.hm.entity.userdoc.ZuserDoc;
import com.thinkgem.jeesite.modules.hm.service.userdoc.ZuserDocService;

/**
 * 用户文档下载记录Controller
 * @author hm
 * @version 2018-03-10
 */
@Controller
@RequestMapping(value = "${adminPath}/hm/userdoc/zuserDoc")
public class ZuserDocController extends BaseController {

	@Autowired
	private ZuserDocService zuserDocService;
	
	@ModelAttribute
	public ZuserDoc get(@RequestParam(required=false) String id) {
		ZuserDoc entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zuserDocService.get(id);
		}
		if (entity == null){
			entity = new ZuserDoc();
		}
		return entity;
	}
	
	@RequiresPermissions("hm:userdoc:zuserDoc:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZuserDoc zuserDoc, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZuserDoc> page = zuserDocService.findPage(new Page<ZuserDoc>(request, response), zuserDoc); 
		model.addAttribute("page", page);
		return "modules/hm/userdoc/zuserDocList";
	}

	@RequiresPermissions("hm:userdoc:zuserDoc:view")
	@RequestMapping(value = "form")
	public String form(ZuserDoc zuserDoc, Model model) {
		model.addAttribute("zuserDoc", zuserDoc);
		return "modules/hm/userdoc/zuserDocForm";
	}

	@RequiresPermissions("hm:userdoc:zuserDoc:edit")
	@RequestMapping(value = "save")
	public String save(ZuserDoc zuserDoc, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zuserDoc)){
			return form(zuserDoc, model);
		}
		zuserDocService.save(zuserDoc);
		addMessage(redirectAttributes, "保存用户文档下载记录成功");
		return "redirect:"+Global.getAdminPath()+"/hm/userdoc/zuserDoc/?repage";
	}
	
	@RequiresPermissions("hm:userdoc:zuserDoc:edit")
	@RequestMapping(value = "delete")
	public String delete(ZuserDoc zuserDoc, RedirectAttributes redirectAttributes) {
		zuserDocService.delete(zuserDoc);
		addMessage(redirectAttributes, "删除用户文档下载记录成功");
		return "redirect:"+Global.getAdminPath()+"/hm/userdoc/zuserDoc/?repage";
	}

}