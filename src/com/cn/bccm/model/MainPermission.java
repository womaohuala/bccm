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
 * MainPermission entity.
 *  @author hux

 */
@Entity
@Table(name = "main_permission", catalog = "bccm")
public class MainPermission implements java.io.Serializable {

	// Fields

	private Integer perId;//权限ID
	private String perName;//权限名称
	private String perInfo;//权限描述
	private Integer perParent;//父权限
	private String perAction;//权限路径
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "per_id", unique = true, nullable = false)
	public Integer getPerId() {
		return perId;
	}


	public void setPerId(Integer perId) {
		this.perId = perId;
	}

	@Column(name = "per_name", nullable = false, length = 20)
	public String getPerName() {
		return perName;
	}


	public void setPerName(String perName) {
		this.perName = perName;
	}

	@Column(name = "per_info", nullable = false, length = 20)
	public String getPerInfo() {
		return perInfo;
	}


	public void setPerInfo(String perInfo) {
		this.perInfo = perInfo;
	}

	@Column(name = "per_parent", nullable = false, length = 20)
	public Integer getPerParent() {
		return perParent;
	}
	
	
	public void setPerParent(Integer perParent) {
		this.perParent = perParent;
	}

	// Constructors

	/** default constructor */
	public MainPermission() {
	}

	@Column(name = "per_action", nullable = false, length = 60)
	public String getPerAction() {
		return perAction;
	}


	public void setPerAction(String perAction) {
		this.perAction = perAction;
	}



}