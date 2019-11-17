package ua.nure.leonov.summarytask.command.user;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.command.ActionCommand;
import ua.nure.leonov.summarytask.command.exception.CommandException;
import ua.nure.leonov.summarytask.command.subscription.DeleteSubCommand;
import ua.nure.leonov.summarytask.controller.MainController;
import ua.nure.leonov.summarytask.entity.User;
import ua.nure.leonov.summarytask.manager.ConfigurationManager;
import ua.nure.leonov.summarytask.service.impl.UserServiceImpl;
import ua.nure.leonov.summarytask.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DeleteUserCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(DeleteUserCommand.class);

    private static final String PARAM_USER_ID = "id";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        Long id = Long.parseLong(req.getParameter(PARAM_USER_ID));

        UserServiceImpl userService = new UserServiceImpl();
        try {
            LOG.info("Try to delete user with id=" + id);
            if (userService.deleteUser(id)) {
                List<User> users = userService.findAllUsers();
                req.setAttribute("users", users);
                return ConfigurationManager.getProperty("path.page.admin.list.user");
            } else {
                req.setAttribute("errorUserEdit",
                        MainController.messageManager.getProperty("message.deleteusererror"));
                return ConfigurationManager.getProperty("path.page.admin.list.user");
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
