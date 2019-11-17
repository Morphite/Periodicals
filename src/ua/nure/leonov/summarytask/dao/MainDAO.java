package ua.nure.leonov.summarytask.dao;

import ua.nure.leonov.summarytask.dao.exception.DAOException;
import ua.nure.leonov.summarytask.entity.Entity;

public interface MainDAO<K, T extends Entity> {

    T findEntityById(K id) throws DAOException;

    boolean delete(K id) throws DAOException;

    boolean create(T entity) throws DAOException;

    boolean update(T entity) throws DAOException;
}
