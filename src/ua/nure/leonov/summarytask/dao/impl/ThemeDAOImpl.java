package ua.nure.leonov.summarytask.dao.impl;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.dao.ThemeDAO;
import ua.nure.leonov.summarytask.dao.exception.DAOException;
import ua.nure.leonov.summarytask.entity.Theme;
import ua.nure.leonov.summarytask.pool.ConnectionPool;
import ua.nure.leonov.summarytask.pool.ConnectionPoolException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ThemeDAOImpl implements ThemeDAO {

    private final static Logger LOG = Logger.getLogger(ThemeDAOImpl.class);

    private final static String SQL_SELECT_ALL_THEMES = "SELECT theme_id, name, imgPath FROM theme";

    private final static String SQL_SELECT_THEME_BY_ID = "SELECT theme_id, name, imgPath FROM theme WHERE theme_id=?";

    private final static String SQL_CREATE_THEME = "INSERT INTO theme(name,imgPath) VALUES(?,?)";

    private final static String SQL_UPDATE_THEME = "UPDATE theme SET name=?,imgPath=? WHERE (theme_id=?)";

    private final static String SQL_DELETE_THEME = "DELETE FROM theme WHERE theme_id=?";

    private ThemeDAOImpl() {}

    private static class ThemeDAOImplHOLDER{
        private static final ThemeDAOImpl HOLDER_INSTANCE = new ThemeDAOImpl();
    }

    public static ThemeDAOImpl getInstance(){
        return ThemeDAOImplHOLDER.HOLDER_INSTANCE;
    }

    @Override
    public List<Theme> findAllThemes() throws DAOException {
        List<Theme> editions = new ArrayList<>();
        try (Connection conn = ConnectionPool.getInstance().getConnection();
             Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(SQL_SELECT_ALL_THEMES);
            while (rs.next()) {
                editions.add(createTheme(rs));
            }
            LOG.info("Get all themes");
        } catch (SQLException e) {
            throw new DAOException("SQL Exception (Find all themes failed)" + e, e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool error" + e, e);
        }
        return editions;
    }

    @Override
    public Theme findEntityById(Integer id) throws DAOException {
        Theme theme = new Theme();
        try (Connection cn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL_SELECT_THEME_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                theme = createTheme(rs);
            }
            LOG.info("Find theme by id: " + id);
            return theme;
        } catch (SQLException e) {
            throw new DAOException("SQL Exception (find theme by id failed)" + e, e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool error" + e, e);
        }
    }

    @Override
    public boolean delete(Integer id) throws DAOException {
        try (Connection cn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL_DELETE_THEME)) {
            ps.setInt(1, id);
            LOG.info("Delete theme by id: " + id);
            return (ps.executeUpdate() != 0);
        } catch (SQLException e) {
            throw new DAOException("SQL Exception (delete theme by id failed)" + e, e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool error" + e, e);
        }
    }

    @Override
    public boolean create(Theme entity) throws DAOException {
        try (Connection cn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL_CREATE_THEME)) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getImgPath());
            LOG.info("Create theme");
            return (ps.executeUpdate() != 0);
        } catch (SQLException e) {
            throw new DAOException("SQL Exception (Create theme failed)" + e, e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool error" + e, e);
        }
    }

    @Override
    public boolean update(Theme entity) throws DAOException {
        try (Connection cn = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL_UPDATE_THEME)) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getImgPath());
            ps.setInt(3, entity.getId());
            LOG.info("Update theme with id=" + entity.getId());
            return (ps.executeUpdate() != 0);
        } catch (SQLException e) {
            throw new DAOException("SQL Exception (Update theme failed)" + e, e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool error" + e, e);
        }
    }

    private Theme createTheme(ResultSet rs) throws DAOException {
        try {
            Theme theme = new Theme();
            theme.setId(rs.getInt(1));
            theme.setName(rs.getString(2));
            theme.setImgPath(rs.getString(3));
            return theme;
        } catch (SQLException e) {
            throw new DAOException("SQL exception (create theme from ResultSet failed): " + e, e);
        }
    }
}
