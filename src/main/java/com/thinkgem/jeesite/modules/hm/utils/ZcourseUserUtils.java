package com.thinkgem.jeesite.modules.hm.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.thinkgem.jeesite.common.utils.StringUtils;

public class ZcourseUserUtils {

	/**
	 * 学习进度
	 * @param usertime	当前学习时间
	 * @param coursetime	总时间
	 * @return
	 */
	public static int getCourseSchedule(String usertime, String coursetime){
		double utime = 0;		//课程学习时长
		double ctime = 0;		//课程学习总时长
		if(usertime!=null && StringUtils.isNumeric(usertime)){
			utime = Integer.parseInt(usertime);
		}else{
			return 0;
		}
		
		if(coursetime!=null && StringUtils.isNumeric(coursetime)){
			ctime = Integer.parseInt(coursetime);
		}else{
			return 0;
		}
		System.out.println(utime);
		System.out.println(ctime);
		double sche = utime/ctime * 100;
		System.out.println("...........");
		System.out.println(sche);
		
		 
		sche = Math.floor(sche);
		if(sche>100){
			return 100;
		}else{
			return (int)sche;
		}
			
	}
}
