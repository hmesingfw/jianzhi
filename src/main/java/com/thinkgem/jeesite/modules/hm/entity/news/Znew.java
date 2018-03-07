/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.entity.news;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 新闻资讯Entity
 * @author hm
 * @version 2018-03-07
 */
public class Znew extends DataEntity<Znew> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 标题
	private String img;		// 图片
	private String iscommend;		// 是否推荐
	private String weight;		// 权重
	private String click;		// 点击量
	private String content;		// 内容
	private String introduce;
	
	public Znew() {
		super();
	}

	public Znew(String id){
		super(id);
	}

	
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	@Length(min=0, max=200, message="标题长度必须介于 0 和 200 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=400, message="图片长度必须介于 0 和 400 之间")
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	@Length(min=0, max=10, message="是否推荐长度必须介于 0 和 10 之间")
	public String getIscommend() {
		return iscommend;
	}

	public void setIscommend(String iscommend) {
		this.iscommend = iscommend;
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
	
	@Length(min=0, max=11, message="点击量长度必须介于 0 和 11 之间")
	public String getClick() {
		if(click==null || "".equals(click)){
			click = "0";
		}
		return click;
	}

	public void setClick(String click) {
		this.click = click;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}