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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Staff entity. @author MyEclipse Persistence Tools
 */

/*
@Entity
@Table(name = "staff", catalog = "bccm")
*/
public class Staff implements java.io.Serializable {

	// Fields

	private Integer staffId;
	private String staffName;
	private String staffPsw;
	private String staffPosition;
	private Set<Leave> leaves;

	// Constructors

	/** default constructor */
	public Staff() {
	}

	/** full constructor */
	public Staff(String staffName, String staffPsw, String staffPosition,
			Set leaves) {
		this.staffName = staffName;
		this.staffPsw = staffPsw;
		this.staffPosition = staffPosition;
		this.leaves = leaves;
	}

	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "staff_id", unique = true, nullable = false)
	public Integer getStaffId() {
		return this.staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	@Column(name = "staff_name", nullable = false, length = 100)
	public String getStaffName() {
		return this.staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	@Column(name = "staff_psw", nullable = false, length = 100)
	public String getStaffPsw() {
		return this.staffPsw;
	}

	public void setStaffPsw(String staffPsw) {
		this.staffPsw = staffPsw;
	}

	@Column(name = "staff_position", nullable = false, length = 100)
	public String getStaffPosition() {
		return this.staffPosition;
	}

	public void setStaffPosition(String staffPosition) {
		this.staffPosition = staffPosition;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="staff")
	public Set<Leave> getLeaves() {
		return this.leaves;
	}

	public void setLeaves(Set<Leave> leaves) {
		this.leaves = leaves;
	}

}