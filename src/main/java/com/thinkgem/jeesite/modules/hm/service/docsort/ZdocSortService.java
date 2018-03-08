/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.service.docsort;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private ZdocSortDao zdocSortDao;
	
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
		deleteclid(zdocSort.getId());
		super.delete(zdocSort);
	}
	
	/**
	 * 循环删除子分类信息
	 * @param parentid	父ID
	 */
	public void deleteclid(String parentid){
		ZdocSort entity = new ZdocSort();
		entity.setParent(parentid);			
		List<ZdocSort> list = zdocSortDao.findList(entity);
		
		ZdocSort zdocSort2 = new ZdocSort();
		if(list!=null && list.size()>0){	
			for (int i = 0; i < list.size(); i++) {			
				zdocSort2 = list.get(i);		
				zdocSort2.setDelFlag("1");
				
				deleteclid(zdocSort2.getId());
				super.delete(zdocSort2);
			}		
		}
	}
	
	
}