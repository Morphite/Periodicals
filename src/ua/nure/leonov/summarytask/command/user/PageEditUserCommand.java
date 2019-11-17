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

public class PageEditUserCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(PageEditUserCommand.class);

    private static final String PARAM_USER_ID = "id";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        long id = Long.parseLong(req.getParameter(PARAM_USER_ID));
        UserServiceImpl userService = new UserServiceImpl();
        User user;
        try {
            LOG.info("Go to admin edit user page with id " + id);
            user = userService.findUserById(id);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        if (id == user.getId()) {
            req.setAttribute("userForEdit", user);
            return ConfigurationManager.getProperty("path.page.admin.edit.user");
        } else {
            return ConfigurationManager.getProperty("path.page.admin.panel");
        }
    }
}
