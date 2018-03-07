/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.dao.news;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.hm.entity.news.Znew;

/**
 * 新闻资讯DAO接口
 * @author hm
 * @version 2018-03-07
 */
@MyBatisDao
public interface ZnewDao extends CrudDao<Znew> {
	
}