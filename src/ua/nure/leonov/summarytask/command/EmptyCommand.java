package ua.nure.leonov.summarytask.command;

import ua.nure.leonov.summarytask.command.exception.CommandException;
import ua.nure.leonov.summarytask.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * The Class that represents empty command
 */
public class EmptyCommand implements ActionCommand {

    /**
     * @param req  the ServletRequest
     * @param resp the ServletResponse
     * @return login page
     * @throws CommandException the CommandException
     */
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        return ConfigurationManager.getProperty("path.page.login");
    }
}
