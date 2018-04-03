/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.dao.testrandom;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.hm.entity.testrandom.ZtestRandom;

/**
 * 随机测试题DAO接口
 * @author hm
 * @version 2018-04-03
 */
@MyBatisDao
public interface ZtestRandomDao extends CrudDao<ZtestRandom> {
	
}