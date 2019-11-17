package ua.nure.leonov.summarytask.command;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.command.exception.CommandException;
import ua.nure.leonov.summarytask.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The Class that represents ForwardCommand
 */
public class ForwardCommand implements ActionCommand {

    /** Constant Logger */
    private static final Logger LOG = Logger.getLogger(ForwardCommand.class);

    /** Constant param page */
    private static final String PARAM_PAGE_NAME = "page";

    /**
     * @param req  the ServletRequest
     * @param resp the ServletResponse
     * @return forwarded page name
     * @throws CommandException throw Command Exception
     */
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String page;
        String requestPage = req.getParameter(PARAM_PAGE_NAME);
        switch (requestPage) {
            case "login":
                page = ConfigurationManager.getProperty("path.page.login");
                break;
            case "register":
                page = ConfigurationManager.getProperty("path.page.register");
                break;
            case "main":
                page = ConfigurationManager.getProperty("path.page.main");
                break;
            case "admin_panel":
                page = ConfigurationManager.getProperty("path.page.admin.panel");
                break;
            case "change_password":
                page = ConfigurationManager.getProperty("path.page.change.password");
                break;
            case "create_user":
                page = ConfigurationManager.getProperty("path.page.admin.create.user");
                break;
            case "profile":
                page = ConfigurationManager.getProperty("path.page.profile");
                break;
            case "create_edition":
                page = ConfigurationManager.getProperty("path.page.admin.create.edit");
                break;
            case "create_theme":
                page = ConfigurationManager.getProperty("path.page.admin.create.theme");
                break;
            default:
                page = ConfigurationManager.getProperty("path.page.login");
                break;
        }
        LOG.info("Forward to ==> " + page);
        return page;
    }
}
