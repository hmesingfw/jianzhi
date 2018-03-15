/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.service.usertest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.hm.entity.usertest.ZuserTest;
import com.thinkgem.jeesite.modules.hm.dao.usertest.ZuserTestDao;

/**
 * 用户答题记录Service
 * @author hm
 * @version 2018-03-15
 */
@Service
@Transactional(readOnly = true)
public class ZuserTestService extends CrudService<ZuserTestDao, ZuserTest> {

	@Autowired
	private ZuserTestDao zuserTestDao;
	
	public ZuserTest get(String id) {
		return super.get(id);
	}
	
	public List<ZuserTest> findList(ZuserTest zuserTest) {
		return super.findList(zuserTest);
	}
	
	public Page<ZuserTest> findPage(Page<ZuserTest> page, ZuserTest zuserTest) {
		return super.findPage(page, zuserTest);
	}
	
	@Transactional(readOnly = false)
	public void save(ZuserTest zuserTest) {
		super.save(zuserTest);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZuserTest zuserTest) {
		super.delete(zuserTest);
	}
	
	@Transactional(readOnly = false)
	public void deleteUesrtest(ZuserTest zuserTest){
		 zuserTestDao.deleteUesrtest(zuserTest);
	}
	
	public List<ZuserTest> findNextTest(ZuserTest zuserTest){
		return zuserTestDao.findNextTest(zuserTest);
	}
}