package ua.nure.leonov.summarytask.service.impl;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.dao.exception.DAOException;
import ua.nure.leonov.summarytask.dao.impl.SubDAOImpl;
import ua.nure.leonov.summarytask.dao.impl.UserDAOImpl;
import ua.nure.leonov.summarytask.entity.Edition;
import ua.nure.leonov.summarytask.entity.Subscription;
import ua.nure.leonov.summarytask.entity.User;
import ua.nure.leonov.summarytask.service.SubService;
import ua.nure.leonov.summarytask.service.exception.ServiceException;

import java.util.Date;
import java.util.List;

public class SubServiceImpl implements SubService {

    private final static Logger LOG = Logger.getLogger(SubServiceImpl.class);

    @Override
    public List<Subscription> findAllSubs() throws ServiceException {
        try {
            return SubDAOImpl.getInstance().findAllSubs();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Subscription> findAllSubByUserTitle(int id) throws ServiceException {
        try {
            return SubDAOImpl.getInstance().findAllSubsByUserWithEditionTitle(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Subscription> findAllSubByUserId(int id) throws ServiceException {
        try {
            return SubDAOImpl.getInstance().findAllSubsByUserWithEditionId(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean makeSubscribe(Long userId, int editionId, double price, double newBalance) throws ServiceException {
        Subscription subscription = new Subscription();
        subscription.setId(0);
        User user = new User();
        user.setId(userId);
        subscription.setUser(user);

        Edition edition = new Edition();
        edition.setId(editionId);
        edition.setPrice(price);
        subscription.setEdition(edition);

        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);

        subscription.setDate(currentTime);

        if (checkSubAvailability(Math.toIntExact(userId), editionId) && newBalance >= 0) {
            try {
                if (SubDAOImpl.getInstance().create(subscription) && UserDAOImpl.getInstance().updateUserBalance(userId, newBalance)) {
                    return true;
                }
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }

    private boolean checkSubAvailability(int userId, int editionID) throws ServiceException {
        List<Integer> purchasedEditionsId = findAllPurchasedEditionsId(userId);
        for (Integer purchasedEdit : purchasedEditionsId) {
            if (purchasedEdit == editionID) {
                return false;
            }
        }
        return true;
    }

    public boolean checkReadAvailability(int userId, int editionID) throws ServiceException{
        List<Integer> purchasedEditionsId = findAllPurchasedEditionsId(userId);
        for (Integer purchasedEdit : purchasedEditionsId) {
            if (purchasedEdit == editionID) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean unsubscribe(int id) throws ServiceException {
        try {
            return SubDAOImpl.getInstance().delete((long) id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Integer> findAllPurchasedEditionsId(int id) throws ServiceException {
        try {
            return SubDAOImpl.getInstance().findAllPurchasedEditionsId(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Subscription findSubById(Long id) throws ServiceException {
        try {
            return SubDAOImpl.getInstance().findEntityById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
