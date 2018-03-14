/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.service.question_answer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.hm.entity.question_answer.ZquestionAnswer;
import com.thinkgem.jeesite.modules.hm.dao.question_answer.ZquestionAnswerDao;

/**
 * 试题答案管理Service
 * @author hm
 * @version 2018-03-14
 */
@Service
@Transactional(readOnly = true)
public class ZquestionAnswerService extends CrudService<ZquestionAnswerDao, ZquestionAnswer> {
	
	@Autowired
	private ZquestionAnswerDao zquestionAnswerDao;
	
	public ZquestionAnswer get(String id) {
		return super.get(id);
	}
	
	public List<ZquestionAnswer> findList(ZquestionAnswer zquestionAnswer) {
		return super.findList(zquestionAnswer);
	}
	
	public Page<ZquestionAnswer> findPage(Page<ZquestionAnswer> page, ZquestionAnswer zquestionAnswer) {
		return super.findPage(page, zquestionAnswer);
	}
	
	@Transactional(readOnly = false)
	public void save(ZquestionAnswer zquestionAnswer) {
		super.save(zquestionAnswer);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZquestionAnswer zquestionAnswer) {
		super.delete(zquestionAnswer);
	}
	
	@Transactional(readOnly = false)
	public void deleteQuesid(ZquestionAnswer zquestionAnswer){
		zquestionAnswerDao.deleteQuesid(zquestionAnswer);
	}
}