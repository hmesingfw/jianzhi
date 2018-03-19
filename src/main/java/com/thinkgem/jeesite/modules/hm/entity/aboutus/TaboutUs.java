/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.entity.aboutus;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 关于我们信息Entity
 * @author hm
 * @version 2018-03-05
 */
public class TaboutUs extends DataEntity<TaboutUs> {
	
	private static final long serialVersionUID = 1L;
	private String content;		// 内容
	private String file;		// 文件路径
	private String dir;		// 目录
	
	public TaboutUs() {
		super();
	}

	public TaboutUs(String id){
		super(id);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=2000, message="文件路径长度必须介于 0 和 2000 之间")
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	@Length(min=0, max=200, message="目录长度必须介于 0 和 200 之间")
	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}
	
}