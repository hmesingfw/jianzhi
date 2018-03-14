/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.service.test;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.hm.entity.test.Ztest;
import com.thinkgem.jeesite.modules.hm.dao.test.ZtestDao;

/**
 * 试卷管理Service
 * @author hm
 * @version 2018-03-14
 */
@Service
@Transactional(readOnly = true)
public class ZtestService extends CrudService<ZtestDao, Ztest> {

	public Ztest get(String id) {
		return super.get(id);
	}
	
	public List<Ztest> findList(Ztest ztest) {
		return super.findList(ztest);
	}
	
	public Page<Ztest> findPage(Page<Ztest> page, Ztest ztest) {
		return super.findPage(page, ztest);
	}
	
	@Transactional(readOnly = false)
	public void save(Ztest ztest) {
		super.save(ztest);
	}
	
	@Transactional(readOnly = false)
	public void delete(Ztest ztest) {
		super.delete(ztest);
	}
	
}