/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.entity.order;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.StringUtils;

/**
 * 用户课程订单Entity
 * @author hm
 * @version 2018-03-12
 */
public class ZcourseOrder extends DataEntity<ZcourseOrder> {
	
	private static final long serialVersionUID = 1L;
	private String courseid;		// 课程编号
	private String userid;		// 用户编号
	private String paytype;		// 支付方式
	private Date paytime;		// 支付时间
	private String payprice;		// 支付数额
	private String paystatus;		// 购买状态
	private String courseprice;		// 购买时价格
	private String payid;		// 支付编号
	private int buynum;			//支付次数
	private String exptime;		//到期时间
	private String shoptype;	//购买类型  2课程购买
	
	private String width;		//课程时间进度
	
	public ZcourseOrder() {
		super();
	}

	public ZcourseOrder(String id){
		super(id);
	}

	
	public String getShoptype() {
		return shoptype;
	}

	public void setShoptype(String shoptype) {
		this.shoptype = shoptype;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	@Length(min=0, max=64, message="课程编号长度必须介于 0 和 64 之间")
	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}
	
	
	
	public String getExptime() {
		return exptime;
	}

	public void setExptime(String exptime) {
		this.exptime = exptime;
	}

	public int getBuynum() {		 
		return buynum;
	}

	public void setBuynum(int buynum) {
		this.buynum = buynum;
	}

	@Length(min=0, max=64, message="用户编号长度必须介于 0 和 64 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Length(min=0, max=20, message="支付方式长度必须介于 0 和 20 之间")
	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPaytime() {
		return paytime;
	}

	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}
	
	@Length(min=0, max=20, message="支付数额长度必须介于 0 和 20 之间")
	public String getPayprice() {
		return payprice;
	}

	public void setPayprice(String payprice) {
		this.payprice = payprice;
	}
	
	@Length(min=0, max=20, message="购买状态长度必须介于 0 和 20 之间")
	public String getPaystatus() {
		return paystatus;
	}

	public void setPaystatus(String paystatus) {
		this.paystatus = paystatus;
	}
	
	@Length(min=0, max=20, message="购买时价格长度必须介于 0 和 20 之间")
	public String getCourseprice() {
		return courseprice;
	}

	public void setCourseprice(String courseprice) {
		this.courseprice = courseprice;
	}
	
	@Length(min=0, max=64, message="支付编号长度必须介于 0 和 64 之间")
	public String getPayid() {
		return payid;
	}

	public void setPayid(String payid) {
		this.payid = payid;
	}
	
}