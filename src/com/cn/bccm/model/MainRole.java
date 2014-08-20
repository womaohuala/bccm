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
 * MainRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "main_role", catalog = "bccm")
public class MainRole implements java.io.Serializable {

	// Fields

	private Integer roleId;//角色ID
	private String roleName;//角色名称
	private String roleInfo;//角色描述
	private String rolePer;//角色权限组
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "role_id", unique = true, nullable = false)
	public Integer getRoleId() {
		return roleId;
	}


	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name = "role_name", nullable = false, length = 20)
	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "role_info", nullable = false, length = 20)
	public String getRoleInfo() {
		return roleInfo;
	}


	public void setRoleInfo(String roleInfo) {
		this.roleInfo = roleInfo;
	}

	@Column(name = "role_per", nullable = false, length = 20)
	public String getRolePer() {
		return rolePer;
	}


	public void setRolePer(String rolePer) {
		this.rolePer = rolePer;
	}
	
	

	// Constructors

	/** default constructor */
	public MainRole() {
	}

}