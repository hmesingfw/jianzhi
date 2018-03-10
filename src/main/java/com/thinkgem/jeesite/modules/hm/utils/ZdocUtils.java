package com.thinkgem.jeesite.modules.hm.utils;

import java.util.List;

import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.hm.dao.doc.ZdocDao;
import com.thinkgem.jeesite.modules.hm.entity.doc.Zdoc;

public class ZdocUtils {
	private static ZdocDao zdocDao = SpringContextHolder.getBean(ZdocDao.class);

	public static final String CACHE_ZdocDao_LIST = "zdocDaoList";

	/**
	 * 获取课程专业分类列表
	 * @return
	 */
	public static List<Zdoc> getDocList(){
		@SuppressWarnings("unchecked")
		List<Zdoc> list = (List<Zdoc>)CacheUtils.get(CACHE_ZdocDao_LIST);
		Zdoc sort = new Zdoc();
		sort.setDelFlag("0");
		if (list==null){
			list = zdocDao.findAllList(sort);
			CacheUtils.put(CACHE_ZdocDao_LIST, list);
		}
		return list;
	}
	
	/**
	 * 获取课程专业分类信息
	 * @param id
	 * @return
	 */
	public static Zdoc getDoc(String id){
		@SuppressWarnings("unchecked")
		List<Zdoc> list = (List<Zdoc>)CacheUtils.get(CACHE_ZdocDao_LIST);
		Zdoc sort = new Zdoc();
		sort.setDelFlag("0");
		if (list==null){
			list = zdocDao.findAllList(sort);
			CacheUtils.put(CACHE_ZdocDao_LIST, list);
		}
		for(Zdoc obj : list) {
			if(obj!=null && obj.getId().trim().equals(id.trim())) {
				return obj;
			}
		}
		return null;
	}
}
