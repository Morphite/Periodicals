package ua.nure.leonov.summarytask.service.impl;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.dao.EditionDAO;
import ua.nure.leonov.summarytask.dao.exception.DAOException;
import ua.nure.leonov.summarytask.dao.impl.EditionDAOImpl;
import ua.nure.leonov.summarytask.entity.Edition;
import ua.nure.leonov.summarytask.entity.Subscription;
import ua.nure.leonov.summarytask.service.EditionService;
import ua.nure.leonov.summarytask.service.exception.ServiceException;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public class EditionServiceImpl implements EditionService {

    private final static Logger LOG = Logger.getLogger(EditionServiceImpl.class);

    private final static int EDITION_ID_FOR_INSERT = 0;

    @Override
    public List<Edition> findAllEditions() throws ServiceException {
        try {
            return EditionDAOImpl.getInstance().findAllEditions();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Edition findEditionById(int id) throws ServiceException {
        try {
            return EditionDAOImpl.getInstance().findEntityById((long) id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean create(Edition entity) throws ServiceException {
        if (Validator.validateTitleEdition(entity.getTitle()) && Validator.validateTextEdition(entity.getText()) &&
                Validator.validatePublisherEdition(entity.getPublisher()) && Validator.validatePrice(entity.getPrice())) {
            try {
                entity.setId(EDITION_ID_FOR_INSERT);
                return EditionDAOImpl.getInstance().create(entity);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }

    @Override
    public boolean update(Edition entity) throws ServiceException {
        if (Validator.validateTitleEdition(entity.getTitle()) && Validator.validateTextEdition(entity.getText()) &&
                Validator.validatePublisherEdition(entity.getPublisher()) && Validator.validatePrice(entity.getPrice())) {
            try {
                return EditionDAOImpl.getInstance().update(entity);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        try {
            return EditionDAOImpl.getInstance().delete((long) id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Edition> sortAllEditions(String crit, boolean order) throws ServiceException {
        try {
            return EditionDAOImpl.getInstance().sortAllEditions(crit, order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Edition> searchInEditions(String searchStr) throws ServiceException {
        try {
            List<Edition> editions = EditionDAOImpl.getInstance().findAllEditions();
            List<Edition> result = new ArrayList<>();
            for (Edition edition : editions) {
                if (edition.getTitle().toLowerCase().contains(searchStr.toLowerCase())) {
                    result.add(edition);
                }
            }
            return result;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Edition> findEditionsByTheme(int id) throws ServiceException {
        try {
            return EditionDAOImpl.getInstance().findEditionsByTheme(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
