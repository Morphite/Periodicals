package ua.nure.leonov.summarytask.dao.impl;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.dao.EditionDAO;
import ua.nure.leonov.summarytask.dao.exception.DAOException;
import ua.nure.leonov.summarytask.entity.Edition;
import ua.nure.leonov.summarytask.entity.Theme;
import ua.nure.leonov.summarytask.pool.ConnectionPool;
import ua.nure.leonov.summarytask.pool.ConnectionPoolException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class EditionDAOImpl implements EditionDAO {

    private final static Logger LOG = Logger.getLogger(EditionDAOImpl.class);

    private static final String SQL_SELECT_ALL_EDITIONS_W_THEME_NAME = "SELECT edition_id, theme.name, title, publisher, text, price, theme.imgPath FROM edition JOIN theme ON edition.theme_id=theme.theme_id ORDER BY edition_id";

    private static final String SQL_SORT_NAME = "SELECT edition_id, theme.name, title, publisher, text, price, theme.imgPath FROM edition JOIN theme ON edition.theme_id=theme.theme_id ORDER BY title";

    private static final String SQL_SORT_THEME = "SELECT edition_id, theme.name, title, publisher, text, price, theme.imgPath FROM edition JOIN theme ON edition.theme_id=theme.theme_id ORDER BY theme.name";

    private static final String SQL_SORT_PRICE = "SELECT edition_id, theme.name, title, publisher, text, price, theme.imgPath FROM edition JOIN theme ON edition.theme_id=theme.theme_id ORDER BY price";

    private static final String SQL_SELECT_EDITION_BY_ID = "SELECT edition_id, theme.name, title, publisher, text, price, theme.imgPath FROM edition JOIN theme ON edition.theme_id=theme.theme_id WHERE edition_id=?";

    private static final String SQL_SELECT_EDITION_BY_THEME = "SELECT edition_id, theme.name, title, publisher, text, price, theme.imgPath FROM edition JOIN theme ON edition.theme_id=theme.theme_id WHERE theme.theme_id=?";

    private static final String SQL_INSERT_EDITION = "INSERT INTO edition(theme_id,title,publisher,text,price) VALUES(?,?,?,?,?)";

    private static final String SQL_UPDATE_EDITION = "UPDATE edition SET theme_id=?,title=?,publisher=?,text=?,price=? WHERE (edition_id=?)";

    private static final String SQL_DELETE_EDITION = "DELETE FROM edition WHERE edition_id=?";

    private static final HashMap<String, String> mapForSortCriterion = new HashMap<>();

    private EditionDAOImpl() {
        mapForSortCriterion.put("name", SQL_SORT_NAME);
        mapForSortCriterion.put("theme", SQL_SORT_THEME);
        mapForSortCriterion.put("price", SQL_SORT_PRICE);
    }

    private static class EditionDAOImplHolder {
        private static final EditionDAOImpl HOLDER_INSTANCE = new EditionDAOImpl();
    }

    public static EditionDAOImpl getInstance() {
        return EditionDAOImplHolder.HOLDER_INSTANCE;
    }

    @Override
    public List<Edition> findAllEditions() throws DAOException {
        List<Edition> editions = new ArrayList<>();
        try (Connection conn = ConnectionPool.getInstance().getConnection();
             Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(SQL_SELECT_ALL_EDITIONS_W_THEME_NAME);
            while (rs.next()) {
                editions.add(createEdition(rs));
            }
            LOG.info("Get all editions");
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Find all editions exception" + e, e);
        }
        return editions;
    }

    @Override
    public List<Edition> sortAllEditions(String crit, boolean order) throws DAOException {
        List<Edition> editions = new ArrayList<>();
        try (Connection cn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(mapForSortCriterion.getOrDefault(crit, SQL_SELECT_ALL_EDITIONS_W_THEME_NAME))) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                editions.add(createEdition(rs));
            }
            if (!order) {
                Collections.reverse(editions);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): " + e, e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception" + e, e);
        }
        return editions;
    }

    @Override
    public List<Edition> findEditionsByTheme(int id) throws DAOException {
        List<Edition> editions = new ArrayList<>();
        try (Connection conn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_EDITION_BY_THEME)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                editions.add(createEdition(rs));
            }
            LOG.info("Get all editions with theme id= " + id);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Get all editions with theme id" + e, e);
        }
        return editions;
    }


    @Override
    public Edition findEntityById(Long id) throws DAOException {
        Edition edition = new Edition();
        try (Connection conn = ConnectionPool.getInstance().getConnection();
             PreparedStatement prStmt = conn.prepareStatement(SQL_SELECT_EDITION_BY_ID)) {
            prStmt.setLong(1, id);
            ResultSet rs = prStmt.executeQuery();
            while (rs.next()) {
                edition = createEdition(rs);
            }
            LOG.info("Find edition by id: " + id);
        } catch (SQLException e) {
            throw new DAOException("SQL Exception (find edition by id failed)" + e, e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception" + e, e);
        }
        return edition;
    }

    @Override
    public boolean delete(Long id) throws DAOException {
        try (Connection cn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL_DELETE_EDITION)) {
            ps.setLong(1, id);
            LOG.info("Delete edition by id: " + id);
            return (ps.executeUpdate() != 0);
        } catch (SQLException e) {
            throw new DAOException("SQL Exception (delete edition by id failed)" + e, e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception" + e, e);
        }
    }

    @Override
    public boolean create(Edition entity) throws DAOException {
        try (Connection cn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL_INSERT_EDITION)) {
            ps.setInt(1, entity.getTheme().getId());
            ps.setString(2, entity.getTitle());
            ps.setString(3, entity.getPublisher());
            ps.setString(4, entity.getText());
            ps.setDouble(5, entity.getPrice());
            LOG.info("Create new edition");
            return (ps.executeUpdate() != 0);
        } catch (SQLException e) {
            throw new DAOException("SQL Exception (Create edition failed)" + e, e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception" + e, e);
        }
    }

    @Override
    public boolean update(Edition entity) throws DAOException {
        try (Connection cn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL_UPDATE_EDITION)) {
            ps.setInt(1, entity.getTheme().getId());
            ps.setString(2, entity.getTitle());
            ps.setString(3, entity.getPublisher());
            ps.setString(4, entity.getText());
            ps.setDouble(5, entity.getPrice());
            ps.setInt(6, entity.getId());
            LOG.info("Update edition");
            return (ps.executeUpdate() != 0);
        } catch (SQLException e) {
            throw new DAOException("SQL Exception (Update edition failed)" + e, e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception" + e, e);
        }
    }

    private Edition createEdition(ResultSet resultSet) throws DAOException {
        try {
            Edition edition = new Edition();
            edition.setId(resultSet.getInt(1));

            Theme theme = new Theme();
            theme.setName(resultSet.getString(2));
            theme.setImgPath(resultSet.getString(7));
            edition.setTheme(theme);

            edition.setTitle(resultSet.getString(3));
            edition.setPublisher(resultSet.getString(4));
            edition.setText(resultSet.getString(5));
            edition.setPrice(resultSet.getDouble(6));

            return edition;
        } catch (SQLException e) {
            throw new DAOException("SQL Exception (create edition from ResultSet failed)" + e, e);
        }


    }
}
