/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.dao.course_sort;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.hm.entity.course_sort.ZcourseSort;

/**
 * 专业分类DAO接口
 * @author hm
 * @version 2018-03-09
 */
@MyBatisDao
public interface ZcourseSortDao extends CrudDao<ZcourseSort> {
	
	public int deleteParent(ZcourseSort zcourseSort);
	
	public List<ZcourseSort> findName(String name);
}