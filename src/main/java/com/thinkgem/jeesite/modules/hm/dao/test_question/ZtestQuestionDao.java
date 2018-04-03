/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.dao.test_question;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.hm.entity.test_question.ZtestQuestion;

/**
 * 试卷题目内容管理DAO接口
 * @author hm
 * @version 2018-03-14
 */
@MyBatisDao
public interface ZtestQuestionDao extends CrudDao<ZtestQuestion> {
	public void deleteTestid(ZtestQuestion ztestQuestion);
	
	public List<ZtestQuestion> findListLeftquestion(ZtestQuestion ztestQuestion);
}