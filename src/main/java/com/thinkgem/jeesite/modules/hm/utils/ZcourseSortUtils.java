package com.thinkgem.jeesite.modules.hm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.hm.dao.course_sort.ZcourseSortDao;
import com.thinkgem.jeesite.modules.hm.entity.course_sort.ZcourseSort;

public class ZcourseSortUtils {
	private static ZcourseSortDao zcourseSortDao = SpringContextHolder.getBean(ZcourseSortDao.class);

	public static final String CACHE_zcourseSort_LIST = "zoursortList";

	/**
	 * 获取课程专业分类列表
	 * @return
	 */
	public static List<ZcourseSort> getCourseSortList(){
		@SuppressWarnings("unchecked")
		List<ZcourseSort> list = (List<ZcourseSort>)CacheUtils.get(CACHE_zcourseSort_LIST);
		ZcourseSort sort = new ZcourseSort();
		sort.setDelFlag("0");
		if (list==null){
			list = zcourseSortDao.findAllList(sort);
			CacheUtils.put(CACHE_zcourseSort_LIST, list);
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
		List<ZcourseSort> list = (List<ZcourseSort>)CacheUtils.get(CACHE_zcourseSort_LIST);
		ZcourseSort sort = new ZcourseSort();
		sort.setDelFlag("0");
		if (list==null){
			list = zcourseSortDao.findAllList(sort);
			CacheUtils.put(CACHE_zcourseSort_LIST, list);
		}
		for(ZcourseSort obj : list) {
			if(obj!=null && obj.getId().trim().equals(id.trim())) {
				return obj;
			}
		}
		return null;
	}
	
	/**
	 * 专业是否到期
	 * date  购买时间
	 * num 	 专业到期天数
	 * @return	true 到期     false没到期
	 */
	public static boolean isExp(Date date, int num){		
		try {
			String pay = plusDay(date, num); //到期时间			
		
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH"); 
			Date day=new Date();   					 
			Date dt1 = df.parse(pay);
			
			if(dt1.getTime()>day.getTime()){
				System.out.println("大于");
				return true;
			}else{
				System.out.println("小于");
				return false;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return false;
	}
	
	
	public static String plusDay(Date currdate, int num) throws ParseException{
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         System.out.println("现在的日期是：" + currdate);
         Calendar ca = Calendar.getInstance();
         ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
         currdate = ca.getTime();
         String enddate = format.format(currdate);
         System.out.println("增加天数以后的日期：" + enddate);
         return enddate;
    }
	 
	
}
