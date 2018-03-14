/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.web.question_answer;

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
import com.thinkgem.jeesite.modules.hm.entity.question_answer.ZquestionAnswer;
import com.thinkgem.jeesite.modules.hm.service.question_answer.ZquestionAnswerService;

/**
 * 试题答案管理Controller
 * @author hm
 * @version 2018-03-14
 */
@Controller
@RequestMapping(value = "${adminPath}/hm/question_answer/zquestionAnswer")
public class ZquestionAnswerController extends BaseController {

	@Autowired
	private ZquestionAnswerService zquestionAnswerService;
	
	@ModelAttribute
	public ZquestionAnswer get(@RequestParam(required=false) String id) {
		ZquestionAnswer entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zquestionAnswerService.get(id);
		}
		if (entity == null){
			entity = new ZquestionAnswer();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(ZquestionAnswer zquestionAnswer, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZquestionAnswer> page = zquestionAnswerService.findPage(new Page<ZquestionAnswer>(request, response), zquestionAnswer); 
		model.addAttribute("page", page);
		return "modules/hm/question_answer/zquestionAnswerList";
	}

	@RequestMapping(value = "form")
	public String form(ZquestionAnswer zquestionAnswer, Model model) {
		model.addAttribute("zquestionAnswer", zquestionAnswer);
		return "modules/hm/question_answer/zquestionAnswerForm";
	}

	@RequestMapping(value = "save")
	public String save(ZquestionAnswer zquestionAnswer, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zquestionAnswer)){
			return form(zquestionAnswer, model);
		}
		zquestionAnswerService.save(zquestionAnswer);
		addMessage(redirectAttributes, "保存试题答案管理成功");
		return "redirect:"+Global.getAdminPath()+"/hm/question_answer/zquestionAnswer/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(ZquestionAnswer zquestionAnswer, RedirectAttributes redirectAttributes) {
		zquestionAnswerService.delete(zquestionAnswer);
		addMessage(redirectAttributes, "删除试题答案管理成功");
		return "redirect:"+Global.getAdminPath()+"/hm/question_answer/zquestionAnswer/?repage";
	}

}