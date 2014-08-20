package com.cn.bccm.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * MainEmployeeRole entity. 
 * @author hux
 */


@Entity
@Table(name = "main_employee_role", catalog = "bccm")
public class MainEmployeeRole implements java.io.Serializable {

	// Fields

	private Integer emproleId;
	
	private MainEmployee employee;
	private MainRole role;

	// Constructors

	/** default constructor */
	public MainEmployeeRole() {
	}

	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "emp_role_id", unique = true, nullable = false)
	public Integer getEmproleId() {
		return emproleId;
	}
	
	
	public void setEmproleId(Integer emproleId) {
		this.emproleId = emproleId;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="emp_id")
	public MainEmployee getEmployee() {
		return employee;
	}

	public void setEmployee(MainEmployee employee) {
		this.employee = employee;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="role_id")
	public MainRole getRole() {
		return role;
	}


	public void setRole(MainRole role) {
		this.role = role;
	}

}