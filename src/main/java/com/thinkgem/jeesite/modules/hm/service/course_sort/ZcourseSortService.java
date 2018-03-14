/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.service.course_sort;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.hm.entity.course_sort.ZcourseSort;
import com.thinkgem.jeesite.modules.hm.entity.docsort.ZdocSort;
import com.thinkgem.jeesite.modules.hm.dao.course_sort.ZcourseSortDao;
import com.thinkgem.jeesite.modules.hm.dao.docsort.ZdocSortDao;

/**
 * 专业分类Service
 * @author hm
 * @version 2018-03-09
 */
@Service
@Transactional(readOnly = true)
public class ZcourseSortService extends CrudService<ZcourseSortDao, ZcourseSort> {
	
	@Autowired
	private ZcourseSortDao zcourseSortDao;
	
	public ZcourseSort get(String id) {
		return super.get(id);
	}
	
	public List<ZcourseSort> findList(ZcourseSort zcourseSort) {
		return super.findList(zcourseSort);
	}
	
	public List<ZcourseSort> findName(String name) {
		return zcourseSortDao.findName(name);
	}
	
	public Page<ZcourseSort> findPage(Page<ZcourseSort> page, ZcourseSort zcourseSort) {
		return super.findPage(page, zcourseSort);
	}
	
	@Transactional(readOnly = false)
	public void save(ZcourseSort zcourseSort) {
		super.save(zcourseSort);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZcourseSort zcourseSort) {
		deleteclid(zcourseSort.getId());
		super.delete(zcourseSort);
	}
	
	
	/**
	 * 循环删除子分类信息
	 * @param parentid	父ID
	 */
	public void deleteclid(String parentid){
		ZcourseSort entity = new ZcourseSort();
		entity.setParentId(parentid);			
		List<ZcourseSort> list = zcourseSortDao.findList(entity);
		
		ZcourseSort Sort2 = new ZcourseSort();
		if(list!=null && list.size()>0){	
			for (int i = 0; i < list.size(); i++) {			
				Sort2 = list.get(i);		
				Sort2.setDelFlag("1");
				
				deleteclid(Sort2.getId());
				super.delete(Sort2);
			}		
		}
	}
}