package com.cn.bccm.common.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.cn.bccm.common.dao.BaseDao;
import com.cn.bccm.common.service.BaseService;

@Transactional
public class BaseServiceImpl<T extends Serializable> implements BaseService<T> {
	protected Logger log = LoggerFactory.getLogger(getClass());
	private BaseDao<T> dao;

	public void setDao(BaseDao<T> dao) {
		this.dao = dao;
	}

	protected BaseDao<T> getDao() {
		return this.dao;
	}

	@Transactional(readOnly = true)
	public T findById(Serializable id) {
		return dao.get(id);
	}

	@Transactional(readOnly = true)
	public T load(Serializable id) {
		return dao.load(id);
	}

	@Transactional(readOnly = true)
	public List<T> findAll() {
		return dao.findAll();
	}
	

	/**
	 * 按属性查找对象的数量
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	@Transactional(readOnly = true)
	public int countByProperty(String property, Object value){
		return dao.countByProperty(property, value);
	}
	
	@Transactional(readOnly = true)
	public List<T> findByProperty(String property, Object value) {
		return dao.findByProperty(property, value);
	}

	public Object saveOrUpdate(Object o) {
		return getDao().saveOrUpdate(o);
	}

	public void delete(Object o) {
		getDao().delete(o);
	}

	public Object update(Object o) {
		return getDao().update(o);
	}

	public Object merge(Object o) {
		return getDao().merge(o);
	}

	public T deleteById(Serializable id) {
		if (id == null) {
			return null;
		}
		return dao.deleteById(id);
	}

	public List<T> deleteById(Serializable[] ids) {
		List<T> dts = new ArrayList<T>();
		T del = null;
		if (ids != null && ids.length > 0) {
			for (Serializable id : ids) {
				del = deleteById(id);
				if (del != null) {
					dts.add(del);
				}
			}
		}
		return dts;
	}

	public List<T> findByEgList(T eg, boolean anyWhere, String... exclude) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> findByEgList(T eg, boolean anyWhere, int firstResult,
			int maxResult, String... exclude) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> findByEgList(T eg, String... exclude) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public T save(T entity) {
		return dao.save(entity);
	}

	public T saveAndRefresh(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object updateDefault(Object entity) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
