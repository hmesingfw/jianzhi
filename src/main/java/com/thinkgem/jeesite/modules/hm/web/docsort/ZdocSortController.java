/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.web.docsort;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.hm.dao.doc.ZdocDao;
import com.thinkgem.jeesite.modules.hm.entity.course_sort.ZcourseSort;
import com.thinkgem.jeesite.modules.hm.entity.docsort.ZdocSort;
import com.thinkgem.jeesite.modules.hm.service.docsort.ZdocSortService;
import com.thinkgem.jeesite.modules.hm.utils.ZdocSortUtils;

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
		
		
		ZdocSort sort = new ZdocSort();
		sort.setDelFlag("0");
		List<ZdocSort> listsort = zdocSortService.findList(sort);
		CacheUtils.put(ZdocSortUtils.CACHE_docsort_LIST, listsort);
		
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

	
	
	/**
	 * 获取机构JSON数据。
	 * @param extId 排除的ID
	 * @param type	类型（1：公司；2：部门/小组/其它：3：用户）
	 * @param grade 显示级别
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, @RequestParam(required=false) String type,
			@RequestParam(required=false) Long grade, @RequestParam(required=false) Boolean isAll, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		
		ZdocSort sort = new ZdocSort();
		sort.setDelFlag("0");
		List<ZdocSort> list = zdocSortService.findList(sort);
		
		for (int i=0; i<list.size(); i++){
			ZdocSort e = list.get(i);
			if ((StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId())))){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParent());
				map.put("pIds", e.getParentIds());
				map.put("name", e.getName());				
				mapList.add(map);
			}
		}
		return mapList;
	}
}