/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.web.test;

import java.util.ArrayList;
import java.util.List;

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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.hm.entity.question.Zquestion;
import com.thinkgem.jeesite.modules.hm.entity.test.Ztest;
import com.thinkgem.jeesite.modules.hm.entity.test_question.ZtestQuestion;
import com.thinkgem.jeesite.modules.hm.service.question.ZquestionService;
import com.thinkgem.jeesite.modules.hm.service.test.ZtestService;
import com.thinkgem.jeesite.modules.hm.service.test_question.ZtestQuestionService;

/**
 * 试卷管理Controller
 * @author hm
 * @version 2018-03-14
 */
@Controller
@RequestMapping(value = "${adminPath}/hm/test/ztest")
public class ZtestController extends BaseController {

	@Autowired
	private ZtestService ztestService;
	@Autowired
	private ZtestQuestionService ztestQuestionService;
	@Autowired
	private ZquestionService zquestionService;
	
	@ModelAttribute
	public Ztest get(@RequestParam(required=false) String id) {
		Ztest entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ztestService.get(id);
		}
		if (entity == null){
			entity = new Ztest();
		}
		return entity;
	}
	
	@RequiresPermissions("hm:test:ztest:view")
	@RequestMapping(value = {"list", ""})
	public String list(Ztest ztest, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Ztest> page = ztestService.findPage(new Page<Ztest>(request, response), ztest); 
		model.addAttribute("page", page);
		return "modules/hm/test/ztestList";
	}

	@RequiresPermissions("hm:test:ztest:view")
	@RequestMapping(value = "form")
	public String form(Ztest ztest, Model model) {
		model.addAttribute("ztest", ztest);
		return "modules/hm/test/ztestForm";
	}

	@RequiresPermissions("hm:test:ztest:edit")
	@RequestMapping(value = "save")
	public String save(Ztest ztest, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ztest)){
			return form(ztest, model);
		}
		 
		System.out.println(ztest.getTesttype());
		ztestService.save(ztest);
		
		//自动组卷
		if("1".equals(ztest.getType())){
			
			int limit = StringUtils.isNumeric(ztest.getSum()) ? Integer.parseInt(ztest.getSum()) : 1;
			Zquestion ztion = new Zquestion();
			List<String> sortlist = new ArrayList<>();
			if(ztest.getTesttype()!=null && !"".equals(ztest.getTesttype())){
				String[] types = ztest.getTesttype().split(",");
				for (String string : types) {
					if(StringUtils.isNotBlank(string)){
						sortlist.add(string);
					}
				}
				ztion.setSortlist(sortlist);				
			}			
			ztion.setLimit(limit);
			ztion.setDelFlag("0");
			List<Zquestion> questionlist = zquestionService.findRandList(ztion);		//获取随机题目列表
			
			if(questionlist!=null && questionlist.size()>0){
				int frac = 0;	//总分数				
				ZtestQuestion question = new ZtestQuestion();
				question.setTestid(ztest.getId());	
				for (int i = 0; i < questionlist.size(); i++) {
					Zquestion zques = questionlist.get(i);
					//添加试题
					question = new ZtestQuestion();
					question.setTestid(ztest.getId());	
					question.setQuestion(zques.getId());
					question.setFraction(zques.getDefaultFraction());
					ztestQuestionService.save(question);
					
					if(StringUtils.isNumeric(zques.getDefaultFraction())){
						frac += Integer.parseInt(zques.getDefaultFraction());
					}
				}
				
				//更新总分数			
				ztest.setFraction(frac+"");
				ztestService.save(ztest);
			}
		}
	
		addMessage(redirectAttributes, "保存试卷成功");
		return "redirect:"+Global.getAdminPath()+"/hm/test/ztest/?repage";
	}
	
	@RequiresPermissions("hm:test:ztest:edit")
	@RequestMapping(value = "delete")
	public String delete(Ztest ztest, RedirectAttributes redirectAttributes) {
		ztestService.delete(ztest);
		addMessage(redirectAttributes, "删除试卷成功");
		return "redirect:"+Global.getAdminPath()+"/hm/test/ztest/?repage";
	}

	
	
	@RequiresPermissions("hm:test:ztest:edit")
	@RequestMapping(value = "questionlist")
	public String questionlist(String id, RedirectAttributes redirectAttributes, Model model) {
		ZtestQuestion ztestQuestion = new ZtestQuestion();
		ztestQuestion.setTestid(id);
		ztestQuestion.setDelFlag("0");
		List<ZtestQuestion> questionlist = ztestQuestionService.findList(ztestQuestion);
		model.addAttribute("questlist", questionlist);
		model.addAttribute("testid", id);
		return "modules/hm/test/questionlist";
	}

	@RequiresPermissions("hm:test:ztest:edit")
	@RequestMapping(value = "questlistsave")
	public String questlistsave(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes, Model model) {
		String ids[] = request.getParameterValues("id");	
		
		String fractions[] = request.getParameterValues("fractions");
		String testid = request.getParameter("testid");
		 
		int frac = 0;	//总分数
		
		ZtestQuestion question = new ZtestQuestion();
		question.setTestid(testid);	
		ztestQuestionService.deleteTestid(question);	//删除试题卷中的题目
		if(ids!=null && ids.length>0){
			for (int i = 0; i < ids.length; i++) {
				
				question = new ZtestQuestion();
				question.setTestid(testid);	
				question.setQuestion(ids[i]);
				question.setFraction(fractions[i]);
				ztestQuestionService.save(question);
				
				if(StringUtils.isNumeric(fractions[i])){
					frac += Integer.parseInt(fractions[i]);
				}
			}
		}			
		
		//更新总分数
		Ztest test = ztestService.get(testid);
		test.setFraction(frac+"");
		ztestService.save(test);
		
		addMessage(redirectAttributes, "保存题目成功");
		return "redirect:"+Global.getAdminPath()+"/hm/test/ztest/?repage";
	}
	
	
}