package ua.nure.leonov.summarytask.command.user;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.command.ActionCommand;
import ua.nure.leonov.summarytask.command.exception.CommandException;
import ua.nure.leonov.summarytask.controller.MainController;
import ua.nure.leonov.summarytask.entity.User;
import ua.nure.leonov.summarytask.manager.ConfigurationManager;
import ua.nure.leonov.summarytask.service.exception.ServiceException;
import ua.nure.leonov.summarytask.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddMoneyCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(AddMoneyCommand.class);

    private static final String PARAM_ID_USER = "id";

    private static final String PARAM_MONEY = "money";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        HttpSession session = req.getSession();
        int id = (int) ((User) session.getAttribute("user")).getId();
        double money = Double.parseDouble(req.getParameter(PARAM_MONEY));

        UserServiceImpl service = new UserServiceImpl();
        try {
            if (service.checkBalanceToAdd(id, money)) {
                return "/period?command=page_profile";
            } else {
                req.setAttribute("errorBalanceAddMessage",
                        MainController.messageManager.getProperty("message.balanceadderror"));
                return ConfigurationManager.getProperty("path.page.profile");
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
