/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.web.doc;

import java.io.File;
import java.io.UnsupportedEncodingException;

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
import com.thinkgem.jeesite.modules.hm.entity.doc.Zdoc;
import com.thinkgem.jeesite.modules.hm.service.doc.ZdocService;
import com.thinkgem.jeesite.modules.hm.utils.offeic.filetools.DocChangePdf;

/**
 * 文档中心Controller
 * @author hm
 * @version 2018-03-08
 */
@Controller
@RequestMapping(value = "${adminPath}/hm/doc/zdoc")
public class ZdocController extends BaseController {

	@Autowired
	private ZdocService zdocService;
	
	@ModelAttribute
	public Zdoc get(@RequestParam(required=false) String id) {
		Zdoc entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zdocService.get(id);
		}
		if (entity == null){
			entity = new Zdoc();
		}
		return entity;
	}
	
	@RequiresPermissions("hm:doc:zdoc:view")
	@RequestMapping(value = {"list", ""})
	public String list(Zdoc zdoc, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Zdoc> page = zdocService.findPage(new Page<Zdoc>(request, response), zdoc); 
		model.addAttribute("page", page);
		return "modules/hm/doc/zdocList";
	}

	@RequiresPermissions("hm:doc:zdoc:view")
	@RequestMapping(value = "form")
	public String form(Zdoc zdoc, Model model) {
		model.addAttribute("zdoc", zdoc);
		return "modules/hm/doc/zdocForm";
	}

	@RequiresPermissions("hm:doc:zdoc:edit")
	@RequestMapping(value = "save")
	public String save(Zdoc zdoc, Model model,HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zdoc)){
			return form(zdoc, model);
		}
		
		
		if("2".equals(zdoc.getFiletype())){			
			if(zdoc.getFiles()!=null && !"".equals(zdoc.getFiles())){
				String string = zdoc.getFiles();
				//string 为当前要转换的文件
				String name = string.substring(string.lastIndexOf("/"), string.lastIndexOf("."));
				String filetype = string.substring(string.lastIndexOf(".") + 1, string.length());

				String path = string.substring(9, string.lastIndexOf("/"));
				try {
					name = java.net.URLDecoder.decode(name, "utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String dfile = path + name + "." + filetype;		//当前文件 service下的路径	
				String filepath = request.getSession().getServletContext().getRealPath("/") + dfile; 		//当前文件的绝对路径		
				
				File file = new File(filepath);
				String savefilepath = request.getSession().getServletContext().getRealPath("/");
				if ("docx".equals(filetype) || "doc".equals(filetype) || "xls".equals(filetype)
						|| "xlsx".equals(filetype) || "ppt".equals(filetype) || "pptx".equals(filetype)) {
					String filepathname = DocChangePdf.changefiles(savefilepath + path, file, name + "." + filetype);	
					System.out.println("----------");
					System.out.println(savefilepath);
					System.out.println(filepathname);
//					//最后得到的pdf
					String sile = filepathname.substring(savefilepath.length()-1, filepathname.length());
					System.out.println(sile);
					
					sile = sile.replaceAll("\\\\", "/");
					zdoc.setFiles(sile);
				}
			}			
		}
		
		zdocService.save(zdoc);
		addMessage(redirectAttributes, "保存文档中心成功");
		return "redirect:"+Global.getAdminPath()+"/hm/doc/zdoc/?repage";
	}
	
	@RequiresPermissions("hm:doc:zdoc:edit")
	@RequestMapping(value = "delete")
	public String delete(Zdoc zdoc, RedirectAttributes redirectAttributes) {
		zdocService.delete(zdoc);
		addMessage(redirectAttributes, "删除文档中心成功");
		return "redirect:"+Global.getAdminPath()+"/hm/doc/zdoc/?repage";
	}

}