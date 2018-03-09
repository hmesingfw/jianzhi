/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.entity.course_sort;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 专业分类Entity
 * @author hm
 * @version 2018-03-09
 */
public class ZcourseSort extends DataEntity<ZcourseSort> {
	
	private static final long serialVersionUID = 1L;
	private String parentId;		// 父级编号
	private String parentIds;		// 所有父级编号
	private String name;		// 名称
	private String sort;		// 排序
	private String code;		// 编码
	private String type;		// 类型
	
	public ZcourseSort() {
		super();
	}

	public ZcourseSort(String id){
		super(id);
	}

	@Length(min=0, max=64, message="父级编号长度必须介于 0 和 64 之间")
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	@Length(min=0, max=2000, message="所有父级编号长度必须介于 0 和 2000 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	@Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSort() {
		if(sort==null || "".equals(sort)){
			sort = "0";
		}
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	@Length(min=0, max=100, message="编码长度必须介于 0 和 100 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=1, message="类型长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
	@JsonIgnore
	public static void sortList(List<ZcourseSort> list, List<ZcourseSort> sourcelist, String parentId, boolean cascade){
		for (int i=0; i<sourcelist.size(); i++){
			ZcourseSort e = sourcelist.get(i);
			if (e.getParentId()!=null && e.getParentId().equals(parentId)){
				list.add(e);
				if (cascade){
					// 判断是否还有子节点, 有则继续获取子节点
					for (int j=0; j<sourcelist.size(); j++){
						ZcourseSort child = sourcelist.get(j);
						if (child.getParentId()!=null && child.getParentId().equals(e.getId())){
							sortList(list, sourcelist, e.getId(), true);
							break;
						}
					}
				}
			}
		}
	}
	
}