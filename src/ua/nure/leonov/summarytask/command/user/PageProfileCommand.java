package ua.nure.leonov.summarytask.command.user;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.command.ActionCommand;
import ua.nure.leonov.summarytask.command.exception.CommandException;
import ua.nure.leonov.summarytask.entity.User;
import ua.nure.leonov.summarytask.manager.ConfigurationManager;
import ua.nure.leonov.summarytask.service.exception.ServiceException;
import ua.nure.leonov.summarytask.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PageProfileCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(PageProfileCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        HttpSession session = req.getSession();
        long id = ((long) session.getAttribute("user_id"));

        UserServiceImpl userService = new UserServiceImpl();
        try {
            LOG.info("User enter a profile page with id=" + id);
            User user = userService.findUserById(id);
            session.setAttribute("user", user);
            return ConfigurationManager.getProperty("path.page.profile");
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
