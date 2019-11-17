package ua.nure.leonov.summarytask.dao;

import ua.nure.leonov.summarytask.dao.exception.DAOException;
import ua.nure.leonov.summarytask.entity.Theme;

import java.util.List;

public interface ThemeDAO extends MainDAO<Integer, Theme> {

    List<Theme> findAllThemes() throws DAOException;
}
