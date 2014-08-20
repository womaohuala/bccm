package com.cn.bccm.dao.base;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.springframework.dao.DataAccessException;

@SuppressWarnings("unchecked")
public interface IBaseHibernateDAO<T, ID extends Serializable> {
	
	/**
	 * 判断一个对象是否为持久化状态
	 * @param t
	 * @return
	 * @throws DataAccessException
	 */
	public boolean contains(T t) throws DataAccessException;
	
	/**
	 * 查找指定ID实体类对象
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	public T get(ID id) throws DataAccessException;
	
	/**
	 * 查找指定ID实体类对象
	 * @param id
	 * @param loc
	 * @return
	 * @throws DataAccessException
	 */
	public T get(ID id, LockMode lockMode) throws DataAccessException;

	/**
	 * 获取所有实体对象列表
	 * @return
	 * @throws DataAccessException
	 */
	public List<T> getAll() throws DataAccessException;
	
	/**
	 * 保存新增的对象，返回该持久化实例的ID
	 * @param t
	 * @return
	 * @throws DataAccessException
	 */
	public ID save(T t) throws DataAccessException;
	
	/**
	 * 保存新增的或修改的对象
	 * @param t
	 * @throws DataAccessException
	 */
	public void saveOrUpdate(T t) throws DataAccessException;
	
	/**
	 * 保存新增的或修改的多个对象
	 * @param entities
	 * @throws DataAccessException
	 */
	public void saveOrUpdateAll(Collection<T> entities) throws DataAccessException;

	/**
	 * 更新对象
	 * @param t
	 * @throws DataAccessException
	 */
	public void update(T t) throws DataAccessException;
	
	/**
	 * 更新对象
	 * @param t
	 * @param lockMode
	 * @throws DataAccessException
	 */
	public void update(T t, LockMode lockMode) throws DataAccessException;
	
	/**
	 * 更新多个对象
	 * @param entities
	 * @throws DataAccessException
	 */
	public void updateAll(Collection<T> entities) throws DataAccessException;

	/**
	 * 删除对象
	 * @param t
	 * @throws DataAccessException
	 */
	public void delete(T t) throws DataAccessException;
	
	/**
	 * 删除对象
	 * @param t
	 * @param lockMode
	 * @throws DataAccessException
	 */
	public void delete(T t, LockMode lockMode) throws DataAccessException;
	
	/**
	 * 删除多个对象
	 * @param entities
	 * @throws DataAccessException
	 */
	public void deleteAll(Collection<T> entities) throws DataAccessException;
	
	/**
	 * 查找唯一记录
	 * 如果有多个记录,抛出异常; 
	 * 如果有且只有一个记录,返回该记录; 
	 * 如果没有记录,返回null.
	 * @param hql
	 * @param params
	 * @return
	 */
	public T findUnique(final String hql, final Object... params) throws DataAccessException;
	
	/**
	 * 查找唯一记录
	 * 如果有多个记录,抛出异常; 
	 * 如果有且只有一个记录,返回该记录; 
	 * 如果没有记录,返回null.
	 * @param hql
	 * @param params
	 * @return
	 */
	public T findUnique(final String hql, final Map<String, Object> params) throws DataAccessException;
	
	/**
	 * 计算总数
	 * @param hql "select count(*) from ……"
	 * @param params
	 * @return
	 * @throws DataAccessException
	 */
	public Long findCount(final String hql, final Object...params) throws DataAccessException;
	
	/**
	 * 计算总数
	 * @param hql "select count(*) from ……"
	 * @param params
	 * @return
	 * @throws DataAccessException
	 */
	public Long findCount(final String hql, final Map<String, Object> params) throws DataAccessException;
	
	/**
	 * 查询对象列表
	 * @param queryName
	 * @param params
	 * @return
	 */
	public List<T> findByNamedQuery(final String queryName, final Object...params) throws DataAccessException;

	/**
	 * 查找对象列表
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<T> list(final String hql, final Object...params) throws DataAccessException;
	
	/**
	 * 查询对象列表
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<T> list(final String hql, final Map<String, Object> params) throws DataAccessException;
	
	/**
	 * 分页查询对象列表
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public List<T> listByPage(final String hql, final int pageNo, final int pageSize, final Object...params) throws DataAccessException;
	
	/**
	 * 分页查询对象列表
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public List<T> listByPage(final String hql, final int pageNo, final int pageSize, final Map<String, Object> params) throws DataAccessException;
	
	/**
	 * 分页查询对象列表
	 * @param page
	 * @param hql
	 * @param params
	 * @return
	 */
	public Page<T> listByPage(final Page page, final String hql, final Object...params) throws DataAccessException;
	
	/**
	 * 分页查询对象列表
	 * @param page
	 * @param hql
	 * @param params
	 * @return
	 */
	public Page<T> listByPage(final Page page, final String hql, final Map<String, Object> params) throws DataAccessException;
	
	/**
	 * 执行更新语句
	 * @param hql
	 * @param params
	 * @return 更新的记录数
	 */
	public int executeUpdate(final String hql, final Object...params) throws DataAccessException;
	
	/**
	 * 执行更新语句
	 * @param hql
	 * @param params
	 * @return
	 */
	public int executeUpdate(final String hql, final Map<String, Object> params) throws DataAccessException;
	
	/**
	 * 批量更新
	 * @param hql
	 * @param values
	 * @throws DataAccessException
	 */
	public void bulkUpdate(final String hql, final Object...values) throws DataAccessException;
	
	/**
	 * 批量更新
	 * @param hql
	 * @param values
	 * @throws DataAccessException
	 */
	public void bulkUpdate(final String hql, final Collection<Object> values);
	
	/**
	 * 提交缓存数据
	 * @throws DataAccessException
	 */
	public void flush() throws DataAccessException;
	
	/**
	 * 清空缓存数据
	 * @throws DataAccessException
	 */
	public void clear() throws DataAccessException;

	/**
	 * 重新获取对象
	 * @param t
	 * @throws DataAccessException
	 */
	public void refresh(T t) throws DataAccessException;
	
	/**
	 * 重新获取对象
	 * @param t
	 * @param lockMode
	 * @throws DataAccessException
	 */
	public void refresh(T t, LockMode lockMode) throws DataAccessException;
	
	public <T> T merge(final T entity) throws DataAccessException;
}
