/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.web.docsort;

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
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.hm.entity.docsort.ZdocSort;
import com.thinkgem.jeesite.modules.hm.service.docsort.ZdocSortService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 文档分类Controller
 * @author hm
 * @version 2018-03-07
 */
@Controller
@RequestMapping(value = "${adminPath}/hm/docsort/zdocSort")
public class ZdocSortController extends BaseController {

	@Autowired
	private ZdocSortService zdocSortService;
	
	@ModelAttribute
	public ZdocSort get(@RequestParam(required=false) String id) {
		ZdocSort entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zdocSortService.get(id);
		}
		if (entity == null){
			entity = new ZdocSort();
		}
		return entity;
	}
	
	@RequiresPermissions("hm:docsort:zdocSort:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZdocSort zdocSort, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<ZdocSort> list = new ArrayList<ZdocSort>();
		List<ZdocSort> sortlist = zdocSortService.findList(zdocSort);
		ZdocSort.sortList(list, sortlist, "0", true);
		
		model.addAttribute("list", list);		
		return "modules/hm/docsort/zdocSortList";
	}

	@RequiresPermissions("hm:docsort:zdocSort:view")
	@RequestMapping(value = "form")
	public String form(ZdocSort zdocSort, Model model) {
		model.addAttribute("zdocSort", zdocSort);
		return "modules/hm/docsort/zdocSortForm";
	}

	@RequiresPermissions("hm:docsort:zdocSort:edit")
	@RequestMapping(value = "save")
	public String save(ZdocSort zdocSort, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zdocSort)){
			return form(zdocSort, model);
		}
		
		zdocSortService.save(zdocSort);
		addMessage(redirectAttributes, "保存文档分类成功");
		return "redirect:"+Global.getAdminPath()+"/hm/docsort/zdocSort/?repage";
	}
	
	@RequiresPermissions("hm:docsort:zdocSort:edit")
	@RequestMapping(value = "delete")
	public String delete(ZdocSort zdocSort, RedirectAttributes redirectAttributes) {
		zdocSortService.delete(zdocSort);
		addMessage(redirectAttributes, "删除文档分类成功");
		return "redirect:"+Global.getAdminPath()+"/hm/docsort/zdocSort/?repage";
	}

}