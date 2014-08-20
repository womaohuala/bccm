package com.cn.bccm.dao.base;


import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cn.bccm.common.util.ReflectionUtils;


@SuppressWarnings("unchecked")
public abstract class BaseHibernateDAO<T, ID extends Serializable> extends HibernateDaoSupport implements IBaseHibernateDAO<T, ID>{

	private static final Log logger = LogFactory.getLog(BaseHibernateDAO.class);
	
	private Class<T> entityClass;
	
	/**
	 * Default Constructor
	 */
	public BaseHibernateDAO(){
		this.entityClass = ReflectionUtils.getSuperClassGenricType(this.getClass());
		logger.debug("T class = " + entityClass.getName());
	}
	
	/**
	 * 为父类HibernateDaoSupport注入sessionFactory实例
	 * @param sessionFactory
	 */
	@Autowired
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	/**
	 * 判断一个对象是否为持久化状态
	 * @param t
	 * @return
	 * @throws DataAccessException
	 */
	public boolean contains(T t) throws DataAccessException {
		return getHibernateTemplate().contains(t);
	}
	
	/**
	 * 获取指定ID实体类对象
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	public T get(ID id) throws DataAccessException {
		T t = (T) getHibernateTemplate().get(entityClass, id);
		return t;
	}
	
	/**
	 * 获取指定ID实体类对象
	 * @param id
	 * @param loc
	 * @return
	 * @throws DataAccessException
	 */
	public T get(ID id, LockMode lockMode) throws DataAccessException {
		T t = (T) getHibernateTemplate().get(entityClass, id, lockMode);
		return t;
	}
	
	/**
	 * 获取所有实体对象列表
	 * @return
	 * @throws DataAccessException
	 */
	public List<T> getAll() throws DataAccessException {
		return this.getHibernateTemplate().loadAll(entityClass);
	}

	/**
	 * 保存新增的对象，返回该持久化实例的ID
	 * @param t
	 * @return
	 * @throws DataAccessException
	 */
	public ID save(T t) throws DataAccessException {
		return (ID)getHibernateTemplate().save(t);
	}
	
	/**
	 * 保存新增的或修改的对象
	 * @param t
	 * @throws DataAccessException
	 */
	public void saveOrUpdate(T t) throws DataAccessException {
		getHibernateTemplate().saveOrUpdate(t);
	}
	
	/**
	 * 保存新增的或修改的多个对象
	 * @param entities
	 * @throws DataAccessException
	 */
	public void saveOrUpdateAll(Collection<T> entities) throws DataAccessException {
		getHibernateTemplate().saveOrUpdateAll(entities);
	}

	/**
	 * 更新对象
	 * @param t
	 * @throws DataAccessException
	 */
	public void update(T t) throws DataAccessException {
		getHibernateTemplate().update(t);
	}
	
	/**
	 * 更新对象
	 * @param t
	 * @param lockMode
	 * @throws DataAccessException
	 */
	public void update(T t, LockMode lockMode) throws DataAccessException {
		getHibernateTemplate().update(t, lockMode);
	}
	
	/**
	 * 更新多个对象
	 * @param entities
	 * @throws DataAccessException
	 */
	public void updateAll(Collection<T> entities) throws DataAccessException {
		this.getHibernateTemplate().saveOrUpdateAll(entities);
	}

	/**
	 * 删除对象
	 * @param t
	 * @throws DataAccessException
	 */
	public void delete(T t) throws DataAccessException {
		getHibernateTemplate().delete(t);
	}
	
	/**
	 * 删除对象
	 * @param t
	 * @param lockMode
	 * @throws DataAccessException
	 */
	public void delete(T t, LockMode lockMode) throws DataAccessException {
		getHibernateTemplate().delete(t, lockMode);
	}
	
	/**
	 * 删除多个对象
	 * @param entities
	 * @throws DataAccessException
	 */
	public void deleteAll(Collection<T> entities) throws DataAccessException {
		getHibernateTemplate().deleteAll(entities);
	}
	
