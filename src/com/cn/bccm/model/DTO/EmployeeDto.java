package com.cn.bccm.model.DTO;

//用户企业结构树状图
public class EmployeeDto {
	private String id;        //职员Id或者部门Id
	private String parentId;  //部门Id或者父部门id
	private String name;   //部门名称或者职员名称
	private String type;   //1表示是部门，2表示是职员
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
	
}
