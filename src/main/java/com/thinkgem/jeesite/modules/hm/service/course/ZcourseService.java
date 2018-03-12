/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.service.course;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.hm.entity.course.Zcourse;
import com.thinkgem.jeesite.modules.hm.dao.course.ZcourseDao;

/**
 * 课程信息管理Service
 * @author hm
 * @version 2018-03-12
 */
@Service
@Transactional(readOnly = true)
public class ZcourseService extends CrudService<ZcourseDao, Zcourse> {

	public Zcourse get(String id) {
		return super.get(id);
	}
	
	public List<Zcourse> findList(Zcourse zcourse) {
		return super.findList(zcourse);
	}
	
	public Page<Zcourse> findPage(Page<Zcourse> page, Zcourse zcourse) {
		return super.findPage(page, zcourse);
	}
	
	@Transactional(readOnly = false)
	public void save(Zcourse zcourse) {
		super.save(zcourse);
	}
	
	@Transactional(readOnly = false)
	public void delete(Zcourse zcourse) {
		super.delete(zcourse);
	}
	
}