package com.trimix.testbackend.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDAO<T, ID extends Serializable> {
	
	T getById(ID id);
	
	Object save(T object);

	void edit(T object);

	void delete(T objet);

	void deleteById(ID id);

	List<T> getListHQL(String hql);

	T getHQL(String hql);

	List<T> getAll();
}
