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
 * CoopCompany entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "coop_company", catalog = "bccm")
public class CoopCompany implements java.io.Serializable {

	// Fields

	private Integer compId;
	private String compName;
	private String compIntro;
	private String compHead;
	private String compPhone;
	private Set<CoopProject> projects;

	// Constructors

	/** default constructor */
	public CoopCompany() {
	}

	/** full constructor */
	public CoopCompany(String compName, String compIntro, String compHead,
			String compPhone) {
		this.compName = compName;
		this.compIntro = compIntro;
		this.compHead = compHead;
		this.compPhone = compPhone;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "comp_id", unique = true, nullable = false)
	public Integer getCompId() {
		return this.compId;
	}

	public void setCompId(Integer compId) {
		this.compId = compId;
	}

	@Column(name = "comp_name", nullable = false, length = 100)
	public String getCompName() {
		return this.compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	@Column(name = "comp_intro", nullable = false, length = 1000)
	public String getCompIntro() {
		return this.compIntro;
	}

	public void setCompIntro(String compIntro) {
		this.compIntro = compIntro;
	}

	@Column(name = "comp_head", nullable = false, length = 20)
	public String getCompHead() {
		return this.compHead;
	}

	public void setCompHead(String compHead) {
		this.compHead = compHead;
	}

	@Column(name = "comp_phone", nullable = false, length = 20)
	public String getCompPhone() {
		return this.compPhone;
	}

	public void setCompPhone(String compPhone) {
		this.compPhone = compPhone;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="company")
	public Set<CoopProject> getProjects() {
		return projects;
	}

	public void setProjects(Set<CoopProject> projects) {
		this.projects = projects;
	}
	
	

}