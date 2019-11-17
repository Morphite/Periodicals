package ua.nure.leonov.summarytask.service.impl;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.dao.exception.DAOException;
import ua.nure.leonov.summarytask.dao.impl.UserDAOImpl;
import ua.nure.leonov.summarytask.entity.User;
import ua.nure.leonov.summarytask.service.UserService;
import ua.nure.leonov.summarytask.service.exception.ServiceException;
import util.Validator;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final static Logger LOG = Logger.getLogger(UserServiceImpl.class);

    private final static int USER_ID_FOR_INSERT = 0;

    private final static boolean USER_DEFAULT_ADMIN = false;

    private final static boolean USER_DEFAULT_BAN = false;

    private final static int USER_DEFAULT_BALANCE = 0;

    @Override
    public List<User> findAllUsers() throws ServiceException {
        try {
            return UserDAOImpl.getInstance().findAllUsers();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findUserById(long id) throws ServiceException {
        try {
            return UserDAOImpl.getInstance().findEntityById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findUserByLogin(String login) throws ServiceException {
        try {
            return UserDAOImpl.getInstance().findEntityByLogin(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public double findBalanceById(long id) throws ServiceException {
        try {
            return UserDAOImpl.getInstance().findBalanceById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean checkBalanceToAdd(long userId, double money) throws ServiceException {
        boolean isOk = false;
        if (Validator.validateBalanceToAdd(money)) {
            try {
                UserDAOImpl dao = UserDAOImpl.getInstance();

                if (dao.updateUserBalanceAdd(userId, money)) {
                    isOk = true;
                }
            } catch (DAOException e) {
                throw new ServiceException("Failed to add money to balance", e);
            }
        }
        return isOk;
    }

    public boolean checkPassword(String enterOldPass, String enterNewPass, Long userId) throws ServiceException {
        boolean flag = false;
        if (Validator.validatePass(enterNewPass)) {
            UserDAOImpl userDAO = UserDAOImpl.getInstance();
            String passwordOld;
            try {
                passwordOld = userDAO.findPassById(userId);
            } catch (DAOException e) {
                throw new ServiceException("Failed to find password by user id.", e);
            }
            try {
                if (passwordOld.equals(enterOldPass)) {
                    if (userDAO.updatePassById(userId, enterNewPass)) {
                        flag = true;
                    }
                }
            } catch (DAOException e) {
                throw new ServiceException("Failed to update password by user id.", e);
            }
        }
        return flag;
    }

    @Override
    public boolean checkUserCreate(String newLogin, String newPass, String newEmail, String newName, String newSurname) throws ServiceException {
        UserDAOImpl userDAO = UserDAOImpl.getInstance();
        try {
            if (checkEnteredValues(newLogin, newPass, newEmail, newName, newSurname)) {
                if (userDAO.findEntityByLogin(newLogin).getId() == 0) {
                    User user = new User(newLogin, newPass, newEmail, newName, newSurname, USER_DEFAULT_ADMIN, USER_DEFAULT_BAN, USER_DEFAULT_BALANCE);
                    user.setId(USER_ID_FOR_INSERT);

                    return userDAO.create(user);
                }
            }
        } catch (DAOException e) {
            throw new ServiceException("Failed to register user", e);
        }
        return false;
    }

    @Override
    public boolean checkUserCreateByAdmin(String newLogin, String newPass, String newEmail, String newName, String newSurname, boolean newIsAdmin, boolean newIsBan, double balance) throws ServiceException {
        UserDAOImpl userDAO = UserDAOImpl.getInstance();
        try {
            if (Validator.validateLogin(newLogin) && Validator.validatePass(newPass) && Validator.validateEmail(newEmail) &&
                    Validator.validateName(newName) && Validator.validateSurname(newSurname) && Validator.validateUserCreateMoney(balance)) {
                if (userDAO.findEntityByLogin(newLogin).getId() == 0) {
                    User user = new User(newLogin, newPass, newEmail, newName, newSurname, newIsAdmin, newIsBan, balance);
                    user.setId(USER_ID_FOR_INSERT);
                    return userDAO.create(user);
                }
            }
        } catch (DAOException e) {
            throw new ServiceException("Failed to create user", e);
        }
        return false;
    }

    @Override
    public boolean deleteUser(long id) throws ServiceException {
        try {
            return UserDAOImpl.getInstance().delete(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean checkUserEdit(Long id, String newLogin, String newPass, String newEmail, String newName, String newSurname, boolean newIsAdmin, boolean newIsBan, double balance) throws ServiceException {
        if (checkEnteredValues(newLogin, newPass, newEmail, newName, newSurname)) {
            User user = new User(newLogin, newPass, newEmail, newName, newSurname, newIsAdmin, newIsBan, balance);
            user.setId(id);

            UserDAOImpl dao = UserDAOImpl.getInstance();

            try {
                return dao.update(user);
            } catch (DAOException e) {
                throw new ServiceException("Failed to update user", e);
            }
        }
        return false;
    }

    public boolean checkLogin(String login) throws ServiceException {
        return !login.equals(findUserByLogin(login).getLogin());
    }

    @Override
    public boolean checkLoginAndPass(String login, String pass) throws ServiceException {
        UserDAOImpl userDAO = UserDAOImpl.getInstance();
        List<User> users;
        try {
            users = userDAO.findAllUsers();
            for (User user : users) {
                if (login.equals(user.getLogin()) && pass.equals(user.getPass())) {
                    return true;
                }
            }
        } catch (DAOException e) {
            throw new ServiceException("Failed to find all users (check login)", e);
        }
        return false;
    }

    @Override
    public boolean checkEnteredValues(String enterLogin, String enterPass, String enterEmail, String enterName, String enterSurname) throws ServiceException {
        return Validator.validateLogin(enterLogin) && Validator.validatePass(enterPass) && Validator.validateEmail(enterEmail) &&
                Validator.validateName(enterName) && Validator.validateSurname(enterSurname);
    }
}
