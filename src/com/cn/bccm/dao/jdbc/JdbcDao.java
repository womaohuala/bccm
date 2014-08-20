package com.cn.bccm.dao.jdbc;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cn.bccm.dao.jdbc.base.Paging;



@Repository
public class JdbcDao {

	private static final Log log = LogFactory.getLog(JdbcDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Double getDouble(String persid){
		Double totalTime;
		String sql="select 1 from dual";
		List<Double> list = jdbcTemplate.queryForList(sql, Double.class);
		if(list!=null&&list.size()>0){
			totalTime=0.0;
			totalTime=list.get(0);
		}else{
			totalTime=0.0;
		}
		return totalTime;
	}
	
	/**
	 * 分页功能例子
	 * @param currentpage
	 * @param count
	 * @return
	 */
	public Paging<Map<String,Object>> getObjectByPage(int page, int size){
		Paging<Map<String,Object>> paging = new Paging<Map<String,Object>>();
		paging.setPage(page);
		paging.setPageSize(size);
		String sqlCount = "select count(*) counts from table p  ";
		int total= jdbcTemplate.queryForInt(sqlCount);
		if(total<1){
			log.warn("getObjectByPage list is empty page:"+page);
			return paging;
		}
		paging.setTotal(total);
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		list=jdbcTemplate.queryForList("select * from table "+paging.getLimitInfo(), new Object[]{});
		paging.setList(list);
		return paging;
	}

	
	
	
	

}
