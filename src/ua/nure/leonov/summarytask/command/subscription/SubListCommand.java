package ua.nure.leonov.summarytask.command.subscription;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.command.ActionCommand;
import ua.nure.leonov.summarytask.command.exception.CommandException;
import ua.nure.leonov.summarytask.entity.Subscription;
import ua.nure.leonov.summarytask.manager.ConfigurationManager;
import ua.nure.leonov.summarytask.service.exception.ServiceException;
import ua.nure.leonov.summarytask.service.impl.SubServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SubListCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(SubListCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        SubServiceImpl subService = new SubServiceImpl();
        List<Subscription> subs;
        try {
            LOG.debug("Try to get all subs from DB");
            subs = subService.findAllSubs();
            req.setAttribute("subs", subs);
            return ConfigurationManager.getProperty("path.page.admin.list.subs");
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
