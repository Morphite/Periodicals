package ua.nure.leonov.summarytask.command.edition;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.command.ActionCommand;
import ua.nure.leonov.summarytask.command.exception.CommandException;
import ua.nure.leonov.summarytask.controller.MainController;
import ua.nure.leonov.summarytask.entity.Edition;
import ua.nure.leonov.summarytask.entity.Theme;
import ua.nure.leonov.summarytask.manager.ConfigurationManager;
import ua.nure.leonov.summarytask.service.exception.ServiceException;
import ua.nure.leonov.summarytask.service.impl.EditionServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class EditEditionCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(EditEditionCommand.class);

    private static final String PARAM_EDITION_ID = "id";

    private static final String PARAM_THEME_ID = "theme_id";

    private static final String PARAM_TITLE = "title";

    private static final String PARAM_PUBLISHER = "publisher";

    private static final String PARAM_TEXT = "text";

    private static final String PARAM_PRICE = "price";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        int edition_id = Integer.parseInt(req.getParameter(PARAM_EDITION_ID));
        int theme_id = Integer.parseInt(req.getParameter(PARAM_THEME_ID));
        String title = req.getParameter(PARAM_TITLE);
        String publisher = req.getParameter(PARAM_PUBLISHER);
        String text = req.getParameter(PARAM_TEXT);
        double price = Double.parseDouble(req.getParameter(PARAM_PRICE));

        Edition edition = new Edition();
        Theme theme = new Theme();
        theme.setId(theme_id);
        edition.setId(edition_id);
        edition.setTheme(theme);
        edition.setTitle(title);
        edition.setPublisher(publisher);
        edition.setText(text);
        edition.setPrice(price);

        EditionServiceImpl editionService = new EditionServiceImpl();
        try {
            LOG.info("Try to update edition with id=" + edition_id);
            if (editionService.update(edition)) {
                List<Edition> editions = editionService.findAllEditions();
                req.setAttribute("editions", editions);
                return ConfigurationManager.getProperty("path.page.admin.list.edit");
            } else {
                req.setAttribute("errorEditCreate",
                        MainController.messageManager.getProperty("message.editediterror"));
                return ConfigurationManager.getProperty("path.page.admin.edit.edit");
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
