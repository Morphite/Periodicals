package ua.nure.leonov.summarytask.command.user;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.command.ActionCommand;
import ua.nure.leonov.summarytask.command.exception.CommandException;
import ua.nure.leonov.summarytask.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String page = ConfigurationManager.getProperty("path.page.index");
        HttpSession session = req.getSession(true);
        String lang = null;
        if (session.getAttribute(lang) != null) {
            lang = (String) session.getAttribute(lang);
        }
        LOG.debug("Log out and invalidate session");
        session.invalidate();
        return page;
    }
}
