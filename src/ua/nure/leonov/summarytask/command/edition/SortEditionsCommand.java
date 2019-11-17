package ua.nure.leonov.summarytask.command.edition;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.command.ActionCommand;
import ua.nure.leonov.summarytask.command.exception.CommandException;
import ua.nure.leonov.summarytask.entity.Edition;
import ua.nure.leonov.summarytask.entity.Subscription;
import ua.nure.leonov.summarytask.manager.ConfigurationManager;
import ua.nure.leonov.summarytask.service.exception.ServiceException;
import ua.nure.leonov.summarytask.service.impl.EditionServiceImpl;
import ua.nure.leonov.summarytask.service.impl.SubServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SortEditionsCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(SortEditionsCommand.class);

    private static final String PARAM_SORT_CRITERION = "crit";

    private static final String PARAM_SORT_ORDER = "order";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        EditionServiceImpl editionService = new EditionServiceImpl();
        SubServiceImpl subService = new SubServiceImpl();
        String crit = req.getParameter(PARAM_SORT_CRITERION);
        boolean order = Boolean.parseBoolean(req.getParameter(PARAM_SORT_ORDER));

        List<Edition> editions;
        List<Subscription> subs;
        HttpSession session = req.getSession();
        long id = (long)session.getAttribute("user_id");
        try {
            LOG.debug("Try to sort all editions with criterion= " + crit + " and order= " + order);
            editions = editionService.sortAllEditions(crit, order);
            subs = subService.findAllSubByUserId((int) id);
            req.setAttribute("crit", crit);
            req.setAttribute("order", order);
            req.setAttribute("editions", editions);
            req.setAttribute("subs", subs);
            return ConfigurationManager.getProperty("path.page.editions");
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
