package com.cn.bccm.common.service;

import java.io.Serializable;
import java.util.List;


public interface BaseService<T extends Serializable> {
	/**
	 * 通过ID查找对象
	 * 
	 * @param id
	 *            记?的ID
	 * @return 实体对象
	 */
	public T findById(Serializable id);

	public T load(Serializable id);

	/**
	 * 按属性查找对象的数量
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	public int countByProperty(String property, Object value);
	
	/**
	 * 按属性查找对象
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	public List<T> findByProperty(String property, Object value);
	
	/**
	 * 查找所有对象
	 * 
	 * @return 对象列表
	 */
	public List<T> findAll();
	

	public List<T> findByEgList(T eg, boolean anyWhere, String... exclude);



	public List<T> findByEgList(T eg, boolean anyWhere, int firstResult,
			int maxResult, String... exclude);


	public List<T> findByEgList(T eg, String... exclude);


	public Object updateDefault(Object entity);

	/**
	 * 保存对象
	 * 
	 * @param entity
	 *            实体对象
	 * @return 操作信息
	 */
	public T save(T entity);

	public Object update(Object o);

	public Object saveOrUpdate(Object o);

	public void delete(Object o);

	/**
	 * 根据ID删除记?
	 * 
	 * @param id
	 *            记?ID
	 */
	public T deleteById(Serializable id);

	/**
	 * 根据ID数组删除记?，当发生异常时，操作终止并回滚
	 * 
	 * @param ids
	 *            记?ID数组
	 * @return 删除的对象
	 */
	public List<T> deleteById(Serializable[] ids);

	/**
	 * 保存并刷新对象，避免many-to-one属性不完整
	 * 
	 * @param entity
	 */
	public T saveAndRefresh(T entity);
}
