package ua.nure.leonov.summarytask.command;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.command.client.CommandEnum;
import ua.nure.leonov.summarytask.controller.MainController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The Class that define commands
 */
public class ActionFactory {

    /** Constant Logger */
    private static final Logger LOG = Logger.getLogger(ActionFactory.class);

    /** Constant command name */
    private static final String COMMAND_NAME = "command";

    /**
     * @param req the ServletRequest
     * @param resp the ServletResponse
     * @return ActionCommand that was defined
     */
    public ActionCommand defineCommand(HttpServletRequest req, HttpServletResponse resp) {
        ActionCommand current = new EmptyCommand();
        String action = req.getParameter(COMMAND_NAME);
        LOG.debug("Action ==> " + action);
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException ex) {
            req.setAttribute("wrongAction", action + MainController.messageManager.getProperty("message.wrongaction"));
        }
        return current;
    }
}
