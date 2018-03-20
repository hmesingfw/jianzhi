/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.web.question;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.hm.entity.course_sort.ZcourseSort;
import com.thinkgem.jeesite.modules.hm.entity.question.Zquestion;
import com.thinkgem.jeesite.modules.hm.entity.question_answer.ZquestionAnswer;
import com.thinkgem.jeesite.modules.hm.service.course_sort.ZcourseSortService;
import com.thinkgem.jeesite.modules.hm.service.question.ZquestionService;
import com.thinkgem.jeesite.modules.hm.service.question_answer.ZquestionAnswerService;
import com.thinkgem.jeesite.modules.hm.utils.ZquestionUtils;

/**
 * 试题管理Controller
 * @author hm
 * @version 2018-03-14
 */
@Controller
@RequestMapping(value = "${adminPath}/hm/question/zquestion")
public class ZquestionController extends BaseController {

	@Autowired
	private ZquestionService zquestionService;
	@Autowired
	private ZquestionAnswerService zquestionAnswerService;
//	课程分类管理
	@Autowired
	private ZcourseSortService zcourseSortService;
	
	@ModelAttribute
	public Zquestion get(@RequestParam(required=false) String id) {
		Zquestion entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zquestionService.get(id);
		}
		if (entity == null){
			entity = new Zquestion();
		}
		return entity;
	}
	
	@RequiresPermissions("hm:question:zquestion:view")
	@RequestMapping(value = {"list", ""})
	public String list(Zquestion zquestion, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Zquestion> page = zquestionService.findPage(new Page<Zquestion>(request, response), zquestion); 
		model.addAttribute("page", page);
		
		
		Zquestion sort = new Zquestion();
		sort.setDelFlag("0");
		List<Zquestion>	list = zquestionService.findList(sort);
		CacheUtils.put(ZquestionUtils.CACHE_ZquestionSort_LIST, list);
		
		return "modules/hm/question/zquestionList";
	}

	@RequiresPermissions("hm:question:zquestion:view")
	@RequestMapping(value = "form")
	public String form(Zquestion zquestion, Model model) {
		
		
		if(zquestion.getId()!=null && !"".equals(zquestion.getId())){
			//查看当前答案
			ZquestionAnswer zquestionAnswer = new ZquestionAnswer();
			zquestionAnswer.setQuesId(zquestion.getId());
			zquestionAnswer.setDelFlag("0");
			List<ZquestionAnswer> answerlist = zquestionAnswerService.findList(zquestionAnswer);
			model.addAttribute("answerlist", answerlist);
		}
		model.addAttribute("zquestion", zquestion);
		return "modules/hm/question/zquestionForm";
	}

	@RequiresPermissions("hm:question:zquestion:edit")
	@RequestMapping(value = "save")
	public String save(Zquestion zquestion, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response) {
		if (!beanValidator(model, zquestion)){
			return form(zquestion, model);
		}
		zquestionService.save(zquestion);

		//保存答案内容
		String answers[] = request.getParameterValues("answer");		
		String correts = request.getParameter("correts");
		String corret[] = correts.split(",");
		
		//先删除当前答案
		ZquestionAnswer Answer = new ZquestionAnswer();
		Answer.setQuesId(zquestion.getId());;
		zquestionAnswerService.deleteQuesid(Answer);		
		for(int i=0; i<answers.length; i++){	
			String answer = answers[i];
			//添加当前答案
			if(answer!=null && !"".equals(answer)){
				ZquestionAnswer zquestionAnswer = new ZquestionAnswer();
				zquestionAnswer.setQuesId(zquestion.getId());
				zquestionAnswer.setAnswer(answer);
				zquestionAnswer.setIsCorrect(corret[i]);
				
				zquestionAnswerService.save(zquestionAnswer);
			}
			
		}
		
		addMessage(redirectAttributes, "保存试题管理成功");
		return "redirect:"+Global.getAdminPath()+"/hm/question/zquestion/?repage";
	}
	
	@RequiresPermissions("hm:question:zquestion:edit")
	@RequestMapping(value = "delete")
	public String delete(Zquestion zquestion, RedirectAttributes redirectAttributes) {
		zquestionService.delete(zquestion);
		addMessage(redirectAttributes, "删除试题管理成功");
		return "redirect:"+Global.getAdminPath()+"/hm/question/zquestion/?repage";
	}

	/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("hm:question:zquestion:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/hm/question/zquestion?repage";
		}
		String title = "";	//当前题目名
		String questionid = ""; //当前题目的ID
		try{
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<Zquestion> list = ei.getDataList(Zquestion.class);
			System.out.println(list.size());
			for (int i=0; i<list.size(); i++){
				Zquestion zquestion = list.get(i);
				String answer = zquestion.getAnswer();
				String corret = zquestion.getIsCorrect();
				
				System.out.println(questionid);
				if("".equals(title) || !title.equals(zquestion.getTitle())){		//第一次循环 
					System.out.println(i);
					ZcourseSort sort = new ZcourseSort();
					List<ZcourseSort> sortlist =  zcourseSortService.findName(zquestion.getParentid());
					if(sortlist!=null && sortlist.size()>0){
						sort = sortlist.get(0);
					}
					zquestion.setParentid(sort.getId());
					zquestionService.save(zquestion);
					
					//添加标识
					questionid = zquestion.getId();
					title = zquestion.getTitle();
				}
				
				
				//保存答案
				ZquestionAnswer zquestionAnswer = new ZquestionAnswer();
				zquestionAnswer.setQuesId(questionid);
				zquestionAnswer.setAnswer(answer);
				zquestionAnswer.setIsCorrect(corret);					
				zquestionAnswerService.save(zquestionAnswer);				
				
			}
		}catch(Exception e){
			System.out.println("----->");
			System.out.println(e.getMessage());
			addMessage(redirectAttributes, "导入用户失败！失败信息："+e.getMessage());
		}
			 
		return "redirect:" + adminPath + "/hm/question/zquestion?repage";
    }
}