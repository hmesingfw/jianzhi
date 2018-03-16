/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.entity.course_user;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 用户课程学习记录Entity
 * @author hm
 * @version 2018-03-16
 */
public class ZcourseUser extends DataEntity<ZcourseUser> {
	
	private static final long serialVersionUID = 1L;
	private String courseid;		// 课程编号
	private String userid;		// 用户编号
	private String coursetime;		// 课程总时长单位秒
	private String usertime;		// 用户学习时长
	private String lasttime;		// 上次观看的时间
	private String islooksuc;		// 是否学习完成
	
	public ZcourseUser() {
		super();
	}

	public ZcourseUser(String id){
		super(id);
	}

	@Length(min=0, max=64, message="课程编号长度必须介于 0 和 64 之间")
	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}
	
	@Length(min=0, max=64, message="用户编号长度必须介于 0 和 64 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Length(min=0, max=50, message="课程总时长单位秒长度必须介于 0 和 50 之间")
	public String getCoursetime() {
		return coursetime;
	}

	public void setCoursetime(String coursetime) {
		this.coursetime = coursetime;
	}
	
	@Length(min=0, max=50, message="用户学习时长长度必须介于 0 和 50 之间")
	public String getUsertime() {
		return usertime;
	}

	public void setUsertime(String usertime) {
		this.usertime = usertime;
	}
	
	@Length(min=0, max=50, message="上次观看的时间长度必须介于 0 和 50 之间")
	public String getLasttime() {
		return lasttime;
	}

	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}
	
	@Length(min=0, max=10, message="是否学习完成长度必须介于 0 和 10 之间")
	public String getIslooksuc() {
		return islooksuc;
	}

	public void setIslooksuc(String islooksuc) {
		this.islooksuc = islooksuc;
	}
	
}