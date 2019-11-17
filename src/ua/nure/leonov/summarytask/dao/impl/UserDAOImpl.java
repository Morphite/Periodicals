package ua.nure.leonov.summarytask.dao.impl;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.dao.UserDAO;
import ua.nure.leonov.summarytask.dao.exception.DAOException;
import ua.nure.leonov.summarytask.entity.User;
import ua.nure.leonov.summarytask.pool.ConnectionPool;
import ua.nure.leonov.summarytask.pool.ConnectionPoolException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private final static Logger LOG = Logger.getLogger(UserDAOImpl.class);

    private static final String SQL_SELECT_ALL_USERS = "SELECT user_id,login,pass,email,name,surname,admin,ban,balance " +
            "FROM user";

    private static final String SQL_SELECT_USER_BY_LOGIN = "SELECT user_id,login,pass,email,name,surname,admin,ban,balance " +
            "FROM user WHERE login=?";

    private static final String SQL_SELECT_USER_BY_ID = "SELECT user_id,login,pass,email,name,surname,admin,ban,balance " +
            "FROM user WHERE user_id=?";

    private static final String SQL_SELECT_USER_PASSWORD_BY_ID = "SELECT pass FROM user WHERE user_id=?";

    private static final String SQL_UPDATE_USER_PASSWORD_BY_ID = "UPDATE user SET pass=? WHERE user_id=?";

    private static final String SQL_SELECT_MONEY_BY_USER_ID = "SELECT balance FROM user WHERE user_id=?";

    private static final String SQL_INSERT_USER = "INSERT INTO user(login,pass,email,name,surname,admin,ban,balance) " +
            "VALUES(?,?,?,?,?,?,?,?)";

    private static final String SQL_UPDATE_USER = "UPDATE user SET login=?,pass=?,email=?,name=?,surname=?,admin=?,ban=?,balance=?" +
            "WHERE (user_id=?)";

    private static final String SQL_DELETE_USER = "DELETE FROM user WHERE user_id=?";

    private static final String SQL_UPDATE_USER_BALANCE = "UPDATE user SET balance=? WHERE user_id=?";

    private static final String SQL_UPDATE_USER_BALANCE_ADDITION = "UPDATE user SET balance=balance + ? WHERE user_id=?";

    private static final String PARAM_ID_USER = "user_id";
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "pass";
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_SURNAME = "surname";
    private static final String PARAM_ADMIN = "admin";
    private static final String PARAM_BAN = "ban";
    private static final String PARAM_BALANCE = "balance";

    private UserDAOImpl() {}

    private static class UserDAOImplHolder {
        private static final UserDAOImpl HOLDER_INSTANCE = new UserDAOImpl();
    }

    public static UserDAOImpl getInstance() {
        return UserDAOImplHolder.HOLDER_INSTANCE;
    }

    @Override
    public List<User> findAllUsers() throws DAOException {
        List<User> users = new ArrayList<>();
        try (Connection conn = ConnectionPool.getInstance().getConnection();
             Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(SQL_SELECT_ALL_USERS);
            while (rs.next()) {
                users.add(createUser(rs));
            }
            LOG.info("Get all users");
        } catch (SQLException e) {
            throw new DAOException("SQL Exception (Find all users failed)" + e, e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool error" + e, e);
        }
        return users;
    }

    @Override
    public User findEntityByLogin(String login) throws DAOException {
        User user = new User();
        try (Connection conn = ConnectionPool.getInstance().getConnection();
             PreparedStatement prStmt = conn.prepareStatement(SQL_SELECT_USER_BY_LOGIN)) {
            prStmt.setString(1, login);
            ResultSet rs = prStmt.executeQuery();
            while (rs.next()) {
                user = createUser(rs);
            }
            LOG.info("Get user by login" + login);
        } catch (SQLException e) {
            throw new DAOException("SQL Exception (find user by login failed)" + e, e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool error" + e, e);
        }
        return user;
    }

    @Override
    public double findBalanceById(Long id) throws DAOException {
        double money = 0;
        try (Connection cn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL_SELECT_MONEY_BY_USER_ID)) {
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                money = resultSet.getDouble(PARAM_BALANCE);
            }
            LOG.info("Get balance by id: " + id);
        } catch (SQLException e) {
            throw new DAOException("SQL Exception (find balance by id failed)" + e, e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool error" + e, e);
        }
        return money;
    }

    @Override
    public String findPassById(Long id) throws DAOException {
        String pass = "";
        try (Connection cn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL_SELECT_USER_PASSWORD_BY_ID)) {
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                pass = resultSet.getString(PARAM_PASSWORD);
            }
            LOG.info("Get pass by id: " + id);
        } catch (SQLException e) {
            throw new DAOException("SQL Exception (find pass by id failed)" + e, e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool error" + e, e);
        }
        return pass;
    }

    @Override
    public boolean updatePassById(Long id, String pass) throws DAOException {
        try (Connection cn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL_UPDATE_USER_PASSWORD_BY_ID)) {
            ps.setString(1, pass);
            ps.setLong(2, id);
            LOG.info("Update pass by id: " + id);
            return (ps.executeUpdate() != 0);
        } catch (SQLException e) {
            throw new DAOException("SQL Exception (update pass by id failed)" + e, e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool error" + e, e);
        }
    }

    @Override
    public boolean updateUserBalanceAdd(Long id, double money) throws DAOException {
        try (Connection cn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL_UPDATE_USER_BALANCE_ADDITION)) {
            ps.setDouble(1, money);
            ps.setLong(2, id);
            LOG.info("Update balance by id: " + id + " ==> money " + money);
            return (ps.executeUpdate() != 0);
        } catch (SQLException e) {
            throw new DAOException("SQL Exception (update balance by id failed (adding))" + e, e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool error" + e, e);
        }
    }

    @Override
    public boolean updateUserBalance(Long id, double newBalance) throws DAOException {
        try (Connection cn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL_UPDATE_USER_BALANCE)) {
            ps.setDouble(1, newBalance);
            ps.setLong(2, id);
            LOG.info("Update balance by id: " + id + "Balance=" + newBalance);
            return (ps.executeUpdate() != 0);
        } catch (SQLException e) {
            throw new DAOException("SQL Exception (update balance by id failed (purchase)" + e, e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool error" + e, e);
        }
    }

    @Override
    public User findEntityById(Long id) throws DAOException {
        User user = new User();
        try (Connection conn = ConnectionPool.getInstance().getConnection();
             PreparedStatement prStmt = conn.prepareStatement(SQL_SELECT_USER_BY_ID)) {
            prStmt.setLong(1, id);
            ResultSet rs = prStmt.executeQuery();
            while (rs.next()) {
                user = createUser(rs);
            }
            LOG.info("Find user by id: " + id);
        } catch (SQLException e) {
            throw new DAOException("SQL Exception (find user by id failed)" + e, e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool error" + e, e);
        }
        return user;
    }

    @Override
    public boolean delete(Long id) throws DAOException {
        try (Connection cn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL_DELETE_USER)) {
            ps.setLong(1, id);
            LOG.info("Delete user by id: " + id);
            return (ps.executeUpdate() != 0);
        } catch (SQLException e) {
            throw new DAOException("SQL Exception (delete user by id failed)" + e, e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool error" + e, e);
        }
    }

    @Override
    public boolean create(User entity) throws DAOException {
        try (Connection cn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL_INSERT_USER)) {
            ps.setString(1, entity.getLogin());
            ps.setString(2, entity.getPass());
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getName());
            ps.setString(5, entity.getSurname());
            ps.setBoolean(6, entity.getAdmin());
            ps.setBoolean(7, entity.getBan());
            ps.setDouble(8, entity.getBalance());
            LOG.info("Create new user");
            return (ps.executeUpdate() != 0);
        } catch (SQLException e) {
            throw new DAOException("SQL Exception (Create user failed)" + e, e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool error" + e, e);
        }
    }

    @Override
    public boolean update(User entity) throws DAOException {
        try (Connection cn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL_UPDATE_USER)) {
            ps.setString(1, entity.getLogin());
            ps.setString(2, entity.getPass());
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getName());
            ps.setString(5, entity.getSurname());
            ps.setBoolean(6, entity.getAdmin());
            ps.setBoolean(7, entity.getBan());
            ps.setDouble(8, entity.getBalance());
            ps.setLong(9, entity.getId());
            LOG.info("Update user by id: " + entity.getId());
            return (ps.executeUpdate() != 0);
        } catch (SQLException e) {
            throw new DAOException("SQL Exception (update user by id failed)" + e, e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool error" + e, e);
        }
    }

    private User createUser(ResultSet resultSet) throws DAOException {
        try {
            User user = new User();
            user.setId(resultSet.getLong(PARAM_ID_USER));
            user.setLogin(resultSet.getString(PARAM_LOGIN));
            user.setPass(resultSet.getString(PARAM_PASSWORD));
            user.setEmail(resultSet.getString(PARAM_EMAIL));
            user.setName(resultSet.getString(PARAM_NAME));
            user.setSurname(resultSet.getString(PARAM_SURNAME));
            user.setAdmin(resultSet.getBoolean(PARAM_ADMIN));
            user.setBan(resultSet.getBoolean(PARAM_BAN));
            user.setBalance(resultSet.getDouble(PARAM_BALANCE));
            return user;
        } catch (SQLException e) {
            throw new DAOException("SQL Exception (Create user from ResultSet failed)" + e, e);
        }
    }
}
