package ua.nure.leonov.summarytask.service;

import ua.nure.leonov.summarytask.entity.Theme;
import ua.nure.leonov.summarytask.service.exception.ServiceException;

import java.util.List;

public interface ThemeService {

    List<Theme> findAllThemes() throws ServiceException;

    Theme findThemeById(int id) throws ServiceException;

    boolean createTheme(String name, String imgPath) throws ServiceException;

    boolean updateTheme(int id, String name, String imgPath) throws ServiceException;

    boolean deleteTheme(int id) throws ServiceException;
}
