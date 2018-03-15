/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.hm.entity.user.Zuser;
import com.thinkgem.jeesite.modules.hm.dao.user.ZuserDao;

/**
 * 用户信息Service
 * @author hm
 * @version 2018-03-09
 */
@Service
@Transactional(readOnly = true)
public class ZuserService extends CrudService<ZuserDao, Zuser> {

	@Autowired
	private ZuserDao zuserDao;
	
	public Zuser get(String id) {
		return super.get(id);
	}
	
	public List<Zuser> findList(Zuser zuser) {
		return super.findList(zuser);
	}
	
	public Page<Zuser> findPage(Page<Zuser> page, Zuser zuser) {
		return super.findPage(page, zuser);
	}
	
	@Transactional(readOnly = false)
	public void save(Zuser zuser) {
		super.save(zuser);
	}
	
	@Transactional(readOnly = false)
	public void delete(Zuser zuser) {
		super.delete(zuser);
	}
	
	public List<Zuser> findidcode(Zuser zuser){
		return zuserDao.findidcode(zuser);
	}
	
}