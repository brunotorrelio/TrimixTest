package com.trimix.testbackend.filter;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;

public class AutoFlushOpenSessionInViewFilter extends OpenSessionInViewFilter{
	
	@Override
	protected Session openSession(SessionFactory sessionFactory) throws DataAccessResourceFailureException {
	    try {
	      Session session = sessionFactory.openSession();
	      session.setFlushMode(FlushMode.AUTO); 
	      return session;
	    } catch (HibernateException ex) {
	      throw new DataAccessResourceFailureException("Could not open Hibernate Session", ex);
	    }
	  }
}
