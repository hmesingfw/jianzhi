package com.thinkgem.jeesite.modules.hm.utils;

import java.util.List;

import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.hm.dao.user.ZuserDao;
import com.thinkgem.jeesite.modules.hm.entity.user.Zuser;

public class ZuserUtils {
	private static ZuserDao zuserDao = SpringContextHolder.getBean(ZuserDao.class);

	public static final String CACHE_Zuser_LIST = "videoCourseList";

	/**
	 * 获取文类分类列表
	 * @return
	 */
	public static List<Zuser> getZuserList(){
		@SuppressWarnings("unchecked")
		List<Zuser> list = (List<Zuser>)CacheUtils.get(CACHE_Zuser_LIST);
		Zuser sort = new Zuser();
		sort.setDelFlag("0");
		if (list==null){
			list = zuserDao.findAllList(sort);
			CacheUtils.put(CACHE_Zuser_LIST, list);
		}
		return list;
	}
	
	/**
	 * 获取文档分类信息
	 * @param id
	 * @return
	 */
	public static Zuser getZuser(String id){
		@SuppressWarnings("unchecked")
		List<Zuser> list = (List<Zuser>)CacheUtils.get(CACHE_Zuser_LIST);
		Zuser sort = new Zuser();
		sort.setDelFlag("0");
		if (list==null){
			list = zuserDao.findAllList(sort);
			CacheUtils.put(CACHE_Zuser_LIST, list);
		}
		for(Zuser obj : list) {
			if(obj!=null && obj.getId().trim().equals(id.trim())) {
				return obj;
			}
		}
		return null;
	}
}
