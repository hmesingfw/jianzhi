/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.entity.testrandom;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 随机测试题Entity
 * @author hm
 * @version 2018-04-03
 */
public class ZtestRandom extends DataEntity<ZtestRandom> {
	
	private static final long serialVersionUID = 1L;
	private String parentid;		// 专业编号
	private String radio;		// 单选题
	private String checkbox;		// 多选题
	private String judge;		// 判断题
	
	public ZtestRandom() {
		super();
	}

	public ZtestRandom(String id){
		super(id);
	}

	@Length(min=0, max=64, message="专业编号长度必须介于 0 和 64 之间")
	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	
	@Length(min=0, max=20, message="单选题长度必须介于 0 和 20 之间")
	public String getRadio() {
		return radio;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}
	
	@Length(min=0, max=20, message="多选题长度必须介于 0 和 20 之间")
	public String getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(String checkbox) {
		this.checkbox = checkbox;
	}
	
	@Length(min=0, max=20, message="判断题长度必须介于 0 和 20 之间")
	public String getJudge() {
		return judge;
	}

	public void setJudge(String judge) {
		this.judge = judge;
	}
	
}