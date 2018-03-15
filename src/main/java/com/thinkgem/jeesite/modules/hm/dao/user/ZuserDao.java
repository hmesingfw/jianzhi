/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.dao.user;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.hm.entity.user.Zuser;

/**
 * 用户信息DAO接口
 * @author hm
 * @version 2018-03-09
 */
@MyBatisDao
public interface ZuserDao extends CrudDao<Zuser> {
	public List<Zuser> findidcode(Zuser zuser);
}