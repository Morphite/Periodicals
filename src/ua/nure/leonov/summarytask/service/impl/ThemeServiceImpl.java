package ua.nure.leonov.summarytask.service.impl;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.dao.exception.DAOException;
import ua.nure.leonov.summarytask.dao.impl.ThemeDAOImpl;
import ua.nure.leonov.summarytask.entity.Theme;
import ua.nure.leonov.summarytask.service.ThemeService;
import ua.nure.leonov.summarytask.service.exception.ServiceException;

import java.util.List;

public class ThemeServiceImpl implements ThemeService {

    private final static Logger LOG = Logger.getLogger(ThemeServiceImpl.class);

    @Override
    public List<Theme> findAllThemes() throws ServiceException {
        try {
            return ThemeDAOImpl.getInstance().findAllThemes();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Theme findThemeById(int id) throws ServiceException {
        try {
            return ThemeDAOImpl.getInstance().findEntityById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean createTheme(String name, String imgPath) throws ServiceException {
        Theme theme = new Theme();
        theme.setName(name);
        theme.setImgPath(imgPath);
        try {
            return ThemeDAOImpl.getInstance().create(theme);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateTheme(int id, String name, String imgPath) throws ServiceException {
        Theme theme = new Theme();
        theme.setId(id);
        theme.setName(name);
        theme.setImgPath(imgPath);
        try {
            return ThemeDAOImpl.getInstance().update(theme);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteTheme(int id) throws ServiceException {
        try {
            return ThemeDAOImpl.getInstance().delete(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
