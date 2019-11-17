package ua.nure.leonov.summarytask.command.subscription;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.command.ActionCommand;
import ua.nure.leonov.summarytask.command.exception.CommandException;
import ua.nure.leonov.summarytask.controller.MainController;
import ua.nure.leonov.summarytask.service.exception.ServiceException;
import ua.nure.leonov.summarytask.service.impl.SubServiceImpl;
import ua.nure.leonov.summarytask.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SubscribeCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(SubscribeCommand.class);

    private static final String PARAM_USER_ID = "user_id";

    private static final String PARAM_EDITION_ID = "edition_id";

    private static final String PARAM_EDITION_PRICE = "price";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        SubServiceImpl subService = new SubServiceImpl();
        UserServiceImpl userService = new UserServiceImpl();

        long uid = Long.parseLong(req.getParameter(PARAM_USER_ID));
        int eid = Integer.parseInt(req.getParameter(PARAM_EDITION_ID));
        double price = Double.parseDouble(req.getParameter(PARAM_EDITION_PRICE));

        HttpSession session = req.getSession();
        if ((long) session.getAttribute("user_id") == uid) {
            try {
                LOG.info("User with id=" + uid + "subscribed to edition with id=" + eid);
                double currentBalance = userService.findBalanceById(uid);
                double newBalance = currentBalance - price;
                if (subService.makeSubscribe(uid, eid, price, newBalance)) {
                    return "/period?command=edition_list";
                } else {
                    req.setAttribute("errorSub", MainController.messageManager.getProperty("message.suberror"));
                    LOG.debug("Subscribe failed");
                    return "/period?command=edition_list";
                }
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        } else {
            req.setAttribute("errorSub", MainController.messageManager.getProperty("message.suberror"));
            LOG.debug("Subscribe failed");
            return "/period?command=edition_list";
        }
    }
}
