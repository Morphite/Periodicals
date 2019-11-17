package ua.nure.leonov.summarytask.service;

import ua.nure.leonov.summarytask.dao.exception.DAOException;
import ua.nure.leonov.summarytask.entity.Subscription;
import ua.nure.leonov.summarytask.service.exception.ServiceException;

import java.util.List;

public interface SubService {

    List<Subscription> findAllSubs() throws ServiceException;

    List<Subscription> findAllSubByUserTitle(int id) throws ServiceException;

    List<Subscription> findAllSubByUserId(int id) throws ServiceException;

    boolean makeSubscribe(Long userId, int editionId, double price, double newBalance) throws ServiceException;

    boolean unsubscribe(int id) throws ServiceException;

    List<Integer> findAllPurchasedEditionsId (int id) throws ServiceException;

    Subscription findSubById(Long id) throws ServiceException;
}
