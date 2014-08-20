package com.cn.bccm.common.dao.impl;

import static org.hibernate.EntityMode.POJO;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.OrderBy;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Example.PropertySelector;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.cn.bccm.common.dao.BaseDao;

/**
 * DAO基类
 * 
 * 提供hql分页查询,example分页查询,拷贝更新等功能
 * 
 * @param <T>
 * 
 * @author 谢平
 * @version 2013-01-25
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@Transactional
@Repository
public abstract class BaseDaoImpl<T extends Serializable> extends HibernateDaoSupport implements BaseDao<T> {
	@Autowired
	protected JdbcTemplate jdbcTemplate;

	@Autowired
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Resource(name = "sessionFactory")
	public void setSessionFactory_0(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	public T save(T entity) {
		Assert.notNull(entity);
		getHibernateTemplate().save(entity);
		return entity;
	}

	public void delete(Object entity) {
		Assert.notNull(entity);
		getHibernateTemplate().delete(entity);
	}

	public T deleteById(Serializable id) {
		Assert.notNull(id);
		T entity = load(id);
		getHibernateTemplate().delete(entity);
		return entity;
	}

	public Object update(Object entity) {
		Assert.notNull(entity);
		getHibernateTemplate().update(entity);
		return entity;
	}

	public Object saveOrUpdate(Object entity) {
		Assert.notNull(entity);
		getHibernateTemplate().saveOrUpdate(entity);
		return entity;
	}

	public Object merge(Object entity) {
		Assert.notNull(entity);
		return getHibernateTemplate().merge(entity);
	}

	public T load(Serializable id) {
		Assert.notNull(id);
		return load(id, false);
	}

	public T load(Serializable id, boolean lock) {
		Assert.notNull(id);
		T entity = null;
		if (lock) {
			entity = (T) getSession().load(getPersistentClass(), id, LockMode.UPGRADE);
		} else {
			entity = (T) getSession().load(getPersistentClass(), id);
		}
		return entity;
	}

	public T get(Serializable id) {
		Assert.notNull(id);
		return (T) getSession().get(getPersistentClass(), id);
	}

	public List<T> findAll() {
		return findByCriteria();
	}



	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param hql
	 *            hql语句
	 * @param values
	 *            数量可变的参数
	 */
	protected List find(String hql, Object... values) {
		return createQuery(hql, values).list();
	}

	/**
	 * 按HQL查询唯一对象.
	 */
	protected Object findUnique(String hql, Object... values) {
		return createQuery(hql, values).uniqueResult();
	}

	/**
	 * 按属性查找对象列表.
	 */
	public List<T> findByProperty(String property, Object value) {
		Assert.hasText(property);
		return createCriteria(Restrictions.eq(property, value)).list();
	}

	/**
	 * 按属性查找唯一对象.
	 */
	public T findUniqueByProperty(String property, Object value) {
		Assert.hasText(property);
		Assert.notNull(value);
		return (T) createCriteria(Restrictions.eq(property, value))
				.uniqueResult();
	}

	public int countByProperty(String property, Object value) {
		Assert.hasText(property);
		Assert.notNull(value);
		return ((Number) (createCriteria(Restrictions.eq(property, value))
				.setProjection(Projections.rowCount()).uniqueResult()))
				.intValue();
	}


	/**
	 * 根据查询函数与参数列表创建Query对象,后续可进行更多处理,辅助函数.
	 */
	protected Query createQuery(String queryString, Object... values) {
		Assert.hasText(queryString);
		Query queryObject = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject;
	}

	/**
	 * 按Criterion查询对象列表.
	 * 
	 * @param criterion
	 *            数量可变的Criterion.
	 */
	protected List<T> findByCriteria(Criterion... criterion) {
		return createCriteria(criterion).list();
	}



	public void refresh(Object entity) {
		getSession().refresh(entity);
	}



	/**
	 * 根据Criterion条件创建Criteria,后续可进行更多处理,辅助函数.
	 */
	protected Criteria createCriteria(Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	private Class<T> persistentClass;

	public BaseDaoImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public T createNewEntiey() {
		try {
			return getPersistentClass().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("不能创建实体对象："
					+ getPersistentClass().getName());
		}
	}

	private ClassMetadata getCmd(Class clazz) {
		return (ClassMetadata) getSessionFactory().getClassMetadata(clazz);
	}

	public static final NotBlankPropertySelector NOT_BLANK = new NotBlankPropertySelector();

	/**
	 * 不为空的EXAMPLE属性选择方式
	 * 
	 * @author liufang
	 * 
	 */
	static final class NotBlankPropertySelector implements PropertySelector {
		private static final long serialVersionUID = 1L;

		public boolean include(Object object, String property, Type type) {
			return object != null
					&& !(object instanceof String && StringUtils
							.isBlank((String) object));
		}
	}


	public Object[] findBySQLFirstObject(String sql) {
		Query query = getSession().createSQLQuery(sql);
		List list = query.list();
		if (list.size() > 0)
			return (Object[]) list.get(0);
		return null;
	}




	/**
	 * SQL查询返回记录数
	 * 
	 * @param querySql
	 *            sql语句
	 * @param parames
	 *            查询参数
	 * 
	 * @return
	 */
	public int findBySQL(String querySql, Object[] parames) {
		Query query = getSessionFactory().getCurrentSession().createSQLQuery(
				querySql);
		if (parames != null) {
			for (int i = 0; i < parames.length; i++) {
				query.setParameter(i, parames[i]);
			}
		}
		return Integer.parseInt(query.uniqueResult().toString());
	}
	


	/**
	 * 删除记录
	 * 
	 * @param queryString
	 *            更新语句
	 * @param listParams
	 *            参数集合
	 * 
	 * @return 更新结果
	 */
	public int delete(final StringBuffer queryString, final List<Object> listParams) {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Integer doInHibernate(Session session) throws HibernateException, SQLException {
						Query query = session.createQuery(queryString.toString());
						if (listParams != null) {
							for (int i = 0; i < listParams.size(); i++) {
								query.setParameter(i, listParams.get(i));
							}
						}
						return query.executeUpdate();
					}
				});
	}

	/**
	 * 删除记录
	 * 
	 * @param queryString
	 *            更新语句
	 * @param objectParmas
	 *            参数数组
	 * 
	 * @return 更新结果
	 */
	public int delete(final StringBuffer queryString, final Object... objectParmas) {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Integer doInHibernate(Session session) throws HibernateException, SQLException {
						Query query = session.createQuery(queryString.toString());
						if (objectParmas != null) {
							for (int i = 0; i < objectParmas.length; i++) {
								query.setParameter(i, objectParmas[i]);
							}
						}
						return query.executeUpdate();
					}
				});
	}
	
	/**
	 * 更新记录
	 * 
	 * @param queryString
	 *            更新语句
	 * @param listParams
	 *            参数集合
	 * 
	 * @return 更新结果
	 */
	public int update(final StringBuffer queryString, final List<Object> listParams) {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Integer doInHibernate(Session session) throws HibernateException, SQLException {
						Query query = session.createQuery(queryString.toString());
						if (listParams != null) {
							for (int i = 0; i < listParams.size(); i++) {
								query.setParameter(i, listParams.get(i));
							}
						}
						return query.executeUpdate();
					}
				});
	}

	/**
	 * 更新记录
	 * 
	 * @param queryString
	 *            更新语句
	 * @param objectParmas
	 *            参数数组
	 * 
	 * @return 更新结果
	 */
	public int update(final StringBuffer queryString, final Object... objectParmas) {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Integer doInHibernate(Session session) throws HibernateException, SQLException {
						Query query = session.createQuery(queryString.toString());
						if (objectParmas != null) {
							for (int i = 0; i < objectParmas.length; i++) {
								query.setParameter(i, objectParmas[i]);
							}
						}
						return query.executeUpdate();
					}
				});
	}

	/**
	 * 根据条件查询分页结果集
	 * 
	 * @param queryString
	 *            查询语句
	 * @param firstResult
	 *            开始记录数
	 * @param maxResults
	 *            分页数
	 * @param parmas
	 *            参数集合
	 * 
	 * @return 结果集
	 */
	public List find(final StringBuffer queryString, final Integer firstResult, final Integer maxResults, final List<Object> parmas) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public List doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(queryString.toString());
				query.setFirstResult(firstResult);
				query.setMaxResults(maxResults);
				if (parmas != null) {
					for (int i = 0; i < parmas.size(); i++) {
						query.setParameter(i, parmas.get(i));
					}
				}
				return query.list();
			}
		});
	}

	/**
	 * 根据条件查询分页结果集
	 * 
	 * @param queryString
	 *            查询语句
	 * @param firstResult
	 *            开始记录数
	 * @param maxResults
	 *            分页数
	 * @param objectParmas
	 *            参数数组
	 * 
	 * @return 结果集
	 */
	public List find(final StringBuffer queryString, final Integer firstResult, final Integer maxResults, final Object... objectParmas) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public List doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(queryString.toString());
				query.setFirstResult(firstResult);
				query.setMaxResults(maxResults);
				if (objectParmas != null) {
					for (int i = 0; i < objectParmas.length; i++) {
						query.setParameter(i, objectParmas[i]);
					}
				}
				return query.list();
			}
		});
	}

	/**
	 * 查询分页结果集
	 * 
	 * @param queryString
	 *            查询语句
	 * @param firstResult
	 *            开始记录数
	 * @param maxResults
	 *            分页数
	 * 
	 * @return 结果集
	 */
	public List find(final StringBuffer queryString, final Integer firstResult, final Integer maxResults) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public List doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(queryString.toString());
				query.setFirstResult(firstResult);
				query.setMaxResults(maxResults);
				return query.list();
			}
		});
	}

	/**
	 * 根据条件查询结果集
	 * 
	 * @param queryString
	 *            查询语句
	 * @param parmas
	 *            参数集合
	 * 
	 * @return 结果集
	 */
	public List find(final StringBuffer queryString, final List<Object> parmas) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public List doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(queryString.toString());
				if (parmas != null) {
					for (int i = 0; i < parmas.size(); i++) {
						query.setParameter(i, parmas.get(i));
					}
				}
				return query.list();
			}
		});
	}

	/**
	 * 根据条件查询结果集
	 * 
	 * @param queryString
	 *            查询语句
	 * @param objectParmas
	 *            参数数组
	 * 
	 * @return 结果集
	 */
	public List find(final StringBuffer queryString, final Object... objectParmas) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public List doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(queryString.toString());
				if (objectParmas != null) {
					for (int i = 0; i < objectParmas.length; i++) {
						query.setParameter(i, objectParmas[i]);
					}
				}
				return query.list();
			}
		});
	}

	/**
	 * 查询记录数
	 * 
	 * @param queryStringCount
	 *            查询语句
	 * 
	 * @return 记录数
	 */
	protected int getRecordCount(final StringBuffer queryStringCount) {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Integer doInHibernate(Session session) throws HibernateException, SQLException {
						Query query = session.createQuery(queryStringCount.toString());
						return Integer.parseInt(query.uniqueResult().toString());
					}
				});
	}
	
	/**
	 * 查询记录数
	 * 
	 * @param queryStringCount
	 *            查询语句
	 * @param listParams
	 *            参数集合
	 * 
	 * @return 记录数
	 */
	public int getRecordCount(final StringBuffer queryStringCount, final List<Object> listParams) {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Integer doInHibernate(Session session) throws HibernateException, SQLException {
						Query query = session.createQuery(queryStringCount.toString());
						if (listParams != null) {
							for (int i = 0; i < listParams.size(); i++) {
								query.setParameter(i, listParams.get(i));
							}
						}
						return Integer.parseInt(query.uniqueResult().toString());
					}
				});
	}

	/**
	 * 查询记录数
	 * 
	 * @param queryStringCount
	 *            查询语句
	 * @param objectParmas
	 *            参数数组
	 * 
	 * @return 记录数
	 */
	public int getRecordCount(final StringBuffer queryStringCount, final Object... objectParmas) {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Integer doInHibernate(Session session) throws HibernateException, SQLException {
						Query query = session.createQuery(queryStringCount.toString());
						if (objectParmas != null) {
							for (int i = 0; i < objectParmas.length; i++) {
								query.setParameter(i, objectParmas[i]);
							}
						}
						return Integer.parseInt(query.uniqueResult().toString());
					}
				});
	}
}