/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hm.entity.user;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 用户信息Entity
 * @author hm
 * @version 2018-03-09
 */
public class Zuser extends DataEntity<Zuser> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String img;		// 头像
	private String password;		// 密码
	private String title;		// 标题
	private String major;		// 专业
	private String idcode;		// 身份证
	private String truename;		// 真实姓名
	private String sex;		// 性别
	private String age;		// 出生年月
	private String ethnic;		// 民族
	private String xmajor;		// 所学专业
	private String education;		// 学历
	private String employer;		// 工作单位
	private String worklength;		// 工作年限
	private String adress;		// 地址
	private String adresscode;		// 邮政编码
	private String phone;		// 联系电话
	private String email;		// 邮箱
	private String type;		// 用户类型
	private String status;		// 用户状态
	
	public Zuser() {
		super();
	}

	public Zuser(String id){
		super(id);
	}

	@Length(min=0, max=200, message="名称长度必须介于 0 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=400, message="头像长度必须介于 0 和 400 之间")
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	@Length(min=0, max=200, message="密码长度必须介于 0 和 200 之间")
	@ExcelField(title="用户状态", align=2, sort=170)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Length(min=0, max=200, message="标题长度必须介于 0 和 200 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=64, message="专业长度必须介于 0 和 64 之间")
	@ExcelField(title="所属专业", align=2, sort=10)
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
	@Length(min=0, max=18, message="身份证长度必须介于 0 和 18 之间")
	@ExcelField(title="身份证号码", align=2, sort=20)
	public String getIdcode() {
		return idcode;
	}

	public void setIdcode(String idcode) {
		this.idcode = idcode;
	}
	
	@Length(min=0, max=100, message="真实姓名长度必须介于 0 和 100 之间")
	@ExcelField(title="真实姓名", align=2, sort=30)
	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}
	
	@Length(min=0, max=10, message="性别长度必须介于 0 和 10 之间")
	@ExcelField(title="性别", align=2, sort=40)
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=100, message="出生年月长度必须介于 0 和 100 之间")
	@ExcelField(title="出生年月", align=2, sort=50)
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	@Length(min=0, max=10, message="民族长度必须介于 0 和 10 之间")
	@ExcelField(title="民族", align=2, sort=60)
	public String getEthnic() {
		return ethnic;
	}

	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}
	
	@Length(min=0, max=300, message="所学专业长度必须介于 0 和 300 之间")
	@ExcelField(title="所学专业", align=2, sort=70)
	public String getXmajor() {
		return xmajor;
	}

	public void setXmajor(String xmajor) {
		this.xmajor = xmajor;
	}
	
	@Length(min=0, max=20, message="学历长度必须介于 0 和 20 之间")
	@ExcelField(title="学历", align=2, sort=80)
	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}
	
	@Length(min=0, max=300, message="工作单位长度必须介于 0 和 300 之间")
	@ExcelField(title="工作单位", align=2, sort=90)
	public String getEmployer() {
		return employer;
	}

	public void setEmployer(String employer) {
		this.employer = employer;
	}
	
	@Length(min=0, max=20, message="工作年限长度必须介于 0 和 20 之间")
	@ExcelField(title="工作年限", align=2, sort=100)
	public String getWorklength() {
		return worklength;
	}

	public void setWorklength(String worklength) {
		this.worklength = worklength;
	}
	
	@Length(min=0, max=200, message="地址长度必须介于 0 和 200 之间")
	@ExcelField(title="地址", align=2, sort=110)
	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	@Length(min=0, max=20, message="邮政编码长度必须介于 0 和 20 之间")
	@ExcelField(title="邮政编号", align=2, sort=120)
	public String getAdresscode() {
		return adresscode;
	}

	public void setAdresscode(String adresscode) {
		this.adresscode = adresscode;
	}
	
	@Length(min=0, max=20, message="联系电话长度必须介于 0 和 20 之间")
	@ExcelField(title="联系电话", align=2, sort=130)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=50, message="邮箱长度必须介于 0 和 50 之间")
	@ExcelField(title="邮箱", align=2, sort=140)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=20, message="用户类型长度必须介于 0 和 20 之间")
	@ExcelField(title="用户类型", align=2, sort=150)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=20, message="用户状态长度必须介于 0 和 20 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}