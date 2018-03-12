/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.web.order;

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
import com.thinkgem.jeesite.modules.hm.entity.order.ZcourseOrder;
import com.thinkgem.jeesite.modules.hm.service.order.ZcourseOrderService;

/**
 * 用户课程订单Controller
 * @author hm
 * @version 2018-03-12
 */
@Controller
@RequestMapping(value = "${adminPath}/hm/order/zcourseOrder")
public class ZcourseOrderController extends BaseController {

	@Autowired
	private ZcourseOrderService zcourseOrderService;
	
	@ModelAttribute
	public ZcourseOrder get(@RequestParam(required=false) String id) {
		ZcourseOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zcourseOrderService.get(id);
		}
		if (entity == null){
			entity = new ZcourseOrder();
		}
		return entity;
	}
	
	@RequiresPermissions("hm:order:zcourseOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZcourseOrder zcourseOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZcourseOrder> page = zcourseOrderService.findPage(new Page<ZcourseOrder>(request, response), zcourseOrder); 
		model.addAttribute("page", page);
		return "modules/hm/order/zcourseOrderList";
	}

	@RequiresPermissions("hm:order:zcourseOrder:view")
	@RequestMapping(value = "form")
	public String form(ZcourseOrder zcourseOrder, Model model) {
		model.addAttribute("zcourseOrder", zcourseOrder);
		return "modules/hm/order/zcourseOrderForm";
	}

	@RequiresPermissions("hm:order:zcourseOrder:edit")
	@RequestMapping(value = "save")
	public String save(ZcourseOrder zcourseOrder, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zcourseOrder)){
			return form(zcourseOrder, model);
		}
		zcourseOrderService.save(zcourseOrder);
		addMessage(redirectAttributes, "保存用户课程订单成功");
		return "redirect:"+Global.getAdminPath()+"/hm/order/zcourseOrder/?repage";
	}
	
	@RequiresPermissions("hm:order:zcourseOrder:edit")
	@RequestMapping(value = "delete")
	public String delete(ZcourseOrder zcourseOrder, RedirectAttributes redirectAttributes) {
		zcourseOrderService.delete(zcourseOrder);
		addMessage(redirectAttributes, "删除用户课程订单成功");
		return "redirect:"+Global.getAdminPath()+"/hm/order/zcourseOrder/?repage";
	}

}