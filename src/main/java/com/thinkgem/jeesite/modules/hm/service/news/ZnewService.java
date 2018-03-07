/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.service.news;

import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.hm.entity.news.Znew;
import com.thinkgem.jeesite.modules.hm.dao.news.ZnewDao;

/**
 * 新闻资讯Service
 * @author hm
 * @version 2018-03-07
 */
@Service
@Transactional(readOnly = true)
public class ZnewService extends CrudService<ZnewDao, Znew> {

	public Znew get(String id) {
		return super.get(id);
	}
	
	public List<Znew> findList(Znew znew) {
		return super.findList(znew);
	}
	
	public Page<Znew> findPage(Page<Znew> page, Znew znew) {
		return super.findPage(page, znew);
	}
	
	@Transactional(readOnly = false)
	public void save(Znew znew) {
		if (znew.getContent()!=null){
			znew.setContent(StringEscapeUtils.unescapeHtml4(znew.getContent()));
		}		
		super.save(znew);
	}
	
	@Transactional(readOnly = false)
	public void delete(Znew znew) {
		super.delete(znew);
	}
	
}