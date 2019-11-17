package ua.nure.leonov.summarytask.dao;

import ua.nure.leonov.summarytask.dao.exception.DAOException;
import ua.nure.leonov.summarytask.entity.Subscription;

import java.util.List;

public interface SubDAO extends MainDAO<Long, Subscription> {

    List<Subscription> findAllSubs() throws DAOException;

    List<Subscription> findAllSubsByUserWithEditionId(int id) throws DAOException;

    List<Subscription> findAllSubsByUserWithEditionTitle(int id) throws DAOException;

    List<Integer> findAllPurchasedEditionsId (int id) throws DAOException;
}
