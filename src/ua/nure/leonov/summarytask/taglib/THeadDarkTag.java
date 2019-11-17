package ua.nure.leonov.summarytask.taglib;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class THeadDarkTag extends TagSupport {

    /**
     * Do start tag.
     *
     * @return the int
     * @throws JspTagException the jsp tag exception
     */
    @Override
    public int doStartTag() throws JspTagException {
        try {
            JspWriter out = pageContext.getOut();
            out.write("<thead class=\"thead-dark\">");
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return EVAL_BODY_INCLUDE;
    }

    /**
     * Do end tag.
     *
     * @return the int
     * @throws JspTagException the jsp tag exception
     */
    @Override
    public int doEndTag() throws JspTagException {
        try {
            pageContext.getOut().write("</thead>");
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return EVAL_PAGE;
    }

}
