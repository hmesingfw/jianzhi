/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.service.docsort;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.hm.entity.docsort.ZdocSort;
import com.thinkgem.jeesite.modules.hm.dao.docsort.ZdocSortDao;

/**
 * 文档分类Service
 * @author hm
 * @version 2018-03-07
 */
@Service
@Transactional(readOnly = true)
public class ZdocSortService extends CrudService<ZdocSortDao, ZdocSort> {

	public ZdocSort get(String id) {
		return super.get(id);
	}
	
	public List<ZdocSort> findList(ZdocSort zdocSort) {
		return super.findList(zdocSort);
	}
	
	public Page<ZdocSort> findPage(Page<ZdocSort> page, ZdocSort zdocSort) {
		return super.findPage(page, zdocSort);
	}
	
	@Transactional(readOnly = false)
	public void save(ZdocSort zdocSort) {
		super.save(zdocSort);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZdocSort zdocSort) {
		super.delete(zdocSort);
	}
	
}