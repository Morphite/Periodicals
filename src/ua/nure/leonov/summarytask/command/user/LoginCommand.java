package ua.nure.leonov.summarytask.command.user;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.command.ActionCommand;
import ua.nure.leonov.summarytask.command.exception.CommandException;
import ua.nure.leonov.summarytask.controller.MainController;
import ua.nure.leonov.summarytask.entity.User;
import ua.nure.leonov.summarytask.manager.ConfigurationManager;
import ua.nure.leonov.summarytask.service.impl.UserServiceImpl;
import ua.nure.leonov.summarytask.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    private static final String PARAM_LOGIN = "login";

    private static final String PARAM_PASS = "pass";


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String page;
        String login = req.getParameter(PARAM_LOGIN);
        String pass = req.getParameter(PARAM_PASS);
        UserServiceImpl userService = new UserServiceImpl();

        try {
            if (userService.findUserByLogin(login).getBan()) {
                req.setAttribute("errorLoginPage", MainController.messageManager.getProperty("message.banned"));
                page = ConfigurationManager.getProperty("path.page.login");
                LOG.debug("Login failed (user with login= " + login + " is banned");
            } else if (userService.checkLoginAndPass(login, pass)) {
                LOG.debug("Try to log in with login = " + login);
                User user = userService.findUserByLogin(login);
                HttpSession session = req.getSession(true);
                if (session.getAttribute("user") == null) {
                    session.setAttribute("user", user);
                }
                if (session.getAttribute("admin") == null) {
                    session.setAttribute("admin", user.getAdmin());
                }
                if (session.getAttribute("user_id") == null) {
                    session.setAttribute("user_id", user.getId());
                }
                page = ConfigurationManager.getProperty("path.page.main");
                LOG.debug("Login success");
            } else {
                req.setAttribute("errorLoginPage", MainController.messageManager.getProperty("message.loginerror"));
                page = ConfigurationManager.getProperty("path.page.login");
                LOG.debug("Login failed");
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
