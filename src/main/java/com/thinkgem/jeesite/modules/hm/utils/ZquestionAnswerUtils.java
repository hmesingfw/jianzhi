package com.thinkgem.jeesite.modules.hm.utils;

import java.util.ArrayList;
import java.util.List;

import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.hm.dao.question_answer.ZquestionAnswerDao;
import com.thinkgem.jeesite.modules.hm.entity.question_answer.ZquestionAnswer;

public class ZquestionAnswerUtils {
	private static ZquestionAnswerDao zquestionAnswerDao = SpringContextHolder.getBean(ZquestionAnswerDao.class);

	public static final String CACHE_zquestionAnswer_LIST = "zquestionAnswerList";

	/**
	 * 获取试题答案列表
	 * @return
	 */
	public static List<ZquestionAnswer> getQuestionAnswerList(){
		@SuppressWarnings("unchecked")
		List<ZquestionAnswer> list = (List<ZquestionAnswer>)CacheUtils.get(CACHE_zquestionAnswer_LIST);
		ZquestionAnswer sort = new ZquestionAnswer();
		sort.setDelFlag("0");
		if (list==null){
			list = zquestionAnswerDao.findAllList(sort);
			CacheUtils.put(CACHE_zquestionAnswer_LIST, list);
		}
		return list;
	}
	
	/**
	 * 获取试题答案信息
	 * @param id
	 * @return
	 */
	public static ZquestionAnswer getQuestionAnswer(String id){
		@SuppressWarnings("unchecked")
		List<ZquestionAnswer> list = (List<ZquestionAnswer>)CacheUtils.get(CACHE_zquestionAnswer_LIST);
		ZquestionAnswer sort = new ZquestionAnswer();
		sort.setDelFlag("0");
		if (list==null){
			list = zquestionAnswerDao.findAllList(sort);
			CacheUtils.put(CACHE_zquestionAnswer_LIST, list);
		}
		for(ZquestionAnswer obj : list) {
			if(obj!=null && obj.getId().trim().equals(id.trim())) {
				return obj;
			}
		}
		return null;
	}
	
	/**
	 * 获取试题答案信息
	 * @param id
	 * @return
	 */
	public static List<ZquestionAnswer> getQuestionAnswerByQuestion(String id){
		@SuppressWarnings("unchecked")
		List<ZquestionAnswer> list = (List<ZquestionAnswer>)CacheUtils.get(CACHE_zquestionAnswer_LIST);
		ZquestionAnswer sort = new ZquestionAnswer();
		sort.setDelFlag("0");
		if (list==null){
			list = zquestionAnswerDao.findAllList(sort);
			CacheUtils.put(CACHE_zquestionAnswer_LIST, list);
		}
		List<ZquestionAnswer> returnlist = new ArrayList<ZquestionAnswer>();
		for(ZquestionAnswer obj : list) {
			if(obj!=null && obj.getQuesId().trim().equals(id.trim())) {
				returnlist.add(obj);
			}
		}
		return returnlist;
	}
}
