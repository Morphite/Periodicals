package ua.nure.leonov.summarytask.command.subscription;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.command.ActionCommand;
import ua.nure.leonov.summarytask.command.exception.CommandException;
import ua.nure.leonov.summarytask.controller.MainController;
import ua.nure.leonov.summarytask.service.exception.ServiceException;
import ua.nure.leonov.summarytask.service.impl.SubServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UnsubscribeCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(UnsubscribeCommand.class);

    private static final String PARAM_SUB_ID = "sub_id";

    private static final String ATTR_USER_ID = "user_id";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        int subId = Integer.parseInt(req.getParameter(PARAM_SUB_ID));
        long checkUID;
        SubServiceImpl subService = new SubServiceImpl();
        try {
            checkUID = subService.findSubById((long) subId).getUser().getId();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        HttpSession session = req.getSession();
        if ((long) session.getAttribute(ATTR_USER_ID) == checkUID) {
            try {
                if (subService.unsubscribe(subId)) {
                    return "/period?command=edition_list";
                } else {
                    req.setAttribute("errorSub", MainController.messageManager.getProperty("message.unsuberror"));
                    LOG.debug("Unsubscribe failed");
                    return "/period?command=edition_list";
                }
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        } else {
            req.setAttribute("errorSub", MainController.messageManager.getProperty("message.unsuberror"));
            LOG.debug("Unsubscribe failed");
            return "/period?command=edition_list";
        }
    }
}
