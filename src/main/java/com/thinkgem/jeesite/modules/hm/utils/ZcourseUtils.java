package com.thinkgem.jeesite.modules.hm.utils;

import java.util.List;

import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.hm.dao.course.ZcourseDao;
import com.thinkgem.jeesite.modules.hm.entity.course.Zcourse;

public class ZcourseUtils {
	private static ZcourseDao zcourseDao = SpringContextHolder.getBean(ZcourseDao.class);

	public static final String CACHE_zcourse_LIST = "zourList";

	/**
	 * 获取课程列表
	 * @return
	 */
	public static List<Zcourse> getCourseList(){
		@SuppressWarnings("unchecked")
		List<Zcourse> list = (List<Zcourse>)CacheUtils.get(CACHE_zcourse_LIST);
		Zcourse sort = new Zcourse();
		sort.setDelFlag("0");
		if (list==null){
			list = zcourseDao.findAllList(sort);
			CacheUtils.put(CACHE_zcourse_LIST, list);
		}
		return list;
	}
	
	/**
	 * 获取课程信息
	 * @param id
	 * @return
	 */
	public static Zcourse getCourse(String id){
		@SuppressWarnings("unchecked")
		List<Zcourse> list = (List<Zcourse>)CacheUtils.get(CACHE_zcourse_LIST);
		Zcourse sort = new Zcourse();
		sort.setDelFlag("0");
		if (list==null){
			list = zcourseDao.findAllList(sort);
			CacheUtils.put(CACHE_zcourse_LIST, list);
		}
		for(Zcourse obj : list) {
			if(obj!=null && obj.getId().trim().equals(id.trim())) {
				return obj;
			}
		}
		return null;
	}
}
