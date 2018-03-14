package com.thinkgem.jeesite.modules.hm.utils;

import java.util.List;

import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.hm.dao.question.ZquestionDao;
import com.thinkgem.jeesite.modules.hm.entity.question.Zquestion;

public class ZquestionUtils {
	private static ZquestionDao zquestionDao = SpringContextHolder.getBean(ZquestionDao.class);

	public static final String CACHE_ZquestionSort_LIST = "ZquestionList";

	/**
	 * 获取课程专业分类列表
	 * @return
	 */
	public static List<Zquestion> getQuestionList(){
		@SuppressWarnings("unchecked")
		List<Zquestion> list = (List<Zquestion>)CacheUtils.get(CACHE_ZquestionSort_LIST);
		Zquestion sort = new Zquestion();
		sort.setDelFlag("0");
		if (list==null){
			list = zquestionDao.findAllList(sort);
			CacheUtils.put(CACHE_ZquestionSort_LIST, list);
		}
		return list;
	}
	
	/**
	 * 获取课程专业分类信息
	 * @param id
	 * @return
	 */
	public static Zquestion getQuestion(String id){
		@SuppressWarnings("unchecked")
		List<Zquestion> list = (List<Zquestion>)CacheUtils.get(CACHE_ZquestionSort_LIST);
		Zquestion sort = new Zquestion();
		sort.setDelFlag("0");
		if (list==null){
			list = zquestionDao.findAllList(sort);
			CacheUtils.put(CACHE_ZquestionSort_LIST, list);
		}
		for(Zquestion obj : list) {
			if(obj!=null && obj.getId().trim().equals(id.trim())) {
				return obj;
			}
		}
		return null;
	}
}
