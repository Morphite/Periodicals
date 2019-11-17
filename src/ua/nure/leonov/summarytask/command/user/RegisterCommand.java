package ua.nure.leonov.summarytask.command.user;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.command.ActionCommand;
import ua.nure.leonov.summarytask.command.exception.CommandException;
import ua.nure.leonov.summarytask.controller.MainController;
import ua.nure.leonov.summarytask.manager.ConfigurationManager;
import ua.nure.leonov.summarytask.service.impl.UserServiceImpl;
import ua.nure.leonov.summarytask.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(RegisterCommand.class);

    private static final String PARAM_LOGIN = "login";

    private static final String PARAM_PASS = "pass";

    private static final String PARAM_EMAIL = "email";

    private static final String PARAM_NAME = "name";

    private static final String PARAM_SURNAME = "surname";


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String page;
        String login = req.getParameter(PARAM_LOGIN);
        String pass = req.getParameter(PARAM_PASS);
        String email = req.getParameter(PARAM_EMAIL);
        String name = req.getParameter(PARAM_NAME);
        String surname = req.getParameter(PARAM_SURNAME);

        UserServiceImpl userService = new UserServiceImpl();

        try {
            LOG.debug("Try to register user with login " + login);

            if (!userService.checkLogin(login)) {
                LOG.debug("Try to register user with login " + login + " failed (login already exist)");
                req.setAttribute("errorRegisterMessage", MainController.messageManager.getProperty("message.loginexist"));
                page = ConfigurationManager.getProperty("path.page.register");
                return page;
            } else if (userService.checkUserCreate(login, pass, email, name, surname)) {
                page = ConfigurationManager.getProperty("path.page.login");
                return page;
            } else {
                LOG.debug("Try to register user with login " + login + " failed (error)");
                req.setAttribute("errorRegisterMessage", MainController.messageManager.getProperty("message.registererror"));
                page = ConfigurationManager.getProperty("path.page.register");
                return page;
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
