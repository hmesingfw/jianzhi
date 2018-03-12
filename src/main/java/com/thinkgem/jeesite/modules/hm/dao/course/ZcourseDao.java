/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.dao.course;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.hm.entity.course.Zcourse;

/**
 * 课程信息管理DAO接口
 * @author hm
 * @version 2018-03-12
 */
@MyBatisDao
public interface ZcourseDao extends CrudDao<Zcourse> {
	
}