	/**
	 * 查找唯一记录
	 * 如果有多个记录,抛出异常; 
	 * 如果有且只有一个记录,返回该记录; 
	 * 如果没有记录,返回null.
	 * @param hql
	 * @param params
	 * @return
	 */
	public T findUnique(final String hql, final Object... params) throws DataAccessException {
		HibernateCallback<T> hc = new HibernateCallback<T>() {
			
			public T doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				if (params != null) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);
					}
				}
				return (T)query.uniqueResult();
			}
		};
		
		return getHibernateTemplate().execute(hc);
	}
	
	/**
	 * 查找唯一记录
	 * 如果有多个记录,抛出异常; 
	 * 如果有且只有一个记录,返回该记录; 
	 * 如果没有记录,返回null.
	 * @param hql
	 * @param params
	 * @return
	 */
	public T findUnique(final String hql, final Map<String, Object> params) throws DataAccessException {
		HibernateCallback<T> hc = new HibernateCallback<T>() {
			public T doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setProperties(params);
				return (T)query.uniqueResult();
			}
		};
		return getHibernateTemplate().execute(hc);
	}
	
	/**
	 * 计算总数
	 * @param hql "select count(*) from ……"
	 * @param params
	 * @return
	 * @throws DataAccessException
	 */
	public Long findCount(final String hql, final Object...params) throws DataAccessException {
		return ((Long)findUnique(hql, params));
	}
	
	/**
	 * 计算总数
	 * @param hql "select count(*) from ……"
	 * @param params
	 * @return
	 * @throws DataAccessException
	 */
	public Long findCount(final String hql, final Map<String, Object> params) throws DataAccessException {
		return ((Long)findUnique(hql, params));
	}
	
	/**
	 * 查询对象列表
	 * @param queryName
	 * @param params
	 * @return
	 */
	public List<T> findByNamedQuery(final String queryName, final Object...params) throws DataAccessException {
		return getHibernateTemplate().findByNamedQuery(queryName, params);
	}

	/**
	 * 查找对象列表
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<T> list(final String hql, final Object...params) throws DataAccessException {
		return getHibernateTemplate().find(hql, params);
	}
	
	/**
	 * 查询对象列表
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<T> list(final String hql, final Map<String, Object> params) throws DataAccessException {
		HibernateCallback hc = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setProperties(params);
				List list = query.list();
				return list;
			}
		};
		return getHibernateTemplate().executeFind(hc);
	}
	
	/**
	 * 分页查询对象列表
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public List<T> listByPage(final String hql, final int pageNo, final int pageSize, final Object...params) throws DataAccessException {
		HibernateCallback hc = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				if (params != null) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);
					}
				}
				query.setMaxResults(pageSize);
				query.setFirstResult((pageNo-1)*pageSize);
				List list = query.list();
				return list;
			}
		};
		return getHibernateTemplate().executeFind(hc);
	}
	
	/**
	 * 分页查询对象列表
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public List<T> listByPage(final String hql, final int pageNo, final int pageSize, final Map<String, Object> params) throws DataAccessException {
		HibernateCallback hc = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setProperties(params);
				query.setMaxResults(pageSize);
				query.setFirstResult((pageNo-1)*pageSize);
				List list = query.list();
				return list;
			}
		};
		return getHibernateTemplate().executeFind(hc);
	}
	
	/**
	 * 分页查询对象列表
	 * @param page
	 * @param hql
	 * @param params
	 * @return
	 */
	public Page<T> listByPage(final Page page, final String hql, final Object...params) throws DataAccessException {
		int pageNo = page.getPageNo();
		if(pageNo < 1){
			pageNo = 1;
		}
		int pageSize = page.getPageSize();
		String sql = hql;
		List list = listByPage(sql, pageNo, pageSize, params);
		page.setPageData(list);
		return page;
	}
	
	/**
	 * 分页查询对象列表
	 * @param page
	 * @param hql
	 * @param params
	 * @return
	 */
	public Page<T> listByPage(final Page page, final String hql, final Map<String, Object> params) throws DataAccessException {
		int pageNo = page.getPageNo();
		if(pageNo < 1){
			pageNo = 1;
		}
		int pageSize = page.getPageSize();
		String sql = hql;
		List list = listByPage(sql, pageNo, pageSize, params);
		page.setPageData(list);
		return page;
	}
	
	/**
	 * 执行更新语句
	 * @param hql
	 * @param params
	 * @return 更新的记录数
	 */
	public int executeUpdate(final String hql, final Object...params) throws DataAccessException {
		HibernateCallback hc = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				if (params != null) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);
					}
				}
				return query.executeUpdate();
			}
		};
		return (Integer)getHibernateTemplate().execute(hc);
	}
	
	/**
	 * 执行更新语句
	 * @param hql
	 * @param params
	 * @return
	 */
	public int executeUpdate(final String hql, final Map<String, Object> params) throws DataAccessException {
		HibernateCallback hc = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setProperties(params);
				return query.executeUpdate();
			}
		};
		return (Integer)getHibernateTemplate().execute(hc);
	}
	
	/**
	 * 批量更新
	 * @param hql
	 * @param values
	 * @throws DataAccessException
	 */
	public void bulkUpdate(final String hql, final Object...values) throws DataAccessException {
		this.getHibernateTemplate().bulkUpdate(hql, values);
	}
	
	/**
	 * 批量更新
	 * @param hql
	 * @param values
	 * @throws DataAccessException
	 */
	public void bulkUpdate(final String hql, final Collection<Object> values) throws DataAccessException {
		this.getHibernateTemplate().bulkUpdate(hql, values.toArray());
	}
	
	/**
	 * 提交缓存数据
	 * @throws DataAccessException
	 */
	public void flush() throws DataAccessException {
		getHibernateTemplate().flush();
	}
	
	/**
	 * 清空缓存数据
	 * @throws DataAccessException
	 */
	public void clear() throws DataAccessException {
		getHibernateTemplate().clear();
	}

	/**
	 * 重新获取对象
	 * @param t
	 * @throws DataAccessException
	 */
	public void refresh(T t) throws DataAccessException {
		getHibernateTemplate().refresh(t);
	}
	
	/**
	 * 重新获取对象
	 * @param t
	 * @param lockMode
	 * @throws DataAccessException
	 */
	public void refresh(T t, LockMode lockMode) throws DataAccessException {
		getHibernateTemplate().refresh(t, lockMode);
	}
	
	
	public <T> T merge(final T entity) throws DataAccessException{
		return getHibernateTemplate().merge(entity);
	}
}
