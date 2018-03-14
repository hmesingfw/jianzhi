/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.entity.question;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.hm.entity.question_answer.ZquestionAnswer;

/**
 * 试题管理Entity
 * @author hm
 * @version 2018-03-14
 */
public class Zquestion extends DataEntity<Zquestion> {
	
	private static final long serialVersionUID = 1L;
	private String parentid;		// 所属分类
	private String title;		// 标题
	private String type;		// 类型
	private String defaultFraction;		// 默认分数
	private String analytical;		// 试题解析
	
	
	private String answer;		// 答案内容
	private String isCorrect;	// 是否为答案
	
	private int limit;			//随机数
	private List<ZquestionAnswer> ZquestionAnswer;
	
	public Zquestion() {
		super();
	}

	public Zquestion(String id){
		super(id);
	}
	
	

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public List<ZquestionAnswer> getZquestionAnswer() {
		return ZquestionAnswer;
	}

	public void setZquestionAnswer(List<ZquestionAnswer> zquestionAnswer) {
		ZquestionAnswer = zquestionAnswer;
	}
	
	
	@Length(min=0, max=64, message="所属分类长度必须介于 0 和 64 之间")
	@ExcelField(title="所属专业", align=2, sort=40)
	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	
	@Length(min=0, max=3000, message="标题长度必须介于 0 和 3000 之间")
	@ExcelField(title="题目标题", align=2, sort=10)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=20, message="类型长度必须介于 0 和 20 之间")
	@ExcelField(title="题目类型", align=2, sort=20)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@ExcelField(title="默认分类", align=2, sort=50)
	public String getDefaultFraction() {
		if(defaultFraction==null || "".equals(defaultFraction)){
			defaultFraction = "2";
		}
		return defaultFraction;
	}

	public void setDefaultFraction(String defaultFraction) {
		this.defaultFraction = defaultFraction;
	}
	
	@Length(min=0, max=3000, message="试题解析长度必须介于 0 和 3000 之间")
	@ExcelField(title="题目解析", align=2, sort=30)
	public String getAnalytical() {
		return analytical;
	}

	public void setAnalytical(String analytical) {
		this.analytical = analytical;
	}
	
	@ExcelField(title="答案", align=2, sort=60)
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	@ExcelField(title="是否正确", align=2, sort=70)
	public String getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(String isCorrect) {
		this.isCorrect = isCorrect;
	}
	
	
	
}