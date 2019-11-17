package ua.nure.leonov.summarytask.dao;

import ua.nure.leonov.summarytask.dao.exception.DAOException;
import ua.nure.leonov.summarytask.entity.User;

import java.util.List;

public interface UserDAO extends MainDAO<Long, User> {

    List<User> findAllUsers() throws DAOException;

    User findEntityByLogin(String login) throws DAOException;

    double findBalanceById(Long id) throws DAOException;

    String findPassById(Long id) throws DAOException;

    boolean updatePassById(Long id, String pass) throws DAOException;

    boolean updateUserBalanceAdd(Long id, double money) throws DAOException;

    boolean updateUserBalance(Long id, double newBalance) throws DAOException;
}
