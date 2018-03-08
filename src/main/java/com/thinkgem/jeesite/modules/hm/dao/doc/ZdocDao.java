/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.dao.doc;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.hm.entity.doc.Zdoc;

/**
 * 文档中心DAO接口
 * @author hm
 * @version 2018-03-08
 */
@MyBatisDao
public interface ZdocDao extends CrudDao<Zdoc> {
	public List<Zdoc> findDownTop();
	
	public int updateLook(Zdoc zdoc);
	
	public int updateDown(Zdoc zdoc);
}