package com.cn.bccm.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MainDepartment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "main_department", catalog = "bccm")
public class MainDepartment implements java.io.Serializable {

	// Fields

	private Integer deptId;
	private String deptName;
	private String deptInfo;
	private String deptHead;
	private String deptPhone;
	private Set<MainEmployee> employees;

	// Constructors

	/** default constructor */
	public MainDepartment() {
	}

	/** full constructor */
	public MainDepartment(String deptName, String deptInfo, String deptHead,
			String deptPhone) {
		this.deptName = deptName;
		this.deptInfo = deptInfo;
		this.deptHead = deptHead;
		this.deptPhone = deptPhone;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "dept_id", unique = true, nullable = false)
	public Integer getDeptId() {
		return this.deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	@Column(name = "dept_name", nullable = false, length = 50)
	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name = "dept_info", nullable = false, length = 1000)
	public String getDeptInfo() {
		return this.deptInfo;
	}

	public void setDeptInfo(String deptInfo) {
		this.deptInfo = deptInfo;
	}

	@Column(name = "dept_head", nullable = false, length = 20)
	public String getDeptHead() {
		return this.deptHead;
	}

	public void setDeptHead(String deptHead) {
		this.deptHead = deptHead;
	}

	@Column(name = "dept_phone", nullable = false, length = 100)
	public String getDeptPhone() {
		return this.deptPhone;
	}

	public void setDeptPhone(String deptPhone) {
		this.deptPhone = deptPhone;
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="department")
	public Set<MainEmployee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<MainEmployee> employees) {
		this.employees = employees;
	}
	
	

}