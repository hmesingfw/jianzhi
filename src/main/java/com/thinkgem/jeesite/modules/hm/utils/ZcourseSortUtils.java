package com.thinkgem.jeesite.modules.hm.utils;

import java.util.List;

import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.hm.dao.course_sort.ZcourseSortDao;
import com.thinkgem.jeesite.modules.hm.entity.course_sort.ZcourseSort;

public class ZcourseSortUtils {
	private static ZcourseSortDao zcourseSortDao = SpringContextHolder.getBean(ZcourseSortDao.class);

	public static final String CACHE_COURSE_LIST = "videoCourseList";

	/**
	 * 获取课程专业分类列表
	 * @return
	 */
	public static List<ZcourseSort> getCourseSortList(){
		@SuppressWarnings("unchecked")
		List<ZcourseSort> list = (List<ZcourseSort>)CacheUtils.get(CACHE_COURSE_LIST);
		ZcourseSort sort = new ZcourseSort();
		sort.setDelFlag("0");
		if (list==null){
			list = zcourseSortDao.findAllList(sort);
			CacheUtils.put(CACHE_COURSE_LIST, list);
		}
		return list;
	}
	
	/**
	 * 获取课程专业分类信息
	 * @param id
	 * @return
	 */
	public static ZcourseSort getCourseSort(String id){
		@SuppressWarnings("unchecked")
		List<ZcourseSort> list = (List<ZcourseSort>)CacheUtils.get(CACHE_COURSE_LIST);
		ZcourseSort sort = new ZcourseSort();
		sort.setDelFlag("0");
		if (list==null){
			list = zcourseSortDao.findAllList(sort);
			CacheUtils.put(CACHE_COURSE_LIST, list);
		}
		for(ZcourseSort obj : list) {
			if(obj!=null && obj.getId().trim().equals(id.trim())) {
				return obj;
			}
		}
		return null;
	}
}
