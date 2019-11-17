package ua.nure.leonov.summarytask.command;

import ua.nure.leonov.summarytask.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface that introduce template for commands
 */
public interface ActionCommand {


    /**
     * @param req the ServletRequest
     * @param resp the ServletResponse
     * @return name of page from config.properties
     * @throws CommandException the CommandException
     */
    String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException;
}
