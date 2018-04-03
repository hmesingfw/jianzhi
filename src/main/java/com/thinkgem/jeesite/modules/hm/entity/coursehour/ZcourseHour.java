/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.entity.coursehour;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 课时管理Entity
 * @author hm
 * @version 2018-04-03
 */
public class ZcourseHour extends DataEntity<ZcourseHour> {
	
	private static final long serialVersionUID = 1L;
	private String courseid;		// 课程编号
	private String title;		// 标题
	private String url;		// 链接
	
	public ZcourseHour() {
		super();
	}

	public ZcourseHour(String id){
		super(id);
	}

	@Length(min=0, max=64, message="课程编号长度必须介于 0 和 64 之间")
	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}
	
	@Length(min=0, max=200, message="标题长度必须介于 0 和 200 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=3000, message="链接长度必须介于 0 和 3000 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}