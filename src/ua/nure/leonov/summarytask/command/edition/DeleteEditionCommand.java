package ua.nure.leonov.summarytask.command.edition;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.command.ActionCommand;
import ua.nure.leonov.summarytask.command.exception.CommandException;
import ua.nure.leonov.summarytask.controller.MainController;
import ua.nure.leonov.summarytask.entity.Edition;
import ua.nure.leonov.summarytask.manager.ConfigurationManager;
import ua.nure.leonov.summarytask.service.exception.ServiceException;
import ua.nure.leonov.summarytask.service.impl.EditionServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DeleteEditionCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(DeleteEditionCommand.class);

    private static final String PARAM_EDIT_ID = "id";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        int id = Integer.parseInt(req.getParameter(PARAM_EDIT_ID));

        EditionServiceImpl editionService = new EditionServiceImpl();
        try {
            LOG.info("Try to get edition with id=" + id);
            if (editionService.delete(id)) {
                List<Edition> editions = editionService.findAllEditions();
                req.setAttribute("editions", editions);
                return ConfigurationManager.getProperty("path.page.admin.list.edit");
            } else {
                req.setAttribute("errorEditEdit",
                        MainController.messageManager.getProperty("message.deleteediterror"));
                return ConfigurationManager.getProperty("path.page.admin.list.edit");
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
