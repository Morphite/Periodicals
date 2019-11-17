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
import java.util.List;

public class ThemeListCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(ThemeListCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        ThemeServiceImpl themeService = new ThemeServiceImpl();

        try {
            LOG.info("Get all themes from DB for admin");
            List<Theme> themes = themeService.findAllThemes();
            req.setAttribute("themes", themes);
            return ConfigurationManager.getProperty("path.page.admin.list.theme");
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
