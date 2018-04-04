/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.service.coursehour;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.hm.entity.coursehour.ZcourseHour;
import com.thinkgem.jeesite.modules.hm.dao.coursehour.ZcourseHourDao;

/**
 * 课时管理Service
 * @author hm
 * @version 2018-04-03
 */
@Service
@Transactional(readOnly = true)
public class ZcourseHourService extends CrudService<ZcourseHourDao, ZcourseHour> {

	public ZcourseHour get(String id) {
		return super.get(id);
	}
	
	public List<ZcourseHour> findList(ZcourseHour zcourseHour) {
		return super.findList(zcourseHour);
	}
	
	public Page<ZcourseHour> findPage(Page<ZcourseHour> page, ZcourseHour zcourseHour) {
		return super.findPage(page, zcourseHour);
	}
	
	@Transactional(readOnly = false)
	public void save(ZcourseHour zcourseHour) {
		super.save(zcourseHour);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZcourseHour zcourseHour) {
		super.delete(zcourseHour);
	}
	
	public List<ZcourseHour> findListByCourseid(ZcourseHour zcourseHour){
		return dao.findListByCourseid(zcourseHour);
	}
}