/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.service.test_question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.hm.entity.test_question.ZtestQuestion;
import com.thinkgem.jeesite.modules.hm.dao.test_question.ZtestQuestionDao;

/**
 * 试卷题目内容管理Service
 * @author hm
 * @version 2018-03-14
 */
@Service
@Transactional(readOnly = true)
public class ZtestQuestionService extends CrudService<ZtestQuestionDao, ZtestQuestion> {

	@Autowired
	private ZtestQuestionDao ztestQuestionDao;
	
	public ZtestQuestion get(String id) {
		return super.get(id);
	}
	
	public List<ZtestQuestion> findList(ZtestQuestion ztestQuestion) {
		return super.findList(ztestQuestion);
	}
	
	public Page<ZtestQuestion> findPage(Page<ZtestQuestion> page, ZtestQuestion ztestQuestion) {
		return super.findPage(page, ztestQuestion);
	}
	
	@Transactional(readOnly = false)
	public void save(ZtestQuestion ztestQuestion) {
		super.save(ztestQuestion);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZtestQuestion ztestQuestion) {
		super.delete(ztestQuestion);
	}
	
	@Transactional(readOnly = false)
	public void deleteTestid(ZtestQuestion ztestQuestion){
		ztestQuestionDao.deleteTestid(ztestQuestion);
	}
}