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

public class ChangePasswordCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(ChangePasswordCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        HttpSession session = req.getSession();
        User user = ((User) session.getAttribute("user"));

        long id = user.getId();
        String oldPass = req.getParameter("oldPass");
        String newPass = req.getParameter("newPass");

        UserServiceImpl userService = new UserServiceImpl();
        try {
            if (userService.checkPassword(oldPass, newPass, id)) {
                req.setAttribute("passChange", MainController.messageManager.getProperty("message.successpasschange"));
                return ConfigurationManager.getProperty("path.page.profile");
            } else {
                req.setAttribute("errorLoginPage", MainController.messageManager.getProperty("message.incorrectpass"));
                LOG.debug("Change pass failed");
                session.invalidate();
                return ConfigurationManager.getProperty("path.page.login");
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
