package ua.nure.leonov.summarytask.command.edition;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.command.ActionCommand;
import ua.nure.leonov.summarytask.command.exception.CommandException;
import ua.nure.leonov.summarytask.entity.Edition;
import ua.nure.leonov.summarytask.entity.Theme;
import ua.nure.leonov.summarytask.manager.ConfigurationManager;
import ua.nure.leonov.summarytask.service.exception.ServiceException;
import ua.nure.leonov.summarytask.service.impl.EditionServiceImpl;
import ua.nure.leonov.summarytask.service.impl.ThemeServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PageEditEditionCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(PageEditEditionCommand.class);

    private static final String PARAM_EDITION_ID = "id";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        int id = Integer.parseInt(req.getParameter(PARAM_EDITION_ID));
        EditionServiceImpl editionService = new EditionServiceImpl();
        ThemeServiceImpl themeService = new ThemeServiceImpl();
        Edition edition;
        List<Theme> themes;
        try {
            LOG.info("Go to admin edit user page with id " + id);
            themes = themeService.findAllThemes();
            edition = editionService.findEditionById(id);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        if (id == edition.getId()) {
            req.setAttribute("themes", themes);
            req.setAttribute("editForEdit", edition);
            return ConfigurationManager.getProperty("path.page.admin.edit.edit");
        } else {
            return ConfigurationManager.getProperty("path.page.admin.panel");
        }
    }
}
