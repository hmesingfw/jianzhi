/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.entity.doc;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 文档中心Entity
 * @author hm
 * @version 2018-03-08
 */
public class Zdoc extends DataEntity<Zdoc> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 标题
	private String introduce;		// 介绍
	private String files;		// 文件路径
	private String filetype;		// 文件类型
	private String usertype;		// 用户类型
	private String sortid;		// 所属分类
	private String weight;		// 权重
	private String keyword;		// 关键字
	private String iscom;		// 是否推荐
	
	private String look;		//浏览量
	private String down;		//点击量
	
	private List<String> sortlist;
	public Zdoc() {
		super();
	}

	public Zdoc(String id){
		super(id);
	}

	@Length(min=0, max=200, message="标题长度必须介于 0 和 200 之间")
	public String getTitle() {
		return title;
	}

	public List<String> getSortlist() {
		return sortlist;
	}
	
	

	public String getLook() {
		if(look==null || "".equals(look)){
			look = "0";
		}
		return look;
	}

	public void setLook(String look) {
		this.look = look;
	}

	public String getDown() {
		if(down==null || "".equals(down)){
			down = "0";
		}
		return down;
	}

	public void setDown(String down) {
		this.down = down;
	}
	
	

	public void setSortlist(List<String> sortlist) {
		this.sortlist = sortlist;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=2000, message="介绍长度必须介于 0 和 2000 之间")
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	@Length(min=0, max=1000, message="文件路径长度必须介于 0 和 1000 之间")
	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}
	
	@Length(min=0, max=10, message="文件类型长度必须介于 0 和 10 之间")
	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	
	@Length(min=0, max=10, message="用户类型长度必须介于 0 和 10 之间")
	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	
	@Length(min=0, max=64, message="所属分类长度必须介于 0 和 64 之间")
	public String getSortid() {
		return sortid;
	}

	public void setSortid(String sortid) {
		this.sortid = sortid;
	}
	
	@Length(min=0, max=11, message="权重长度必须介于 0 和 11 之间")
	public String getWeight() {
		if(weight==null || "".equals(weight)){
			weight = "0";
		}
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	@Length(min=0, max=200, message="关键字长度必须介于 0 和 200 之间")
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Length(min=0, max=20, message="是否推荐长度必须介于 0 和 20 之间")
	public String getIscom() {
		return iscom;
	}

	public void setIscom(String iscom) {
		this.iscom = iscom;
	}
	
}