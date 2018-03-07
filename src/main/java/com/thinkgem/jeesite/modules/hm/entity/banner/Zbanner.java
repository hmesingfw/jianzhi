/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.entity.banner;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 轮播图Entity
 * @author hm
 * @version 2018-03-07
 */
public class Zbanner extends DataEntity<Zbanner> {
	
	private static final long serialVersionUID = 1L;
	private String url;		// 链接
	private String filepath;		// 文件路径
	private String weight;		// 权重
	private String type;
	private String name;
	
	
	public Zbanner() {
		super();
	}

	public Zbanner(String id){
		super(id);
	}

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Length(min=0, max=300, message="链接长度必须介于 0 和 300 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Length(min=0, max=2000, message="文件路径长度必须介于 0 和 2000 之间")
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	@Length(min=0, max=10, message="权重长度必须介于 0 和 10 之间")
	public String getWeight() {
		if(weight==null || "".equals(weight)){
			weight = "0";
		}
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}
	
}