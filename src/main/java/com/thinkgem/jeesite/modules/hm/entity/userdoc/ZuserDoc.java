/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.entity.userdoc;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 用户文档下载记录Entity
 * @author hm
 * @version 2018-03-10
 */
public class ZuserDoc extends DataEntity<ZuserDoc> {
	
	private static final long serialVersionUID = 1L;
	private String userid;		// 用户编号
	private String docid;		// 文档编号
	
	public ZuserDoc() {
		super();
	}

	public ZuserDoc(String id){
		super(id);
	}

	@Length(min=0, max=64, message="用户编号长度必须介于 0 和 64 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Length(min=0, max=64, message="文档编号长度必须介于 0 和 64 之间")
	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}
	
}