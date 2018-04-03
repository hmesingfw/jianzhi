/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.entity.test_question;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 试卷题目内容管理Entity
 * @author hm
 * @version 2018-03-14
 */
public class ZtestQuestion extends DataEntity<ZtestQuestion> {
	
	private static final long serialVersionUID = 1L;
	private String testid;		// 试卷编号
	private String question;		// 题库编号
	private String fraction;		// 设置分数
	private String type;			//当前题目的类型
	
	public ZtestQuestion() {
		super();
	}

	public ZtestQuestion(String id){
		super(id);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Length(min=0, max=64, message="试卷编号长度必须介于 0 和 64 之间")
	public String getTestid() {
		return testid;
	}

	public void setTestid(String testid) {
		this.testid = testid;
	}
	
	@Length(min=0, max=64, message="题库编号长度必须介于 0 和 64 之间")
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	@Length(min=0, max=20, message="设置分数长度必须介于 0 和 20 之间")
	public String getFraction() {
		return fraction;
	}

	public void setFraction(String fraction) {
		this.fraction = fraction;
	}
	
}