package com.thinkgem.jeesite.modules.hm.utils;

import java.util.List;

import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.hm.dao.testrandom.ZtestRandomDao;
import com.thinkgem.jeesite.modules.hm.entity.testrandom.ZtestRandom;

public class ZcourseTestRandom {
	private static ZtestRandomDao ztestRandomDao = SpringContextHolder.getBean(ZtestRandomDao.class);

	public static final String CACHE_testRandom_LIST = "testRandomList";

	/**
	 * 获取课程列表
	 * @return
	 */
	public static List<ZtestRandom> getZtestRandomList(){
		@SuppressWarnings("unchecked")
		List<ZtestRandom> list = (List<ZtestRandom>)CacheUtils.get(CACHE_testRandom_LIST);
		ZtestRandom sort = new ZtestRandom();
		sort.setDelFlag("0");
		if (list==null){
			list = ztestRandomDao.findAllList(sort);
			CacheUtils.put(CACHE_testRandom_LIST, list);
		}
		return list;
	}
	
	/**
	 * 获取课程信息
	 * @param id
	 * @return
	 */
	public static ZtestRandom getZtestRandom(String id){
		@SuppressWarnings("unchecked")
		List<ZtestRandom> list = (List<ZtestRandom>)CacheUtils.get(CACHE_testRandom_LIST);
		ZtestRandom sort = new ZtestRandom();
		sort.setDelFlag("0");
		if (list==null){
			list = ztestRandomDao.findAllList(sort);
			CacheUtils.put(CACHE_testRandom_LIST, list);
		}
		for(ZtestRandom obj : list) {
			if(obj!=null && obj.getId().trim().equals(id.trim())) {
				return obj;
			}
		}
		return null;
	}
}
