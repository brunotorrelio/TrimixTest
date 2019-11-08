package com.trimix.testbackend.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.trimix.testbackend.dao.BaseDAO;

@Repository
@Transactional
public class BaseDAOImpl<T, ID extends Serializable> implements BaseDAO<T, ID> {
	
	private static final Logger LOG = LogManager.getLogger(BaseDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private EntityManager entityManager;
	private Class<T> persistentClass;
	
	@SuppressWarnings("unchecked")
	public BaseDAOImpl() {
		Type type = getClass().getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] types = ((ParameterizedType) type).getActualTypeArguments();
			persistentClass = (Class<T>) types[0];
			LOG.info("DAO for " + getPersistentClass().getSimpleName());
		} else {
			LOG.info("DAO without domain");
		}
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	protected Class<T> getPersistentClass(){
		return persistentClass;
	}
	
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	protected EntityManager getEntityManager() {
		if (entityManager == null) {
			this.entityManager = getSession().getEntityManagerFactory().createEntityManager();
		}
		return entityManager;
	}
	
	@Override
	public T getById(ID id) {
		if(id != null){
			return (T) getSession().get(getPersistentClass(), id);
		}
		else {
			return null;
		}
	}

	@Override
	public Object save(T object) {
		Object obj =  getSession().save(object);
		return obj;
	}

	@Override
	public void edit(T object) {
		getSession().update(object);
	}

	@Override
	public void delete(T objet) {
		getSession().delete(objet);
	}

	@Override
	public void deleteById(ID id) {
		T register = this.getById(id);
		getSession().delete(register);
	}

	@Override
	public List<T> getListHQL(String hql) {
		StringBuilder sb = new StringBuilder();
		sb.append("FROM ").append(getPersistentClass().getSimpleName());
		sb.append(" WHERE ").append(hql);
		
		LOG.info("hql: " + sb.toString());
		
		TypedQuery<T> typedQuery = getSession().createQuery(sb.toString(), getPersistentClass());
		
		return typedQuery.getResultList();
	}

	@Override
	public T getHQL(String hql) {
		StringBuilder sb = new StringBuilder();
		sb.append("FROM ").append(getPersistentClass().getSimpleName());
		sb.append(" WHERE ").append(hql);
		TypedQuery<T> typedQuery = getSession().createQuery(sb.toString(), getPersistentClass());
		typedQuery.setMaxResults(1);
		List<T> result = typedQuery.getResultList();
		if(result != null && !result.isEmpty()){
			return result.get(0);
		}
		return null;
	}

	@Override
	public List<T> getAll() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("FROM ").append(getPersistentClass().getSimpleName());
		TypedQuery<T> typedQuery = getSession().createQuery(sb.toString(), getPersistentClass());
		return typedQuery.getResultList();
		
	}
	
	
}
