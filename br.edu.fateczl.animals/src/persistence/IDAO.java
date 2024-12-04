package persistence;

import java.util.List;

import exception.DAOException;

public interface IDAO<T> {
	void insert(T t) throws DAOException;
	void update(T t) throws DAOException;
	void delete(T t) throws DAOException;
	T findOne(T t) throws DAOException;
	List<T> findAll() throws DAOException;
}
