package ua.nure.leonov.summarytask.service;

import ua.nure.leonov.summarytask.entity.User;
import ua.nure.leonov.summarytask.service.exception.ServiceException;

import java.util.List;

public interface UserService {

    List<User> findAllUsers() throws ServiceException;

    User findUserById(long id) throws ServiceException;

    User findUserByLogin(String login) throws ServiceException;

    double findBalanceById(long id) throws ServiceException;

    boolean checkBalanceToAdd(long userId, double money) throws ServiceException;

    boolean checkUserCreate(String newLogin, String newPass, String newEmail, String newName,
                            String newSurname) throws ServiceException;


    boolean checkUserCreateByAdmin(String newLogin, String newPass, String newEmail, String newName,
                                   String newSurname, boolean newIsAdmin, boolean newIsBan, double balance) throws ServiceException;

    boolean deleteUser(long id) throws ServiceException;

    boolean checkUserEdit(Long id, String newLogin, String newPass, String newEmail, String newName,
                          String newSurname, boolean newIsAdmin, boolean newIsBan, double balance) throws ServiceException;

    boolean checkLoginAndPass(String login, String pass) throws ServiceException;

    boolean checkEnteredValues(String enterLogin, String enterPass, String enterEmail, String enterName, String enterSurname) throws ServiceException;

}
