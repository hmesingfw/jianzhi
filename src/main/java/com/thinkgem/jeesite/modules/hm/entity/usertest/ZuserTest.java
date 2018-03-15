/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.entity.usertest;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 用户答题记录Entity
 * @author hm
 * @version 2018-03-15
 */
public class ZuserTest extends DataEntity<ZuserTest> {
	
	private static final long serialVersionUID = 1L;
	private String testid;		// 试卷编号
	private String questionid;		// 当前题目编号
	private String answerid;		// 选择答案项
	private String isselected;		// 是否已选
	private String istrue;		// 是否答对
	private String isclick;		// waile
	private String userid;		//用户编号
	private int sort;			//排序
	public ZuserTest() {
		super();
	}

	public ZuserTest(String id){
		super(id);
	}
	
	

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Length(min=0, max=64, message="试卷编号长度必须介于 0 和 64 之间")
	public String getTestid() {
		return testid;
	}

	public void setTestid(String testid) {
		this.testid = testid;
	}
	
	@Length(min=0, max=64, message="当前题目编号长度必须介于 0 和 64 之间")
	public String getQuestionid() {
		return questionid;
	}

	public void setQuestionid(String questionid) {
		this.questionid = questionid;
	}
	
	@Length(min=0, max=640, message="选择答案项长度必须介于 0 和 640 之间")
	public String getAnswerid() {
		return answerid;
	}

	public void setAnswerid(String answerid) {
		this.answerid = answerid;
	}
	
	@Length(min=0, max=20, message="是否已选长度必须介于 0 和 20 之间")
	public String getIsselected() {
		return isselected;
	}

	public void setIsselected(String isselected) {
		this.isselected = isselected;
	}
	
	@Length(min=0, max=20, message="是否答对长度必须介于 0 和 20 之间")
	public String getIstrue() {
		return istrue;
	}

	public void setIstrue(String istrue) {
		this.istrue = istrue;
	}
	
	@Length(min=0, max=255, message="waile长度必须介于 0 和 255 之间")
	public String getIsclick() {
		return isclick;
	}

	public void setIsclick(String isclick) {
		this.isclick = isclick;
	}
	
}