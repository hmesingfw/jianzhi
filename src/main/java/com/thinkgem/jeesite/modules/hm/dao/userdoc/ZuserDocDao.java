/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.dao.userdoc;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.hm.entity.userdoc.ZuserDoc;

/**
 * 用户文档下载记录DAO接口
 * @author hm
 * @version 2018-03-10
 */
@MyBatisDao
public interface ZuserDocDao extends CrudDao<ZuserDoc> {
	
}