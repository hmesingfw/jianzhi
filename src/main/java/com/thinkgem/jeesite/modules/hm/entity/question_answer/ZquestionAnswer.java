/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.entity.question_answer;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 试题答案管理Entity
 * @author hm
 * @version 2018-03-14
 */
public class ZquestionAnswer extends DataEntity<ZquestionAnswer> {
	
	private static final long serialVersionUID = 1L;
	private String quesId;		// 试题编号
	private String answer;		// 答案内容
	private String seqnum;		// 排序，权重
	private String isCorrect;		// 是否为答案
	
	public ZquestionAnswer() {
		super();
	}

	public ZquestionAnswer(String id){
		super(id);
	}

	@Length(min=0, max=64, message="试题编号长度必须介于 0 和 64 之间")
	public String getQuesId() {
		return quesId;
	}

	public void setQuesId(String quesId) {
		this.quesId = quesId;
	}
	
	@Length(min=0, max=3000, message="答案内容长度必须介于 0 和 3000 之间")
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	@Length(min=0, max=11, message="排序，权重长度必须介于 0 和 11 之间")
	public String getSeqnum() {
		return seqnum;
	}

	public void setSeqnum(String seqnum) {
		this.seqnum = seqnum;
	}
	
	@Length(min=0, max=1, message="是否为答案长度必须介于 0 和 1 之间")
	public String getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(String isCorrect) {
		this.isCorrect = isCorrect;
	}
	
}