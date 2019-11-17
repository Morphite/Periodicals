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
import java.util.List;

public class CreateUserCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(CreateUserCommand.class);

    private static final String PARAM_USER_LOGIN = "login";

    private static final String PARAM_USER_PASS = "pass";

    private static final String PARAM_USER_EMAIL = "email";

    private static final String PARAM_USER_NAME = "name";

    private static final String PARAM_USER_SURNAME = "surname";

    private static final String PARAM_USER_ADMIN = "admin";

    private static final String PARAM_USER_BAN = "ban";

    private static final String PARAM_USER_BALANCE = "balance";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String login = req.getParameter(PARAM_USER_LOGIN);
        String pass = req.getParameter(PARAM_USER_PASS);
        String email = req.getParameter(PARAM_USER_EMAIL);
        String name = req.getParameter(PARAM_USER_NAME);
        String surname = req.getParameter(PARAM_USER_SURNAME);
        boolean admin = Boolean.parseBoolean(req.getParameter(PARAM_USER_ADMIN));
        boolean ban = Boolean.parseBoolean(req.getParameter(PARAM_USER_BAN));
        double balance = Double.parseDouble(req.getParameter(PARAM_USER_BALANCE));

        UserServiceImpl userService = new UserServiceImpl();

        try {
            LOG.info("Try to create new user by admin with login " + login);
            if (userService.checkUserCreateByAdmin(login, pass, email, name, surname, admin, ban, balance)) {
                List<User> users = userService.findAllUsers();
                req.setAttribute("users", users);
                return ConfigurationManager.getProperty("path.page.admin.list.user");
            } else {
                req.setAttribute("errorUserCreate",
                        MainController.messageManager.getProperty("message.createusererror"));
                return ConfigurationManager.getProperty("path.page.admin.create.user");
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
