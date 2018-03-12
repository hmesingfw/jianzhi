/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.entity.course;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 课程信息管理Entity
 * @author hm
 * @version 2018-03-12
 */
public class Zcourse extends DataEntity<Zcourse> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 标题
	private String introduce;		// 介绍
	private String parentid;		// 所属分类
	private String img;		// 课程截图
	private String teacher;		// 老师名称
	private String type;		// 类型
	private String sale;		// 特价
	private String price;		// 价格
	private String files;		// 文件地址
	private String weight;		// 权重
	private String iscommend;	// 是否推荐
	
	
	private List<String> sortlist;
	
	public Zcourse() {
		super();
	}

	public Zcourse(String id){
		super(id);
	}
	
	

	public List<String> getSortlist() {
		return sortlist;
	}

	public void setSortlist(List<String> sortlist) {
		this.sortlist = sortlist;
	}

	public String getIscommend() {
		return iscommend;
	}

	public void setIscommend(String iscommend) {
		this.iscommend = iscommend;
	}

	@Length(min=0, max=200, message="标题长度必须介于 0 和 200 之间")
	public String getTitle() {
		return title;
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
	
	@Length(min=0, max=64, message="所属分类长度必须介于 0 和 64 之间")
	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	
	@Length(min=0, max=400, message="课程截图长度必须介于 0 和 400 之间")
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	@Length(min=0, max=100, message="老师名称长度必须介于 0 和 100 之间")
	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	
	@Length(min=0, max=20, message="类型长度必须介于 0 和 20 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=20, message="特价长度必须介于 0 和 20 之间")
	public String getSale() {
		if(sale==null || "".equals(sale)){
			sale = "0.00";
		}
		return sale;
	}

	public void setSale(String sale) {
		this.sale = sale;
	}
	
	@Length(min=0, max=20, message="价格长度必须介于 0 和 20 之间")
	public String getPrice() {
		if(price==null || "".equals(price)){
			price = "0.00";
		}
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	@Length(min=0, max=500, message="文件地址长度必须介于 0 和 500 之间")
	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
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
	
}