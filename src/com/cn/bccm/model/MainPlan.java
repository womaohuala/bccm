package com.cn.bccm.model;

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
 * MainPlan entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "main_plan", catalog = "bccm")
public class MainPlan implements java.io.Serializable {

	// Fields

	private Integer planId;
	private String planName;
	private String planInfo;
	private MainEmployee employee;
	private CoopProject project;
	private Integer planPrime;
	private Integer planCost;
	private Integer planSale;
	private Date planStartTime;
	private Date planEndTime;
	private Date planCreateTime;
	private Integer planCreateEmp;
	private String planRemark;
	private Set<MainPlanExec> execs = new HashSet<MainPlanExec>();

	// Constructors

	/** default constructor */
	public MainPlan() {
	}

	/** full constructor */
	public MainPlan(String planName, String planInfo, MainEmployee employee,
			CoopProject project, Integer planPrime, Integer planCost,
			Integer planSale, Date planStartTime, Date planEndTime,
			Date planCreateTime, Integer planCreateEmp) {
		this.planName = planName;
		this.planInfo = planInfo;
		this.employee = employee;
		this.project = project;
		this.planPrime = planPrime;
		this.planCost = planCost;
		this.planSale = planSale;
		this.planStartTime = planStartTime;
		this.planEndTime = planEndTime;
		this.planCreateTime = planCreateTime;
		this.planCreateEmp = planCreateEmp;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "plan_id", unique = true, nullable = false)
	public Integer getPlanId() {
		return this.planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	@Column(name = "plan_name", nullable = false, length = 50)
	public String getPlanName() {
		return this.planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	@Column(name = "plan_info", nullable = false, length = 1000)
	public String getPlanInfo() {
		return this.planInfo;
	}

	public void setPlanInfo(String planInfo) {
		this.planInfo = planInfo;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="plan_emp_id")
	public MainEmployee getEmployee() {
		return employee;
	}

	public void setEmployee(MainEmployee employee) {
		this.employee = employee;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="coop_pro_id")
	public CoopProject getProject() {
		return project;
	}

	public void setProject(CoopProject project) {
		this.project = project;
	}

	@Column(name = "plan_prime", nullable = false)
	public Integer getPlanPrime() {
		return this.planPrime;
	}

	public void setPlanPrime(Integer planPrime) {
		this.planPrime = planPrime;
	}

	@Column(name = "plan_cost", nullable = false)
	public Integer getPlanCost() {
		return this.planCost;
	}

	public void setPlanCost(Integer planCost) {
		this.planCost = planCost;
	}

	@Column(name = "plan_sale", nullable = false)
	public Integer getPlanSale() {
		return this.planSale;
	}

	public void setPlanSale(Integer planSale) {
		this.planSale = planSale;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "plan_start_time", nullable = false, length = 0)
	public Date getPlanStartTime() {
		return this.planStartTime;
	}

	public void setPlanStartTime(Date planStartTime) {
		this.planStartTime = planStartTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "plan_end_time", nullable = false, length = 0)
	public Date getPlanEndTime() {
		return this.planEndTime;
	}

	public void setPlanEndTime(Date planEndTime) {
		this.planEndTime = planEndTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "plan_create_time", nullable = false, length = 0)
	public Date getPlanCreateTime() {
		return this.planCreateTime;
	}

	public void setPlanCreateTime(Date planCreateTime) {
		this.planCreateTime = planCreateTime;
	}

	@Column(name = "plan_create_emp", nullable = false)
	public Integer getPlanCreateEmp() {
		return this.planCreateEmp;
	}

	public void setPlanCreateEmp(Integer planCreateEmp) {
		this.planCreateEmp = planCreateEmp;
	}
	
	
	@Column(name = "plan_remark", length = 5000)
	public String getPlanRemark() {
		return planRemark;
	}

	public void setPlanRemark(String planRemark) {
		this.planRemark = planRemark;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="plan")
	public Set<MainPlanExec> getExecs() {
		return execs;
	}

	public void setExecs(Set<MainPlanExec> execs) {
		this.execs = execs;
	}
	
	

}