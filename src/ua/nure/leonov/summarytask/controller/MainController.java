package ua.nure.leonov.summarytask.controller;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.command.ActionCommand;
import ua.nure.leonov.summarytask.command.ActionFactory;
import ua.nure.leonov.summarytask.command.exception.CommandException;
import ua.nure.leonov.summarytask.manager.ConfigurationManager;
import ua.nure.leonov.summarytask.manager.MessageManager;
import ua.nure.leonov.summarytask.manager.ResourceManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@WebServlet("/period")
public class MainController extends HttpServlet {

    /**
     * The constant logger.
     */
    private final static Logger LOG = Logger.getLogger(MainController.class);

    /**
     * The resource manager.
     */
    public static ResourceManager resourceManager = ResourceManager.INSTANCE;

    /**
     * The message manager.
     */
    public static MessageManager messageManager = MessageManager.INSTANCE;

    /**
     * The constant RU Locale
     */
    private final static String LOCALE_RU = "ru_RU";

    /**
     * The constant RU Locale value
     */
    private final static String LOCALE_RU_VALUE = "ru";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Method process requests from another methods by its commands.
     * Get Locale cookies and define users locale.
     * Execute commands and then get the target page.
     * @param req the ServletRequest
     * @param resp the ServletResponse
     * @throws IOException the IOException
     * @throws ServletException the ServletException
     */
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if ("locale".equals(cookie.getValue())) {
                req.setAttribute("lang", cookie.getValue());
                if (LOCALE_RU.equals(cookie.getValue())) {
                    MainController.messageManager.changeResource(new Locale(LOCALE_RU_VALUE, LOCALE_RU_VALUE.toUpperCase()));
                    MainController.resourceManager.changeResource(new Locale(LOCALE_RU_VALUE, LOCALE_RU_VALUE.toUpperCase()));
                } else {
                    MainController.messageManager.changeResource(Locale.US);
                    MainController.resourceManager.changeResource(Locale.US);
                }
            }
        }

        String page = "";
        ActionFactory actionFactory = new ActionFactory();
        ActionCommand command = actionFactory.defineCommand(req, resp);
        try {
            page = command.execute(req, resp);
        } catch (CommandException e) {
            LOG.error(e);
        }
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(req, resp);
        } else {
            page = ConfigurationManager.getProperty("path.page.main");
            req.getSession().setAttribute("emptyPage",
                    messageManager.getProperty("message.emptypage"));
            resp.sendRedirect(req.getContextPath() + page);
        }
    }
}
