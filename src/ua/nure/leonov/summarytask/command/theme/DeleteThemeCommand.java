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

public class DeleteThemeCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(DeleteThemeCommand.class);

    private static final String PARAM_SUB_ID = "id";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        int id = Integer.parseInt(req.getParameter(PARAM_SUB_ID));

        ThemeServiceImpl themeService = new ThemeServiceImpl();
        List<Theme> themes;
        try {
            LOG.info("Try to delete theme with id=" + id);
            if (themeService.deleteTheme(id)) {
                themes = themeService.findAllThemes();
                req.setAttribute("themes", themes);
                LOG.info("Delete theme process with id= " + id + " succeeded");
                return ConfigurationManager.getProperty("path.page.admin.list.theme");
            } else {
                req.setAttribute("errorUserEdit",
                        MainController.messageManager.getProperty("message.deletethemeerror"));
                return ConfigurationManager.getProperty("path.page.admin.list.theme");
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
