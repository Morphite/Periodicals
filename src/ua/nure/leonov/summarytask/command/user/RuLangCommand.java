package ua.nure.leonov.summarytask.command.user;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.command.ActionCommand;
import ua.nure.leonov.summarytask.command.exception.CommandException;
import ua.nure.leonov.summarytask.controller.MainController;
import ua.nure.leonov.summarytask.manager.ConfigurationManager;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

public class RuLangCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(RuLangCommand.class);

    private final static String COOKIE_NAME = "locale";

    private final static String COOKIE_VALUE = "ru_RU";

    private final static String LOCALE_VALUE = "ru";

    private final static int COOKIE_AGE_IN_SEC = 86_400;

    private static final String PARAM_HEADER_REFERER = "referer";

    private static final String PARAM_NAME_SERVLET = "period?";


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String page;
        Locale rus = new Locale(LOCALE_VALUE, LOCALE_VALUE.toUpperCase());
        Locale.setDefault(rus);
        MainController.messageManager.changeResource(rus);
        MainController.resourceManager.changeResource(rus);
        HttpSession session = req.getSession(true);
        Cookie cookie = new Cookie(COOKIE_NAME, COOKIE_VALUE);
        cookie.setMaxAge(COOKIE_AGE_IN_SEC);
        resp.addCookie(cookie);
        req.setAttribute("lang", COOKIE_VALUE);
        session.setAttribute("lang", COOKIE_VALUE);
        try {
            LOG.debug("Try to change LOCALE to RU");
            URL url = new URL(req.getHeader(PARAM_HEADER_REFERER));
            req.setAttribute("redirect", PARAM_NAME_SERVLET + url.getQuery());
        } catch (MalformedURLException e) {
            throw new CommandException("URL malformed.");
        }
        if (session.getAttribute("user") == null) {
            page = ConfigurationManager.getProperty("path.page.login");
        }
        else {
            page = ConfigurationManager.getProperty("path.page.main");
        }
        return page;
    }
}
