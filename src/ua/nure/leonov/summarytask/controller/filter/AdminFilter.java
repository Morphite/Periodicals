package ua.nure.leonov.summarytask.controller.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebFilter("/*")
public class AdminFilter implements Filter {

    private final static Logger LOG = Logger.getLogger(AdminFilter.class);

    /**
     * The Constant PARAM_COMMAND
     */
    private static final String PARAM_COMMAND = "command";

    /**
     * The Constant PARAM_PAGE.
     */
    private static final String PARAM_PAGE = "page";

    /**
     * The Constant PARAM_ADMIN.
     */
    private static final String PARAM_ADMIN = "admin";

    /**
     * The Constant COMMAND_FORWARD.
     */
    private static final String COMMAND_FORWARD = "forward";

    /**
     * The Constant REDIRECT_ADDRESS.
     */
    private static final String REDIRECT_ADDRESS = "period?command=forward&page=login";

    private static final String REDIRECT_ADDRESS_EDIT = "period?command=forward&page=main";

    /**
     * The restricted command list.
     */
    private ArrayList<String> commands;

    /**
     * The restricted page list.
     */
    private ArrayList<String> pages;


    /**
     * @param config Filter
     */
    public void init(FilterConfig config) {
        commands = new ArrayList<>();
        pages = new ArrayList<>();
        commands.add("user_list");
        commands.add("page_edit_user");
        commands.add("create_user");
        commands.add("edit_user");
        commands.add("delete_user");
        commands.add("delete_sub");
        commands.add("sub_list");
        commands.add("edition_admin_list");
        commands.add("page_create_edition");
        commands.add("create_edition");
        commands.add("edit_edition");
        commands.add("delete_edition");
        commands.add("theme_admin_list");
        commands.add("create_theme");
        commands.add("page_edit_theme");
        commands.add("edit_theme");
        commands.add("delete_theme");
        commands.add("forward");

        pages.add("admin_panel");
        pages.add("create_user");
        pages.add("create_edition");
        pages.add("create_theme");
        pages.add("create_user");

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) resp;
        HttpSession session = httpRequest.getSession();
        boolean permission = true;
        boolean isAdmin = false;
//        if (session.getAttribute("user") != null || "login".equals(req.getParameter("command"))) {
//
//        } else {
//            req.getRequestDispatcher(REDIRECT_ADDRESS).forward(req, resp);
//        }

        if (session.getAttribute(PARAM_ADMIN) != null) {
            isAdmin = (boolean) session.getAttribute(PARAM_ADMIN);
        }
        LOG.debug("User isAdmin = " + (isAdmin));
        if (!isAdmin) {
            permission = checkRestrictedCommands(httpRequest);
        }

        if (permission) {
            chain.doFilter(req, resp);
        } else {
            httpResponse.sendRedirect(REDIRECT_ADDRESS_EDIT);
        }
    }

    public void destroy() {}

    /**
     * @param httpRequest the ServletRequest
     * @return permission to go forward
     */
    private boolean checkRestrictedCommands(HttpServletRequest httpRequest) {
        boolean toReturn = true;
        String command = httpRequest.getParameter(PARAM_COMMAND);
        if (command != null && commands.contains(command)) {
            if (command.equals(COMMAND_FORWARD)) {
                String page = httpRequest.getParameter(PARAM_PAGE);
                if (page != null && pages.contains(page)) {
                    toReturn = false;
                }
            } else {
                toReturn = false;
            }
        }
        return toReturn;
    }

}
