package ua.nure.leonov.summarytask.command.edition;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.command.ActionCommand;
import ua.nure.leonov.summarytask.command.exception.CommandException;
import ua.nure.leonov.summarytask.controller.MainController;
import ua.nure.leonov.summarytask.entity.Edition;
import ua.nure.leonov.summarytask.manager.ConfigurationManager;
import ua.nure.leonov.summarytask.service.exception.ServiceException;
import ua.nure.leonov.summarytask.service.impl.EditionServiceImpl;
import ua.nure.leonov.summarytask.service.impl.SubServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ReadEditionCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(ReadEditionCommand.class);

    private static final String PARAM_EDITION_ID = "id";

    private static final String PARAM_USER_ID = "user_id";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        HttpSession session = req.getSession();
        int id = Integer.parseInt(req.getParameter(PARAM_EDITION_ID));
        long userId = (long) session.getAttribute(PARAM_USER_ID);

        EditionServiceImpl editionService = new EditionServiceImpl();
        SubServiceImpl subService = new SubServiceImpl();

        try {
            if (subService.checkReadAvailability((int) userId, id)) {
                LOG.info("User read a edition with id=" + id);
                Edition edition = editionService.findEditionById(id);
                req.setAttribute("edition", edition);
                return ConfigurationManager.getProperty("path.page.edition_page");
            } else {
                req.setAttribute("errorSub", MainController.messageManager.getProperty("message.readerror"));
                LOG.debug("Read failed");
                return "/period?command=edition_list";
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
