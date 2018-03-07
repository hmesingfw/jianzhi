/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.service.banner;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.hm.entity.banner.Zbanner;
import com.thinkgem.jeesite.modules.hm.dao.banner.ZbannerDao;

/**
 * 轮播图Service
 * @author hm
 * @version 2018-03-07
 */
@Service
@Transactional(readOnly = true)
public class ZbannerService extends CrudService<ZbannerDao, Zbanner> {

	public Zbanner get(String id) {
		return super.get(id);
	}
	
	public List<Zbanner> findList(Zbanner zbanner) {
		return super.findList(zbanner);
	}
	
	public Page<Zbanner> findPage(Page<Zbanner> page, Zbanner zbanner) {
		return super.findPage(page, zbanner);
	}
	
	@Transactional(readOnly = false)
	public void save(Zbanner zbanner) {
		super.save(zbanner);
	}
	
	@Transactional(readOnly = false)
	public void delete(Zbanner zbanner) {
		super.delete(zbanner);
	}
	
	//刷新轮播图
	public void flashBanner(){
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		ServletContext sx = webApplicationContext.getServletContext();
		
		Zbanner zbanner = new Zbanner();
		zbanner.setDelFlag("0");
		zbanner.setType("1");	//轮播图
		List<Zbanner> list = this.findList(zbanner);		
		sx.setAttribute("aboutBanner", list);
		
		zbanner.setType("2");	//轮播图
		list = this.findList(zbanner);		
		sx.setAttribute("aboutFriendship", list);
	}
}