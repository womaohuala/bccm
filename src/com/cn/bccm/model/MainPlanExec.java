package com.cn.bccm.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MainPlanExec entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "main_plan_exec", catalog = "bccm")
public class MainPlanExec implements java.io.Serializable {

	// Fields

	private Integer execId;
	private String execName;
	private String execInfo;
	private MainPlan plan;
	private Integer execPrime;
	private Integer execCost;
	private Integer execSale;
	private Date execStartTime;
	private Date execEndTime;
	private Date execCreateTime;
	private Integer execCreateEmp;
	private String execRemark;
	
	

	// Constructors

	/** default constructor */
	public MainPlanExec() {
	}

	/** full constructor */
	public MainPlanExec(String execName, String execInfo, MainPlan plan,
			Integer execPrime, Integer execCost, Integer execSale,
			Date execStartTime, Date execEndTime,
			Date execCreateTime, Integer execCreateEmp) {
		this.execName = execName;
		this.execInfo = execInfo;
		this.plan = plan;
		this.execPrime = execPrime;
		this.execCost = execCost;
		this.execSale = execSale;
		this.execStartTime = execStartTime;
		this.execEndTime = execEndTime;
		this.execCreateTime = execCreateTime;
		this.execCreateEmp = execCreateEmp;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "exec_id", unique = true, nullable = false)
	public Integer getExecId() {
		return this.execId;
	}

	public void setExecId(Integer execId) {
		this.execId = execId;
	}

	@Column(name = "exec_name", nullable = false, length = 50)
	public String getExecName() {
		return this.execName;
	}

	public void setExecName(String execName) {
		this.execName = execName;
	}

	@Column(name = "exec_info", nullable = false, length = 1000)
	public String getExecInfo() {
		return this.execInfo;
	}

	public void setExecInfo(String execInfo) {
		this.execInfo = execInfo;
	}

	
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="plan_id")
	public MainPlan getPlan() {
		return plan;
	}

	public void setPlan(MainPlan plan) {
		this.plan = plan;
	}

	@Column(name = "exec_prime", nullable = false)
	public Integer getExecPrime() {
		return this.execPrime;
	}

	public void setExecPrime(Integer execPrime) {
		this.execPrime = execPrime;
	}

	@Column(name = "exec_cost", nullable = false)
	public Integer getExecCost() {
		return this.execCost;
	}

	public void setExecCost(Integer execCost) {
		this.execCost = execCost;
	}

	@Column(name = "exec_sale", nullable = false)
	public Integer getExecSale() {
		return this.execSale;
	}

	public void setExecSale(Integer execSale) {
		this.execSale = execSale;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "exec_start_time", nullable = false, length = 0)
	public Date getExecStartTime() {
		return this.execStartTime;
	}

	public void setExecStartTime(Date execStartTime) {
		this.execStartTime = execStartTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "exec_end_time", nullable = false, length = 0)
	public Date getExecEndTime() {
		return this.execEndTime;
	}

	public void setExecEndTime(Date execEndTime) {
		this.execEndTime = execEndTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "exec_create_time", nullable = false, length = 0)
	public Date getExecCreateTime() {
		return this.execCreateTime;
	}

	public void setExecCreateTime(Date execCreateTime) {
		this.execCreateTime = execCreateTime;
	}

	@Column(name = "exec_create_emp", nullable = false)
	public Integer getExecCreateEmp() {
		return this.execCreateEmp;
	}

	public void setExecCreateEmp(Integer execCreateEmp) {
		this.execCreateEmp = execCreateEmp;
	}

	
	@Column(name = "exec_remark",  length = 5000)
	public String getExecRemark() {
		return execRemark;
	}

	public void setExecRemark(String execRemark) {
		this.execRemark = execRemark;
	}
	
	

}