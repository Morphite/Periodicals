package ua.nure.leonov.summarytask.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(filterName = "EncodingFilter",
        urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "encoding", value = "utf-8")})
public class EncodingFilter implements Filter {

    private String code;

    public void init(FilterConfig config) throws ServletException {
        code = config.getInitParameter("encoding");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String codeReq = req.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeReq)) {
            req.setCharacterEncoding(code);
            resp.setCharacterEncoding(code);
        }
        chain.doFilter(req, resp);
    }

    public void destroy() {
    }
}
