/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.service.aboutus;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.hm.entity.aboutus.TaboutUs;
import com.thinkgem.jeesite.modules.hm.dao.aboutus.TaboutUsDao;

/**
 * 关于我们信息Service
 * @author hm
 * @version 2018-03-05
 */
@Service
@Transactional(readOnly = true)
public class TaboutUsService extends CrudService<TaboutUsDao, TaboutUs> {

	public TaboutUs get(String id) {
		return super.get(id);
	}
	
	public List<TaboutUs> findList(TaboutUs taboutUs) {
		return super.findList(taboutUs);
	}
	
	public Page<TaboutUs> findPage(Page<TaboutUs> page, TaboutUs taboutUs) {
		return super.findPage(page, taboutUs);
	}
	
	@Transactional(readOnly = false)
	public void save(TaboutUs taboutUs) {
		super.save(taboutUs);
	}
	
	@Transactional(readOnly = false)
	public void delete(TaboutUs taboutUs) {
		super.delete(taboutUs);
	}
	
}