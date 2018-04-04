/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.dao.order;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.hm.entity.order.ZcourseOrder;

/**
 * 用户课程订单DAO接口
 * @author hm
 * @version 2018-03-12
 */
@MyBatisDao
public interface ZcourseOrderDao extends CrudDao<ZcourseOrder> {
	
	public List<ZcourseOrder> findMyorderByid(ZcourseOrder zcourseOrder);
	
	public List<ZcourseOrder> findMyorder(ZcourseOrder zcourseOrder);
}