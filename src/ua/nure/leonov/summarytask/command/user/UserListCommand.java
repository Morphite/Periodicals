package ua.nure.leonov.summarytask.command.user;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.command.ActionCommand;
import ua.nure.leonov.summarytask.command.exception.CommandException;
import ua.nure.leonov.summarytask.entity.User;
import ua.nure.leonov.summarytask.manager.ConfigurationManager;
import ua.nure.leonov.summarytask.service.impl.UserServiceImpl;
import ua.nure.leonov.summarytask.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserListCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(UserListCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        UserServiceImpl userService = new UserServiceImpl();
        List<User> users;
        try {
            LOG.debug("Try to get all users from DB");
            users = userService.findAllUsers();
            req.setAttribute("users", users);
            return ConfigurationManager.getProperty("path.page.admin.list.user");
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
