/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.web.usertest;

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
import com.thinkgem.jeesite.modules.hm.entity.usertest.ZuserTest;
import com.thinkgem.jeesite.modules.hm.service.usertest.ZuserTestService;

/**
 * 用户答题记录Controller
 * @author hm
 * @version 2018-03-15
 */
@Controller
@RequestMapping(value = "${adminPath}/hm/usertest/zuserTest")
public class ZuserTestController extends BaseController {

	@Autowired
	private ZuserTestService zuserTestService;
	
	@ModelAttribute
	public ZuserTest get(@RequestParam(required=false) String id) {
		ZuserTest entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zuserTestService.get(id);
		}
		if (entity == null){
			entity = new ZuserTest();
		}
		return entity;
	}
	
	@RequiresPermissions("hm:usertest:zuserTest:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZuserTest zuserTest, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZuserTest> page = zuserTestService.findPage(new Page<ZuserTest>(request, response), zuserTest); 
		model.addAttribute("page", page);
		return "modules/hm/usertest/zuserTestList";
	}

	@RequiresPermissions("hm:usertest:zuserTest:view")
	@RequestMapping(value = "form")
	public String form(ZuserTest zuserTest, Model model) {
		model.addAttribute("zuserTest", zuserTest);
		return "modules/hm/usertest/zuserTestForm";
	}

	@RequiresPermissions("hm:usertest:zuserTest:edit")
	@RequestMapping(value = "save")
	public String save(ZuserTest zuserTest, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zuserTest)){
			return form(zuserTest, model);
		}
		zuserTestService.save(zuserTest);
		addMessage(redirectAttributes, "保存用户答题记录成功");
		return "redirect:"+Global.getAdminPath()+"/hm/usertest/zuserTest/?repage";
	}
	
	@RequiresPermissions("hm:usertest:zuserTest:edit")
	@RequestMapping(value = "delete")
	public String delete(ZuserTest zuserTest, RedirectAttributes redirectAttributes) {
		zuserTestService.delete(zuserTest);
		addMessage(redirectAttributes, "删除用户答题记录成功");
		return "redirect:"+Global.getAdminPath()+"/hm/usertest/zuserTest/?repage";
	}

}