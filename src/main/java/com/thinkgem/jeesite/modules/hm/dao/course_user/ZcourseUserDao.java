/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.dao.course_user;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.hm.entity.course_user.ZcourseUser;

/**
 * 用户课程学习记录DAO接口
 * @author hm
 * @version 2018-03-16
 */
@MyBatisDao
public interface ZcourseUserDao extends CrudDao<ZcourseUser> {
	public void updateUsertime(ZcourseUser zcourseUser);
}