/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.entity.test;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 试卷管理Entity
 * @author hm
 * @version 2018-03-14
 */
public class Ztest extends DataEntity<Ztest> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 试题卷名称
	private String type;		// 试题卷类型
	private String parentid;		// 所属分类
	private String sum;		// 题目数量
	private String testtype;		// 测试题目类型
	private String fraction;		// 总分数
	private String testtime;		//测试时长
	
	private List<String> sortlist;	
	public Ztest() {
		super();
	}

	public Ztest(String id){
		super(id);
	}
	
	

	public String getTesttime() {
		return testtime;
	}

	public void setTesttime(String testtime) {
		this.testtime = testtime;
	}

	public List<String> getSortlist() {
		return sortlist;
	}

	public void setSortlist(List<String> sortlist) {
		this.sortlist = sortlist;
	}

	@Length(min=0, max=3000, message="试题卷名称长度必须介于 0 和 3000 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=20, message="试题卷类型长度必须介于 0 和 20 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=64, message="所属分类长度必须介于 0 和 64 之间")
	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	
	@Length(min=0, max=20, message="题目数量长度必须介于 0 和 20 之间")
	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}
	
	@Length(min=0, max=20, message="测试题目类型长度必须介于 0 和 20 之间")
	public String getTesttype() {
		return testtype;
	}

	public void setTesttype(String testtype) {
		this.testtype = testtype;
	}
	
	@Length(min=0, max=20, message="总分数长度必须介于 0 和 20 之间")
	public String getFraction() {
		return fraction;
	}

	public void setFraction(String fraction) {
		this.fraction = fraction;
	}
	
}