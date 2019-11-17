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

public class SearchEditionsCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(SearchEditionsCommand.class);

    private static final String PARAM_SEARCH_STR = "searchStr";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String searchStr = req.getParameter(PARAM_SEARCH_STR);
        EditionServiceImpl editionService = new EditionServiceImpl();

        try {
            LOG.info("Search in edition with string=" + searchStr);
            List<Edition> result = editionService.searchInEditions(searchStr);
            req.setAttribute("editions", result);
            return ConfigurationManager.getProperty("path.page.editions");
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
