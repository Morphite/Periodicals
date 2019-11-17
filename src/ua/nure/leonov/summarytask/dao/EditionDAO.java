package ua.nure.leonov.summarytask.dao;

import ua.nure.leonov.summarytask.dao.exception.DAOException;
import ua.nure.leonov.summarytask.entity.Edition;

import java.util.List;

public interface EditionDAO extends MainDAO<Long, Edition> {

    List<Edition> findAllEditions() throws DAOException;

    List<Edition> sortAllEditions(String crit, boolean order) throws DAOException;

    List<Edition> findEditionsByTheme(int id) throws DAOException;
}
