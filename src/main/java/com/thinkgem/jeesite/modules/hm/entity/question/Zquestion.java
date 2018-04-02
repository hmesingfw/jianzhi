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
	
	
	
	//导入题目时的答案选项
	private String answer1;	
	private String answer2;	
	private String answer3;	
	private String answer4;	
	private String answer5;	
	private String answer6;	
	private String answer7;	
	private String answer8;	
	private String answer9;	
	private String answer10;	
	private String answer11;	
	private String answer12;	
	
	
	private List<String> sortlist;	//前台点击分类
	
	public Zquestion() {
		super();
	}

	public Zquestion(String id){
		super(id);
	}
	
	

	public List<String> getSortlist() {
		return sortlist;
	}

	public void setSortlist(List<String> sortlist) {
		this.sortlist = sortlist;
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

	
	@ExcelField(title="选项1", align=2, sort=80)
	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	
	@ExcelField(title="选项2", align=2, sort=90)
	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	@ExcelField(title="选项3", align=2, sort=100)
	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}
	
	@ExcelField(title="选项4", align=2, sort=110)
	public String getAnswer4() {
		return answer4;
	}

	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}

	@ExcelField(title="选项5", align=2, sort=120)
	public String getAnswer5() {
		return answer5;
	}

	public void setAnswer5(String answer5) {
		this.answer5 = answer5;
	}

	@ExcelField(title="选项6", align=2, sort=130)
	public String getAnswer6() {
		return answer6;
	}

	public void setAnswer6(String answer6) {
		this.answer6 = answer6;
	}

	@ExcelField(title="选项7", align=2, sort=140)
	public String getAnswer7() {
		return answer7;
	}

	public void setAnswer7(String answer7) {
		this.answer7 = answer7;
	}

	@ExcelField(title="选项8", align=2, sort=150)
	public String getAnswer8() {
		return answer8;
	}

	public void setAnswer8(String answer8) {
		this.answer8 = answer8;
	}

	@ExcelField(title="选项9", align=2, sort=160)
	public String getAnswer9() {
		return answer9;
	}

	public void setAnswer9(String answer9) {
		this.answer9 = answer9;
	}

	@ExcelField(title="选项10", align=2, sort=170)
	public String getAnswer10() {
		return answer10;
	}

	public void setAnswer10(String answer10) {
		this.answer10 = answer10;
	}

	@ExcelField(title="选项11", align=2, sort=180)
	public String getAnswer11() {
		return answer11;
	}

	public void setAnswer11(String answer11) {
		this.answer11 = answer11;
	}

	@ExcelField(title="选项12", align=2, sort=190)
	public String getAnswer12() {
		return answer12;
	}

	public void setAnswer12(String answer12) {
		this.answer12 = answer12;
	}
	
	
	
	
	
	
	
}