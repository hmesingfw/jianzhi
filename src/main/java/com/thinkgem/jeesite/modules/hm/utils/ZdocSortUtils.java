package com.thinkgem.jeesite.modules.hm.utils;

import java.util.List;

import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.hm.dao.docsort.ZdocSortDao;
import com.thinkgem.jeesite.modules.hm.entity.docsort.ZdocSort;

public class ZdocSortUtils {
	private static ZdocSortDao zdocsortDao = SpringContextHolder.getBean(ZdocSortDao.class);

	public static final String CACHE_docsort_LIST = "docsortList";

	/**
	 * 获取文类分类列表
	 * @return
	 */
	public static List<ZdocSort> getDocSortList(){
		@SuppressWarnings("unchecked")
		List<ZdocSort> list = (List<ZdocSort>)CacheUtils.get(CACHE_docsort_LIST);
		ZdocSort sort = new ZdocSort();
		sort.setDelFlag("0");
		if (list==null){
			list = zdocsortDao.findAllList(sort);
			CacheUtils.put(CACHE_docsort_LIST, list);
		}
		return list;
	}
	
	/**
	 * 获取文档分类信息
	 * @param id
	 * @return
	 */
	public static ZdocSort getDocSort(String id){
		@SuppressWarnings("unchecked")
		List<ZdocSort> list = (List<ZdocSort>)CacheUtils.get(CACHE_docsort_LIST);
		ZdocSort sort = new ZdocSort();
		sort.setDelFlag("0");
		if (list==null){
			list = zdocsortDao.findAllList(sort);
			CacheUtils.put(CACHE_docsort_LIST, list);
		}
		for(ZdocSort obj : list) {
			if(obj!=null && obj.getId().trim().equals(id.trim())) {
				return obj;
			}
		}
		return null;
	}
}
