package ua.nure.leonov.summarytask.command.subscription;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.command.ActionCommand;
import ua.nure.leonov.summarytask.command.exception.CommandException;
import ua.nure.leonov.summarytask.dao.exception.DAOException;
import ua.nure.leonov.summarytask.dao.impl.SubDAOImpl;
import ua.nure.leonov.summarytask.entity.Subscription;
import ua.nure.leonov.summarytask.entity.User;
import ua.nure.leonov.summarytask.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SubsByUserCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(SubsByUserCommand.class);

    private static final String PARAM_USER = "user";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        HttpSession session = req.getSession();
        long id = ((User) session.getAttribute(PARAM_USER)).getId();

        try {
            LOG.info("User with id=" + id + " view all their subscriptions");
            List<Subscription> subscriptionList = SubDAOImpl.getInstance().findAllSubsByUserWithEditionTitle((int) id);
            req.setAttribute("subList", subscriptionList);
            return ConfigurationManager.getProperty("path.page.sub");
        } catch (DAOException e) {
            throw new CommandException(e);
        }
    }
}
