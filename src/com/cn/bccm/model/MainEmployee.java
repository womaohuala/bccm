package com.cn.bccm.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * MainEmployee entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "main_employee", catalog = "bccm")
public class MainEmployee implements java.io.Serializable {

	// Fields

	private Integer empId;
	private String empName;
	private String empJob;
	private Short empSex;
	private String empPhone;
	private String empRemark;
	private String empUserName;
	private String empPassword;
	private MainDepartment department;
	
	private Set<MainPlan> plans = new HashSet<MainPlan>();
	
	private Set<Leave> leaves;
	
	

	// Constructors

	/** default constructor */
	public MainEmployee() {
	}

	/** minimal constructor */
	public MainEmployee(Integer empId,  String empName,
			String empJob, Short empSex, String empPhone,
			String empUserName, String empPassword) {
		this.empId = empId;
		this.empName = empName;
		this.empJob = empJob;
		this.empSex = empSex;
		this.empPhone = empPhone;
		this.empUserName = empUserName;
		this.empPassword = empPassword;
	}


	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "emp_id", unique = true, nullable = false)
	public Integer getEmpId() {
		return this.empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}


	@Column(name = "emp_name", nullable = false, length = 20)
	public String getempName() {
		return this.empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	@Column(name = "emp_job", nullable = false, length = 50)
	public String getEmpJob() {
		return this.empJob;
	}

	public void setEmpJob(String empJob) {
		this.empJob = empJob;
	}

	@Column(name = "emp_sex", nullable = false)
	public Short getEmpSex() {
		return this.empSex;
	}

	public void setEmpSex(Short empSex) {
		this.empSex = empSex;
	}

	@Column(name = "emp_phone", nullable = false, length = 20)
	public String getEmpPhone() {
		return this.empPhone;
	}

	public void setEmpPhone(String EmpPhone) {
		this.empPhone = EmpPhone;
	}

	@Column(name = "emp_remark", length = 8000)
	public String getEmpRemark() {
		return this.empRemark;
	}

	public void setEmpRemark(String empRemark) {
		this.empRemark = empRemark;
	}

	@Column(name = "emp_user_name", nullable = false, length = 50)
	public String getEmpUserName() {
		return this.empUserName;
	}

	public void setEmpUserName(String empUserName) {
		this.empUserName = empUserName;
	}

	@Column(name = "emp_password", nullable = false, length = 50)
	public String getEmpPassword() {
		return this.empPassword;
	}

	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="dept_id")
	public MainDepartment getDepartment() {
		return department;
	}

	public void setDepartment(MainDepartment department) {
		this.department = department;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="employee")
	public Set<MainPlan> getPlans() {
		return plans;
	}

	public void setPlans(Set<MainPlan> plans) {
		this.plans = plans;
	}
	
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="employee")
	public Set<Leave> getLeaves() {
		return this.leaves;
	}

	public void setLeaves(Set<Leave> leaves) {
		this.leaves = leaves;
	}
	

	
}