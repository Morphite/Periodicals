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
import java.util.List;

public class EditUserProfileCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(EditUserCommand.class);

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
        HttpSession session = req.getSession();
        User user = ((User) session.getAttribute("user"));
        Long id = user.getId();
        String login = req.getParameter(PARAM_USER_LOGIN);
        String pass = user.getPass();
        String email = req.getParameter(PARAM_USER_EMAIL);
        String name = req.getParameter(PARAM_USER_NAME);
        String surname = req.getParameter(PARAM_USER_SURNAME);
        boolean admin = user.getAdmin();
        boolean ban = user.getBan();
        double balance = user.getBalance();

        user.setLogin(login);
        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);
        session.setAttribute("user", user);

        UserServiceImpl userService = new UserServiceImpl();
        try {
            if (userService.checkUserEdit(id, login, pass, email, name, surname, admin, ban, balance)) {
                List<User> users = userService.findAllUsers();
                req.setAttribute("users", users);
                return "/period?command=page_profile";
            } else {
                req.setAttribute("errorUserEdit",
                        MainController.messageManager.getProperty("message.editusererror"));
                return ConfigurationManager.getProperty("path.page.profile");
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
