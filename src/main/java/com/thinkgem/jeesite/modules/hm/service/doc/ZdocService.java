/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.service.doc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.hm.entity.doc.Zdoc;
import com.thinkgem.jeesite.modules.hm.dao.doc.ZdocDao;

/**
 * 文档中心Service
 * @author hm
 * @version 2018-03-08
 */
@Service
@Transactional(readOnly = true)
public class ZdocService extends CrudService<ZdocDao, Zdoc> {

	@Autowired
	private ZdocDao zdocDao;
	
	public Zdoc get(String id) {
		return super.get(id);
	}
	
	public List<Zdoc> findList(Zdoc zdoc) {
		return super.findList(zdoc);
	}
	
	public Page<Zdoc> findPage(Page<Zdoc> page, Zdoc zdoc) {
		return super.findPage(page, zdoc);
	}
	
	@Transactional(readOnly = false)
	public void save(Zdoc zdoc) {
		super.save(zdoc);
	}
	
	@Transactional(readOnly = false)
	public void delete(Zdoc zdoc) {
		super.delete(zdoc);
	}
	
	public List<Zdoc> findDownTop(){
		return zdocDao.findDownTop();
	}
	
	@Transactional(readOnly = false)
	public int updateLook(Zdoc zdoc){
		return zdocDao.updateLook(zdoc);
	}
	
	@Transactional(readOnly = false)
	public int updateDown(Zdoc zdoc){
		return zdocDao.updateDown(zdoc);
	}
	
}