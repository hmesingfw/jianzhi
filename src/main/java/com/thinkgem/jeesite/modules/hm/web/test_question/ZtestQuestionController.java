/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.web.test_question;

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
import com.thinkgem.jeesite.modules.hm.entity.test_question.ZtestQuestion;
import com.thinkgem.jeesite.modules.hm.service.test_question.ZtestQuestionService;

/**
 * 试卷题目内容管理Controller
 * @author hm
 * @version 2018-03-14
 */
@Controller
@RequestMapping(value = "${adminPath}/hm/test_question/ztestQuestion")
public class ZtestQuestionController extends BaseController {

	@Autowired
	private ZtestQuestionService ztestQuestionService;
	
	@ModelAttribute
	public ZtestQuestion get(@RequestParam(required=false) String id) {
		ZtestQuestion entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ztestQuestionService.get(id);
		}
		if (entity == null){
			entity = new ZtestQuestion();
		}
		return entity;
	}
	
	@RequiresPermissions("hm:test_question:ztestQuestion:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZtestQuestion ztestQuestion, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZtestQuestion> page = ztestQuestionService.findPage(new Page<ZtestQuestion>(request, response), ztestQuestion); 
		model.addAttribute("page", page);
		return "modules/hm/test_question/ztestQuestionList";
	}

	@RequiresPermissions("hm:test_question:ztestQuestion:view")
	@RequestMapping(value = "form")
	public String form(ZtestQuestion ztestQuestion, Model model) {
		model.addAttribute("ztestQuestion", ztestQuestion);
		return "modules/hm/test_question/ztestQuestionForm";
	}

	@RequiresPermissions("hm:test_question:ztestQuestion:edit")
	@RequestMapping(value = "save")
	public String save(ZtestQuestion ztestQuestion, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ztestQuestion)){
			return form(ztestQuestion, model);
		}
		ztestQuestionService.save(ztestQuestion);
		addMessage(redirectAttributes, "保存试卷题目内容管理成功");
		return "redirect:"+Global.getAdminPath()+"/hm/test_question/ztestQuestion/?repage";
	}
	
	@RequiresPermissions("hm:test_question:ztestQuestion:edit")
	@RequestMapping(value = "delete")
	public String delete(ZtestQuestion ztestQuestion, RedirectAttributes redirectAttributes) {
		ztestQuestionService.delete(ztestQuestion);
		addMessage(redirectAttributes, "删除试卷题目内容管理成功");
		return "redirect:"+Global.getAdminPath()+"/hm/test_question/ztestQuestion/?repage";
	}

}