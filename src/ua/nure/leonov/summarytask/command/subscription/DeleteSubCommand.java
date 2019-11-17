package ua.nure.leonov.summarytask.command.subscription;

import com.mysql.cj.log.Log;
import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.command.ActionCommand;
import ua.nure.leonov.summarytask.command.exception.CommandException;
import ua.nure.leonov.summarytask.controller.MainController;
import ua.nure.leonov.summarytask.entity.Subscription;
import ua.nure.leonov.summarytask.manager.ConfigurationManager;
import ua.nure.leonov.summarytask.service.exception.ServiceException;
import ua.nure.leonov.summarytask.service.impl.SubServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DeleteSubCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(DeleteSubCommand.class);

    private static final String PARAM_SUB_ID = "id";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        int id = Integer.parseInt(req.getParameter(PARAM_SUB_ID));

        SubServiceImpl subService = new SubServiceImpl();

        try {
            LOG.info("Try to delete sub with id=" + id);
            if (subService.unsubscribe(id)) {
                List<Subscription> subs = subService.findAllSubs();
                req.setAttribute("subs", subs);
                return ConfigurationManager.getProperty("path.page.admin.list.subs");
            } else {
                req.setAttribute("errorUserEdit",
                        MainController.messageManager.getProperty("message.deletesuberror"));
                return ConfigurationManager.getProperty("path.page.admin.list.subs");
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
