package com.cn.bccm.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CoopProject entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "coop_project", catalog = "bccm")
public class CoopProject implements java.io.Serializable {

	// Fields

	private Integer proId;
	private String proName;
	private String proInfo;
	private String proHead;
	private String proPhone;
	private String remark;
	private Date beginTime;
	private Date endTime;
	private CoopCompany company;
	private Set<MainPlan> plans = new HashSet<MainPlan>();

	// Constructors

	/** default constructor */
	public CoopProject() {
	}

	/** minimal constructor */
	public CoopProject(String coopCompId, String proName, String proInfo,
			String proHead, String proPhone,CoopCompany company) {
		this.proName = proName;
		this.proInfo = proInfo;
		this.proHead = proHead;
		this.proPhone = proPhone;
		this.company = company;
	}

	/** full constructor */
	public CoopProject(String coopCompId, String proName, String proInfo,
			String proHead, String proPhone, String remark,
			Date beginTime, Date endTime,CoopCompany company) {
		this.proName = proName;
		this.proInfo = proInfo;
		this.proHead = proHead;
		this.proPhone = proPhone;
		this.remark = remark;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.company = company;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "pro_id", unique = true, nullable = false)
	public Integer getProId() {
		return this.proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}


	@Column(name = "pro_name", nullable = false, length = 50)
	public String getProName() {
		return this.proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	@Column(name = "pro_info", nullable = false, length = 1000)
	public String getProInfo() {
		return this.proInfo;
	}

	public void setProInfo(String proInfo) {
		this.proInfo = proInfo;
	}

	@Column(name = "pro_head", nullable = false, length = 20)
	public String getProHead() {
		return this.proHead;
	}

	public void setProHead(String proHead) {
		this.proHead = proHead;
	}

	@Column(name = "pro_phone", nullable = false, length = 20)
	public String getProPhone() {
		return this.proPhone;
	}

	public void setProPhone(String proPhone) {
		this.proPhone = proPhone;
	}

	@Column(name = "remark", length = 8000)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "begin_time", length = 0)
	public Date getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_time", length = 0)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="coop_comp_id")
	public CoopCompany getCompany() {
		return company;
	}

	public void setCompany(CoopCompany company) {
		this.company = company;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="project")
	public Set<MainPlan> getPlans() {
		return plans;
	}

	public void setPlans(Set<MainPlan> plans) {
		this.plans = plans;
	}
	
	

	
}