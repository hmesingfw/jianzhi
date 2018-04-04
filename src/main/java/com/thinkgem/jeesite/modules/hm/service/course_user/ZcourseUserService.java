/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.service.course_user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.hm.entity.course_user.ZcourseUser;
import com.thinkgem.jeesite.modules.hm.dao.course_user.ZcourseUserDao;

/**
 * 用户课程学习记录Service
 * @author hm
 * @version 2018-03-16
 */
@Service
@Transactional(readOnly = true)
public class ZcourseUserService extends CrudService<ZcourseUserDao, ZcourseUser> {

	@Autowired
	private ZcourseUserDao zcourseUserDao;
	
	public ZcourseUser get(String id) {
		return super.get(id);
	}
	
	public List<ZcourseUser> findList(ZcourseUser zcourseUser) {
		return super.findList(zcourseUser);
	}
	
	public Page<ZcourseUser> findPage(Page<ZcourseUser> page, ZcourseUser zcourseUser) {
		return super.findPage(page, zcourseUser);
	}
	
	@Transactional(readOnly = false)
	public void save(ZcourseUser zcourseUser) {
		super.save(zcourseUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZcourseUser zcourseUser) {
		super.delete(zcourseUser);
	}
	
	@Transactional(readOnly = false)
	public void updateUsertime(ZcourseUser zcourseUser){
		zcourseUserDao.updateUsertime(zcourseUser);;
	}
	
	public List<ZcourseUser> findListByCourseUser(ZcourseUser zcourseUser){
		return zcourseUserDao.findListByCourseUser(zcourseUser);
	}
	
}