/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.web.user;

import java.util.Date;
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
import com.thinkgem.jeesite.modules.hm.entity.doc.Zdoc;
import com.thinkgem.jeesite.modules.hm.entity.order.ZcourseOrder;
import com.thinkgem.jeesite.modules.hm.entity.user.Zuser;
import com.thinkgem.jeesite.modules.hm.service.course_sort.ZcourseSortService;
import com.thinkgem.jeesite.modules.hm.service.order.ZcourseOrderService;
import com.thinkgem.jeesite.modules.hm.service.user.ZuserService;
import com.thinkgem.jeesite.modules.hm.utils.ZuserUtils;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.service.DictService;

/**
 * 用户信息Controller
 * @author hm
 * @version 2018-03-09
 */
@Controller
@RequestMapping(value = "${adminPath}/hm/user/zuser")
public class ZuserController extends BaseController {

	@Autowired
	private ZuserService zuserService;
	@Autowired
	private DictService dictService;  
	@Autowired
	private ZcourseSortService zcourseSortService;  
	@Autowired
	private ZcourseOrderService zcourseOrderService;  
	
	@ModelAttribute
	public Zuser get(@RequestParam(required=false) String id) {
		Zuser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zuserService.get(id);
		}
		if (entity == null){
			entity = new Zuser();
		}
		return entity;
	}
	
	@RequiresPermissions("hm:user:zuser:view")
	@RequestMapping(value = {"list", ""})
	public String list(Zuser zuser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Zuser> page = zuserService.findPage(new Page<Zuser>(request, response), zuser); 
		model.addAttribute("page", page);
		
		zuser = new Zuser();
		zuser.setDelFlag("0");
		List<Zuser> userlist = zuserService.findList(zuser);
		CacheUtils.put(ZuserUtils.CACHE_Zuser_LIST, userlist);
		
		return "modules/hm/user/zuserList";
	}

	@RequiresPermissions("hm:user:zuser:view")
	@RequestMapping(value = "form")
	public String form(Zuser zuser, Model model) {
		model.addAttribute("zuser", zuser);
		return "modules/hm/user/zuserForm";
	}

	@RequiresPermissions("hm:user:zuser:edit")
	@RequestMapping(value = "save")
	public String save(Zuser zuser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zuser)){
			return form(zuser, model);
		}
		zuserService.save(zuser);
		ZcourseSort sort = zcourseSortService.get(zuser.getMajor());
		
		
		//为当时用户加上专业
		ZcourseOrder order = new ZcourseOrder();
		order.setUserid(zuser.getId());
		order.setPaystatus("4");
		
		//取消以前分配的内部专业
		List<ZcourseOrder> status4list = zcourseOrderService.findList(order);
		for (ZcourseOrder zcourseOrder : status4list) {
			zcourseOrder.setPaystatus("1");
			zcourseOrderService.save(zcourseOrder);
		}
		
		order.setCourseid(zuser.getMajor());
		order.setPaytime(new Date());
		order.setExptime(sort.getValidity());
		
		List<ZcourseOrder> orderlist = zcourseOrderService.findMyorderByid(order);
		if (orderlist != null && orderlist.size() > 0) {
			ZcourseOrder zorder = orderlist.get(0);
			order.setId(zorder.getId());
			zcourseOrderService.save(order);
		} else {
			zcourseOrderService.save(order);
		}
		
		
		
		addMessage(redirectAttributes, "保存用户信息成功");
		return "redirect:"+Global.getAdminPath()+"/hm/user/zuser/?repage";
	}
	
	@RequiresPermissions("hm:user:zuser:edit")
	@RequestMapping(value = "delete")
	public String delete(Zuser zuser, RedirectAttributes redirectAttributes) {
		zuserService.delete(zuser);
		addMessage(redirectAttributes, "删除用户信息成功");
		return "redirect:"+Global.getAdminPath()+"/hm/user/zuser/?repage";
	}
	
	// 批量删除
	@RequiresPermissions("hm:question:zquestion:edit")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String deleteValue, RedirectAttributes redirectAttributes) {

		if (StringUtils.isNotBlank(deleteValue)) {
			String[] vals = deleteValue.trim().split("~");
			for (String string : vals) {

				if (StringUtils.isNotBlank(string)) {
					Zuser zquestion = zuserService.get(string);
					zuserService.delete(zquestion);
				}
			}
		}

		addMessage(redirectAttributes, "删除试题管理成功");
		return "redirect:" + Global.getAdminPath() + "/hm/user/zuser/?repage";
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
		 
		try{
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<Zuser> list = ei.getDataList(Zuser.class);
			System.out.println(list.size());
			for (int i=0; i<list.size(); i++){
				System.out.println(list.size()+"------------------------>");
				Zuser zuser = list.get(i);
				if(StringUtils.isNoneBlank(zuser.getIdcode())){
					
					 
					String phone = zuser.getPhone().replaceAll("-", "");  
					zuser.setPhone(phone);
					//字黄类型
					Dict dict = new Dict();
					dict.setType("user_ethnic");
					dict.setLabel(zuser.getEthnic());
					List<Dict> dictlist = dictService.findLabel(dict);
					if(dictlist!=null && dictlist.size()>0){
						zuser.setEthnic(dictlist.get(0).getValue());
					}
					zuserService.save(zuser);
				}
				
				
			}
		}catch(Exception e){
			System.out.println("----->");
			System.out.println(e.getMessage());
			addMessage(redirectAttributes, "导入用户失败！失败信息："+e.getMessage());
		}
			 
		return "redirect:" + adminPath + "/hm/user/zuser?repage";
    }

}