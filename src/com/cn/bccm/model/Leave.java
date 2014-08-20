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
 * Leave entity. @author MyEclipse Persistence Tools
 */


@Entity
@Table(name = "leaves", catalog = "bccm")
public class Leave implements java.io.Serializable {

	// Fields

	private Integer leaveId;
	
	//private Staff staff;
	private String leaveLong;
	private String leaveContent;
	private String leaveState;
	private String leaveInstanceId;
	
	private MainEmployee employee;

	// Constructors

	/** default constructor */
	public Leave() {
	}

	/** full constructor */
	public Leave(MainEmployee employee, String leaveLong, String leaveContent,
			String leaveState, String leaveInstanceId) {
		//this.staff = staff;
		this.employee = employee;
		this.leaveLong = leaveLong;
		this.leaveContent = leaveContent;
		this.leaveState = leaveState;
		this.leaveInstanceId = leaveInstanceId;
	}

	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "leave_id", unique = true, nullable = false)
	public Integer getLeaveId() {
		return this.leaveId;
	}

	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
	}

		
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="employee_id")
	public MainEmployee getEmployee() {
		return employee;
	}

	public void setEmployee(MainEmployee employee) {
		this.employee = employee;
	}

	@Column(name = "leave_long", length = 100)
	public String getLeaveLong() {
		return this.leaveLong;
	}

	public void setLeaveLong(String leaveLong) {
		this.leaveLong = leaveLong;
	}

	@Column(name = "leave_content", length = 4000)
	public String getLeaveContent() {
		return this.leaveContent;
	}

	public void setLeaveContent(String leaveContent) {
		this.leaveContent = leaveContent;
	}

	@Column(name = "leave_state", length = 100)
	public String getLeaveState() {
		return this.leaveState;
	}

	public void setLeaveState(String leaveState) {
		this.leaveState = leaveState;
	}

	@Column(name = "leave_instance_id", length = 100)
	public String getLeaveInstanceId() {
		return this.leaveInstanceId;
	}

	public void setLeaveInstanceId(String leaveInstanceId) {
		this.leaveInstanceId = leaveInstanceId;
	}

}