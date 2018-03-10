/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.service.userdoc;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.hm.entity.userdoc.ZuserDoc;
import com.thinkgem.jeesite.modules.hm.dao.userdoc.ZuserDocDao;

/**
 * 用户文档下载记录Service
 * @author hm
 * @version 2018-03-10
 */
@Service
@Transactional(readOnly = true)
public class ZuserDocService extends CrudService<ZuserDocDao, ZuserDoc> {

	public ZuserDoc get(String id) {
		return super.get(id);
	}
	
	public List<ZuserDoc> findList(ZuserDoc zuserDoc) {
		return super.findList(zuserDoc);
	}
	
	public Page<ZuserDoc> findPage(Page<ZuserDoc> page, ZuserDoc zuserDoc) {
		return super.findPage(page, zuserDoc);
	}
	
	@Transactional(readOnly = false)
	public void save(ZuserDoc zuserDoc) {
		super.save(zuserDoc);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZuserDoc zuserDoc) {
		super.delete(zuserDoc);
	}
	
}