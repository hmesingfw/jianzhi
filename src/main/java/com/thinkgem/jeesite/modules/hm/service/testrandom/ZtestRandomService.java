/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.service.testrandom;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.hm.entity.testrandom.ZtestRandom;
import com.thinkgem.jeesite.modules.hm.dao.testrandom.ZtestRandomDao;

/**
 * 随机测试题Service
 * @author hm
 * @version 2018-04-03
 */
@Service
@Transactional(readOnly = true)
public class ZtestRandomService extends CrudService<ZtestRandomDao, ZtestRandom> {

	public ZtestRandom get(String id) {
		return super.get(id);
	}
	
	public List<ZtestRandom> findList(ZtestRandom ztestRandom) {
		return super.findList(ztestRandom);
	}
	
	public Page<ZtestRandom> findPage(Page<ZtestRandom> page, ZtestRandom ztestRandom) {
		return super.findPage(page, ztestRandom);
	}
	
	@Transactional(readOnly = false)
	public void save(ZtestRandom ztestRandom) {
		ZtestRandom ztestRandoms = super.get(ztestRandom.getId());
		if(ztestRandoms!=null && "0".equals(ztestRandoms.getDelFlag())){
			ztestRandom.preUpdate();
			dao.update(ztestRandom);
		}else{			 
			dao.insert(ztestRandom);
		}	 
	}
	
	@Transactional(readOnly = false)
	public void delete(ZtestRandom ztestRandom) {
		super.delete(ztestRandom);
	}
	
}