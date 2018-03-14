/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.dao.question;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.hm.entity.question.Zquestion;

/**
 * 试题管理DAO接口
 * @author hm
 * @version 2018-03-14
 */
@MyBatisDao
public interface ZquestionDao extends CrudDao<Zquestion> {
	public List<Zquestion> findRandList(Zquestion zquestion);
}