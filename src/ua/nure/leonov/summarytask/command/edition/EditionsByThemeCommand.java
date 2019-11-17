package ua.nure.leonov.summarytask.command.edition;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.command.ActionCommand;
import ua.nure.leonov.summarytask.command.exception.CommandException;
import ua.nure.leonov.summarytask.entity.Edition;
import ua.nure.leonov.summarytask.entity.Subscription;
import ua.nure.leonov.summarytask.entity.Theme;
import ua.nure.leonov.summarytask.manager.ConfigurationManager;
import ua.nure.leonov.summarytask.service.exception.ServiceException;
import ua.nure.leonov.summarytask.service.impl.EditionServiceImpl;
import ua.nure.leonov.summarytask.service.impl.SubServiceImpl;
import ua.nure.leonov.summarytask.service.impl.ThemeServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class EditionsByThemeCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(EditionsByThemeCommand.class);

    private static final String PARAM_THEME_ID = "theme_id";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        int theme_id = Integer.parseInt(req.getParameter(PARAM_THEME_ID));

        EditionServiceImpl editionService = new EditionServiceImpl();
        SubServiceImpl subService = new SubServiceImpl();
        ThemeServiceImpl themeService = new ThemeServiceImpl();
        List<Edition> editions;
        List<Subscription> subs;
        List<Theme> themes;
        HttpSession session = req.getSession();
        long id = (long)session.getAttribute("user_id");
        try {
            LOG.debug("Get editions by theme");
            editions = editionService.findEditionsByTheme(theme_id);
            subs = subService.findAllSubByUserId((int) id);
            themes = themeService.findAllThemes();
            req.setAttribute("editions", editions);
            req.setAttribute("subs", subs);
            req.setAttribute("themes", themes);
            req.setAttribute("prevThemeId", theme_id);
            return ConfigurationManager.getProperty("path.page.editions");
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
