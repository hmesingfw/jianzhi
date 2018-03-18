/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.dao.usertest;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.hm.entity.usertest.ZuserTest;

/**
 * 用户答题记录DAO接口
 * @author hm
 * @version 2018-03-15
 */
@MyBatisDao
public interface ZuserTestDao extends CrudDao<ZuserTest> {
	public void deleteUesrtest(ZuserTest zuserTest);
	
	public List<ZuserTest> findNextTest(ZuserTest zuserTest);
	
	public List<ZuserTest> findMyUserTest(ZuserTest zuserTest);
}