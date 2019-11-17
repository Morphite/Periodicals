package ua.nure.leonov.summarytask.command.theme;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.command.ActionCommand;
import ua.nure.leonov.summarytask.command.exception.CommandException;
import ua.nure.leonov.summarytask.controller.MainController;
import ua.nure.leonov.summarytask.entity.Theme;
import ua.nure.leonov.summarytask.manager.ConfigurationManager;
import ua.nure.leonov.summarytask.service.exception.ServiceException;
import ua.nure.leonov.summarytask.service.impl.ThemeServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CreateThemeCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(CreateThemeCommand.class);

    private static final String PARAM_THEME_NAME = "name";

    private static final String PARAM_THEME_IMG_PATH = "imgPath";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String name = req.getParameter(PARAM_THEME_NAME);
        String imgPath = req.getParameter(PARAM_THEME_IMG_PATH);

        ThemeServiceImpl themeService = new ThemeServiceImpl();
        List<Theme> themes;
        try {
            LOG.info("Try to create new theme named: " + name);
            if (themeService.createTheme(name, imgPath)) {
                themes = themeService.findAllThemes();
                req.setAttribute("themes", themes);
                LOG.info("Create theme process named: " + name + " success");
                return ConfigurationManager.getProperty("path.page.admin.list.theme");
            } else {
                req.setAttribute("errorThemeCreate",
                        MainController.messageManager.getProperty("messages.createthemeerror"));
                return ConfigurationManager.getProperty("path.page.admin.create.theme");
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
