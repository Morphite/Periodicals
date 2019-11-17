package ua.nure.leonov.summarytask.command.edition;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.command.ActionCommand;
import ua.nure.leonov.summarytask.command.exception.CommandException;
import ua.nure.leonov.summarytask.entity.Edition;
import ua.nure.leonov.summarytask.manager.ConfigurationManager;
import ua.nure.leonov.summarytask.service.exception.ServiceException;
import ua.nure.leonov.summarytask.service.impl.EditionServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class EditionAdminListCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(EditionAdminListCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        EditionServiceImpl editionService = new EditionServiceImpl();

        try {
            LOG.debug("Try to get all editions from DB for admin");
            List<Edition> editions = editionService.findAllEditions();
            req.setAttribute("editions", editions);
            return ConfigurationManager.getProperty("path.page.admin.list.edit");
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
