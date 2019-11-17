package ua.nure.leonov.summarytask.service;

import ua.nure.leonov.summarytask.dao.exception.DAOException;
import ua.nure.leonov.summarytask.entity.Edition;
import ua.nure.leonov.summarytask.service.exception.ServiceException;

import java.util.List;

public interface EditionService {

    List<Edition> findAllEditions() throws ServiceException;

    Edition findEditionById(int id) throws ServiceException;

    boolean create(Edition entity) throws ServiceException;

    boolean update(Edition entity) throws ServiceException;

    boolean delete(int id) throws ServiceException;

    List<Edition> sortAllEditions(String crit, boolean order) throws ServiceException;

    List<Edition> searchInEditions(String searchStr) throws ServiceException;

    List<Edition> findEditionsByTheme(int id) throws ServiceException;
}
