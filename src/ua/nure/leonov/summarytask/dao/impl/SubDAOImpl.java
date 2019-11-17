package ua.nure.leonov.summarytask.dao.impl;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.dao.SubDAO;
import ua.nure.leonov.summarytask.dao.exception.DAOException;
import ua.nure.leonov.summarytask.entity.Edition;
import ua.nure.leonov.summarytask.entity.Subscription;
import ua.nure.leonov.summarytask.entity.Theme;
import ua.nure.leonov.summarytask.entity.User;
import ua.nure.leonov.summarytask.pool.ConnectionPool;
import ua.nure.leonov.summarytask.pool.ConnectionPoolException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubDAOImpl implements SubDAO {

    private final static Logger LOG = Logger.getLogger(SubDAOImpl.class);

    private static final String SQL_SELECT_ALL_SUBS = "SELECT subscription_id, edition.title, user.login, date FROM subscription JOIN edition ON subscription.edition_id=edition.edition_id JOIN user ON subscription.user_id=user.user_id ORDER BY subscription_id";

    private static final String SQL_SELECT_ALL_SUBS_BY_USER_TITLE = "SELECT subscription_id, e.title, t.name, date FROM subscription JOIN edition e ON subscription.edition_id=e.edition_id JOIN theme t on t.theme_id = e.theme_id WHERE subscription.user_id=?";


    private static final String SQL_SELECT_ALL_SUBS_BY_USER_ID = "SELECT subscription_id, user_id, edition_id, date FROM subscription WHERE user_id=?";

    private static final String SQL_SELECT_SUB_BY_ID = "SELECT user_id, edition_id, date FROM subscription WHERE subscription_id=?";

    private static final String SQL_SELECT_ALL_PURCHASED_EDITION_ID = "SELECT edition_id FROM subscription WHERE user_id=?";

    private static final String SQL_INSERT_SUB = "INSERT INTO subscription(user_id,edition_id,date) VALUES(?,?,?)";

    private static final String SQL_UPDATE_SUB = "UPDATE subscription SET user_id=?,edition_id=?,date=? WHERE (subscription_id=?)";

    private static final String SQL_DELETE_SUB = "DELETE FROM subscription WHERE subscription_id=?";


    private static final String PARAM_ID_SUB = "subscription_id";
    private static final String PARAM_ID_USER = "user_id";
    private static final String PARAM_ID_EDITION = "edition_id";
    private static final String PARAM_TITLE_EDITION = "edition_title";
    private static final String PARAM_DATE = "date";

    private SubDAOImpl() {
    }

    private static class SubDAOImplHolder {
        private static final SubDAOImpl HOLDER_INSTANCE = new SubDAOImpl();
    }

    public static SubDAOImpl getInstance() {
        return SubDAOImplHolder.HOLDER_INSTANCE;
    }

    @Override
    public List<Subscription> findAllSubs() throws DAOException {
        List<Subscription> subs = new ArrayList<>();
        try (Connection conn = ConnectionPool.getInstance().getConnection();
             Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(SQL_SELECT_ALL_SUBS);
            while (rs.next()) {
                subs.add(createSub(rs));
            }
            LOG.info("Get all subs");
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Error during findAllSubs" + e, e);
        }
        return subs;
    }

    @Override
    public List<Subscription> findAllSubsByUserWithEditionId(int id) throws DAOException {
        List<Subscription> subs = new ArrayList<>();
        try (Connection conn = ConnectionPool.getInstance().getConnection();
             PreparedStatement prSt = conn.prepareStatement(SQL_SELECT_ALL_SUBS_BY_USER_ID)) {
            prSt.setInt(1, id);
            ResultSet rs = prSt.executeQuery();
            while (rs.next()) {
                Subscription subscription = new Subscription();
                subscription.setId(rs.getInt(1));

                User user = new User();
                user.setId(rs.getLong(2));
                subscription.setUser(user);

                Edition edition = new Edition();
                edition.setId(rs.getInt(3));
                subscription.setEdition(edition);

                subscription.setDate(rs.getString(4));
                subs.add(subscription);
            }
            LOG.info("Get all subs by user");
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Error during findAllSubs by user with editionID" + e, e);
        }
        return subs;
    }

    @Override
    public List<Subscription> findAllSubsByUserWithEditionTitle(int id) throws DAOException {
        List<Subscription> subs = new ArrayList<>();
        try (Connection conn = ConnectionPool.getInstance().getConnection();
             PreparedStatement prSt = conn.prepareStatement(SQL_SELECT_ALL_SUBS_BY_USER_TITLE)) {
            prSt.setInt(1, id);
            ResultSet rs = prSt.executeQuery();
            while (rs.next()) {
                Subscription subscription = new Subscription();
                subscription.setId(rs.getInt(1));

                Edition edition = new Edition();
                edition.setTitle(rs.getString(2));
                subscription.setEdition(edition);

                Theme theme = new Theme();
                theme.setName(rs.getString(3));
                edition.setTheme(theme);

                subscription.setDate(rs.getString(4));
                subs.add(subscription);
            }
            LOG.info("Get all subs by user");
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Error during findAllSubs with edition title" + e, e);
        }
        return subs;
    }

    @Override
    public List<Integer> findAllPurchasedEditionsId(int id) throws DAOException {
        List<Integer> editionsId = new ArrayList<>();
        try (Connection conn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_ALL_PURCHASED_EDITION_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                editionsId.add(rs.getInt(1));
            }
            LOG.info("Get all purchased editions");
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Error during findAllPurchasedEditions by id" + e, e);
        }
        return editionsId;
    }

    @Override
    public Subscription findEntityById(Long id) throws DAOException {
        Subscription sub = new Subscription();
        try (Connection conn = ConnectionPool.getInstance().getConnection();
             PreparedStatement prStmt = conn.prepareStatement(SQL_SELECT_SUB_BY_ID)) {
            prStmt.setLong(1, id);
            ResultSet rs = prStmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong(1));
                sub.setUser(user);

                Edition edition = new Edition();
                edition.setTitle(rs.getString(2));
                sub.setEdition(edition);

                sub.setDate(rs.getString(3));
            }
            LOG.info("Find subscription by id: " + id);
        } catch (SQLException e) {
            throw new DAOException("SQL Exception (find subscription by id failed)" + e, e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool error" + e, e);
        }
        return sub;
    }

    @Override
    public boolean delete(Long id) throws DAOException {
        try (Connection cn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL_DELETE_SUB)) {
            ps.setLong(1, id);
            LOG.info("Delete sub by id: " + id);
            return (ps.executeUpdate() != 0);
        } catch (SQLException e) {
            throw new DAOException("SQL Exception (delete sub by id failed)" + e, e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool error" + e, e);
        }
    }

    @Override
    public boolean create(Subscription entity) throws DAOException {
        try (Connection cn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL_INSERT_SUB)) {
            ps.setInt(1, (int) entity.getUser().getId());
            ps.setInt(2, entity.getEdition().getId());
            ps.setString(3, entity.getDate());
            LOG.info("Create new sub");
            return (ps.executeUpdate() != 0);
        } catch (SQLException e) {
            throw new DAOException("SQL Exception (Create sub failed)" + e, e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool error" + e, e);
        }
    }

    @Override
    public boolean update(Subscription entity) throws DAOException {
        throw new UnsupportedOperationException();
    }

    private Subscription createSub(ResultSet resultSet) throws DAOException {
        try {
            Subscription subscription = new Subscription();
            subscription.setId(resultSet.getInt(1));

            Edition edition = new Edition();
            edition.setTitle(resultSet.getString(2));
            subscription.setEdition(edition);

            User user = new User();
            user.setLogin(resultSet.getString(3));
            subscription.setUser(user);

            subscription.setDate(resultSet.getString(4));

            return subscription;
        } catch (SQLException e) {
            throw new DAOException("SQL exception (create sub from ResultSet failed): " + e, e);
        }
    }
}
