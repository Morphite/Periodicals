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

public class EditionListCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(EditionListCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        EditionServiceImpl editionService = new EditionServiceImpl();
        SubServiceImpl subService = new SubServiceImpl();
        ThemeServiceImpl themeService = new ThemeServiceImpl();
        List<Edition> editions;
        List<Subscription> subs;
        List<Theme> themes;
        HttpSession session = req.getSession();
        long id = (long)session.getAttribute("user_id");
        try {
            LOG.debug("Try to get all editions from DB");
            editions = editionService.findAllEditions();
            subs = subService.findAllSubByUserId((int) id);
            themes = themeService.findAllThemes();
            req.setAttribute("editions", editions);
            req.setAttribute("subs", subs);
            req.setAttribute("themes", themes);
            return ConfigurationManager.getProperty("path.page.editions");
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
