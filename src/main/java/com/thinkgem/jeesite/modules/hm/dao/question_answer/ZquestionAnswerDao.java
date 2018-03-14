/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.dao.question_answer;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.hm.entity.question_answer.ZquestionAnswer;

/**
 * 试题答案管理DAO接口
 * @author hm
 * @version 2018-03-14
 */
@MyBatisDao
public interface ZquestionAnswerDao extends CrudDao<ZquestionAnswer> {
	
	public int deleteQuesid(ZquestionAnswer answer);
}