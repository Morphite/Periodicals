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

public class EditThemeCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(EditThemeCommand.class);

    private static final String PARAM_THEME_ID = "id";

    private static final String PARAM_THEME_NAME = "name";

    private static final String PARAM_THEME_IMG_PATH = "imgPath";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        int id = Integer.parseInt(req.getParameter(PARAM_THEME_ID));
        String name = req.getParameter(PARAM_THEME_NAME);
        String imgPath = req.getParameter(PARAM_THEME_IMG_PATH);

        ThemeServiceImpl themeService = new ThemeServiceImpl();
        List<Theme> themes;
        try {
            LOG.info("Try to edit theme named: " + name);
            if (themeService.updateTheme(id, name, imgPath)) {
                themes = themeService.findAllThemes();
                req.setAttribute("themes", themes);
                LOG.info("Edit theme process named: " + name + " succeeded");
                return ConfigurationManager.getProperty("path.page.admin.list.theme");
            } else {
                req.setAttribute("errorThemeCreate",
                        MainController.messageManager.getProperty("messages.createediterror"));
                return ConfigurationManager.getProperty("path.page.admin.edit.theme");
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
