/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.service.question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.hm.dao.question.ZquestionDao;
import com.thinkgem.jeesite.modules.hm.entity.question.Zquestion;

/**
 * 试题管理Service
 * @author hm
 * @version 2018-03-14
 */
@Service
@Transactional(readOnly = true)
public class ZquestionService extends CrudService<ZquestionDao, Zquestion> {
	
	@Autowired
	private ZquestionDao zquestionDao;
	
	public Zquestion get(String id) {
		return super.get(id);
	}
	
	public List<Zquestion> findList(Zquestion zquestion) {
		return super.findList(zquestion);
	}
	
	public Page<Zquestion> findPage(Page<Zquestion> page, Zquestion zquestion) {
		return super.findPage(page, zquestion);
	}
	
	@Transactional(readOnly = false)
	public void save(Zquestion zquestion) {
		super.save(zquestion);
	}
	
	@Transactional(readOnly = false)
	public void delete(Zquestion zquestion) {
		super.delete(zquestion);
	}
	
	public List<Zquestion> findRandList(Zquestion zquestion){
		return zquestionDao.findRandList(zquestion);
	}
}