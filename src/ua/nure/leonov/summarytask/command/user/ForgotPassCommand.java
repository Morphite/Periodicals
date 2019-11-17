package ua.nure.leonov.summarytask.command.user;

import org.apache.log4j.Logger;
import ua.nure.leonov.summarytask.command.ActionCommand;
import ua.nure.leonov.summarytask.command.edition.CreateEditionCommand;
import ua.nure.leonov.summarytask.command.exception.CommandException;
import ua.nure.leonov.summarytask.controller.MainController;
import ua.nure.leonov.summarytask.entity.User;
import ua.nure.leonov.summarytask.mailer.Mailer;
import ua.nure.leonov.summarytask.mailer.MailerException;
import ua.nure.leonov.summarytask.manager.ConfigurationManager;
import ua.nure.leonov.summarytask.service.exception.ServiceException;
import ua.nure.leonov.summarytask.service.impl.UserServiceImpl;
import util.PasswordGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForgotPassCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(ForgotPassCommand.class);

    private static final String PARAM_LOGIN = "login";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String login = req.getParameter(PARAM_LOGIN);

        LOG.info("User with login " + login + " execute forgot pass command");
        PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
                .useDigits(true)
                .useLower(true)
                .useUpper(true)
                .build();

        User user;
        String newPass = passwordGenerator.generate(10);
        String oldPass;
        String email;
        long uid;
        UserServiceImpl userService = new UserServiceImpl();
        Mailer mailer = new Mailer();

        try {
            user = userService.findUserByLogin(login);
            oldPass = user.getPass();
            uid = user.getId();
            email = user.getEmail();
            if (login.equals(user.getLogin())) {
                if (userService.checkPassword(oldPass, newPass, uid) && mailer.sendMail(newPass, email)) {
                    req.setAttribute("emailSendMessage", MainController.messageManager.getProperty("message.forgotpass"));
                    LOG.debug("Pass recovery success");
                    return ConfigurationManager.getProperty("path.page.login");
                } else {
                    req.setAttribute("errorLoginPage", MainController.messageManager.getProperty("message.errorforgotpass"));
                    LOG.debug("Pass recovery failed");
                    return ConfigurationManager.getProperty("path.page.login");
                }
            } else {
                req.setAttribute("errorLoginPage", MainController.messageManager.getProperty("message.errorforgotpass"));
                LOG.debug("Pass recovery failed");
                return ConfigurationManager.getProperty("path.page.login");
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        } catch (MailerException e) {
            throw new CommandException("Error during sending email with new pass" + e, e);
        }
    }
}
