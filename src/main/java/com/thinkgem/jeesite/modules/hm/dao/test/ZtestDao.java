/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.dao.test;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.hm.entity.test.Ztest;

/**
 * 试卷管理DAO接口
 * @author hm
 * @version 2018-03-14
 */
@MyBatisDao
public interface ZtestDao extends CrudDao<Ztest> {
	
}