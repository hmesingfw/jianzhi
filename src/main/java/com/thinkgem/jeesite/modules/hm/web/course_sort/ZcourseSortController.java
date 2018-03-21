/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.web.course_sort;

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
import com.thinkgem.jeesite.modules.hm.entity.course_sort.ZcourseSort;
import com.thinkgem.jeesite.modules.hm.service.course_sort.ZcourseSortService;
import com.thinkgem.jeesite.modules.hm.utils.ZcourseSortUtils;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 专业分类Controller
 * @author hm
 * @version 2018-03-09
 */
@Controller
@RequestMapping(value = "${adminPath}/hm/course_sort/zcourseSort")
public class ZcourseSortController extends BaseController {

	@Autowired
	private ZcourseSortService zcourseSortService;
	
	@ModelAttribute
	public ZcourseSort get(@RequestParam(required=false) String id) {
		ZcourseSort entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zcourseSortService.get(id);
		}
		if (entity == null){
			entity = new ZcourseSort();
		}
		return entity;
	}
	
	@RequiresPermissions("hm:course_sort:zcourseSort:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZcourseSort zcourseSort, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		List<ZcourseSort> list = new ArrayList<ZcourseSort>();
		List<ZcourseSort> sortlist = zcourseSortService.findList(zcourseSort);
		ZcourseSort.sortList(list, sortlist, "0", true);
		
		model.addAttribute("list", list);
		
		
		ZcourseSort sort = new ZcourseSort();
		sort.setDelFlag("0");
		List<ZcourseSort> courselist = zcourseSortService.findList(sort);
		CacheUtils.put(ZcourseSortUtils.CACHE_zcourseSort_LIST, courselist);
		
		return "modules/hm/course_sort/zcourseSortList";
	}

	@RequiresPermissions("hm:course_sort:zcourseSort:view")
	@RequestMapping(value = "form")
	public String form(ZcourseSort zcourseSort, Model model) {
		model.addAttribute("zcourseSort", zcourseSort);
		return "modules/hm/course_sort/zcourseSortForm";
	}

	@RequiresPermissions("hm:course_sort:zcourseSort:edit")
	@RequestMapping(value = "save")
	public String save(ZcourseSort zcourseSort, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zcourseSort)){
			return form(zcourseSort, model);
		}
		zcourseSortService.save(zcourseSort);
		addMessage(redirectAttributes, "保存专业分类成功");
		return "redirect:"+Global.getAdminPath()+"/hm/course_sort/zcourseSort/?repage";
	}
	
	@RequiresPermissions("hm:course_sort:zcourseSort:edit")
	@RequestMapping(value = "delete")
	public String delete(ZcourseSort zcourseSort, RedirectAttributes redirectAttributes) {
		zcourseSortService.delete(zcourseSort);
		addMessage(redirectAttributes, "删除专业分类成功");
		return "redirect:"+Global.getAdminPath()+"/hm/course_sort/zcourseSort/?repage";
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
		
		ZcourseSort sort = new ZcourseSort();
		sort.setDelFlag("0");
		List<ZcourseSort> list = zcourseSortService.findList(sort);
		
		for (int i=0; i<list.size(); i++){
			ZcourseSort e = list.get(i);
			if ((StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId())))){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("pIds", e.getParentIds());
				map.put("name", e.getName());				
				mapList.add(map);
			}
		}
		return mapList;
	}

	
}