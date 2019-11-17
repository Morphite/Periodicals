package ua.nure.leonov.summarytask.command.theme;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.command.ActionCommand;
import ua.nure.leonov.summarytask.command.exception.CommandException;
import ua.nure.leonov.summarytask.entity.Theme;
import ua.nure.leonov.summarytask.manager.ConfigurationManager;
import ua.nure.leonov.summarytask.service.exception.ServiceException;
import ua.nure.leonov.summarytask.service.impl.ThemeServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageEditThemeCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(PageEditThemeCommand.class);

    private static final String PARAM_THEME_ID = "id";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        int id = Integer.parseInt(req.getParameter(PARAM_THEME_ID));

        ThemeServiceImpl themeService = new ThemeServiceImpl();
        Theme theme;
        try {
            LOG.info("Go to admin edit theme page with id " + id);
            theme = themeService.findThemeById(id);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        if (id == theme.getId()) {
            req.setAttribute("themeForEdit", theme);
            return ConfigurationManager.getProperty("path.page.admin.edit.theme");
        } else {
            return ConfigurationManager.getProperty("path.page.admin.panel");
        }
    }
}
