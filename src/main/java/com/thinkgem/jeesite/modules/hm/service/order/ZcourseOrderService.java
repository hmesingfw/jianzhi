/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.service.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.hm.entity.order.ZcourseOrder;
import com.thinkgem.jeesite.modules.hm.dao.order.ZcourseOrderDao;

/**
 * 用户课程订单Service
 * @author hm
 * @version 2018-03-12
 */
@Service
@Transactional(readOnly = true)
public class ZcourseOrderService extends CrudService<ZcourseOrderDao, ZcourseOrder> {

	@Autowired
	private ZcourseOrderDao zcourseOrderDao;
	
	public ZcourseOrder get(String id) {
		return super.get(id);
	}
	
	public List<ZcourseOrder> findList(ZcourseOrder zcourseOrder) {
		return super.findList(zcourseOrder);
	}
	
	public Page<ZcourseOrder> findPage(Page<ZcourseOrder> page, ZcourseOrder zcourseOrder) {
		return super.findPage(page, zcourseOrder);
	}
	
	@Transactional(readOnly = false)
	public void save(ZcourseOrder zcourseOrder) {
		super.save(zcourseOrder);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZcourseOrder zcourseOrder) {
		super.delete(zcourseOrder);
	}
	
	public List<ZcourseOrder> findMyorderByid(ZcourseOrder zcourseOrder){
		return zcourseOrderDao.findMyorderByid(zcourseOrder);
	}
	
	public List<ZcourseOrder> findMyorder(ZcourseOrder zcourseOrder){
		return zcourseOrderDao.findMyorder(zcourseOrder);
	}
}