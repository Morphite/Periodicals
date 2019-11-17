package ua.nure.leonov.summarytask.command.client;

import ua.nure.leonov.summarytask.command.ActionCommand;
import ua.nure.leonov.summarytask.command.ForwardCommand;
import ua.nure.leonov.summarytask.command.edition.*;
import ua.nure.leonov.summarytask.command.subscription.*;
import ua.nure.leonov.summarytask.command.theme.*;
import ua.nure.leonov.summarytask.command.user.*;

/**
 * The class with web-app commands
 */
public enum CommandEnum {

    /** Login command */
    LOGIN(new LoginCommand()),

    /** Register command */
    REGISTER(new RegisterCommand()),

    /** Logout command */
    LOGOUT(new LogoutCommand()),

    /** Forgot password command */
    FORGOT_PASS(new ForgotPassCommand()),

    /** Switch lang to EN command  */
    ENGLISH_LANGUAGE(new EnLangCommand()),

    /** Switch lang to RU command  */
    RUSSIAN_LANGUAGE(new RuLangCommand()),

    /** Forward command */
    FORWARD(new ForwardCommand()),

    /** User list for admin command */
    USER_LIST(new UserListCommand()),

    /** Load user data to edit page command */
    PAGE_EDIT_USER(new PageEditUserCommand()),

    /** Page profile command */
    PAGE_PROFILE(new PageProfileCommand()),

    /** Create user by admin command */
    CREATE_USER(new CreateUserCommand()),

    /** Edit user by admin command */
    EDIT_USER(new EditUserCommand()),

    /** Edit user profile by user command */
    EDIT_USER_PROFILE(new EditUserProfileCommand()),

    /** Delete user by admin command */
    DELETE_USER(new DeleteUserCommand()),

    /** Change password command */
    CHANGE_PASS(new ChangePasswordCommand()),

    /** Add money command */
    ADD_MONEY(new AddMoneyCommand()),

    /** Delete subscription by admin command */
    DELETE_SUB(new DeleteSubCommand()),

    /** Get all subscriptions by admin command */
    SUB_LIST(new SubListCommand()),

    /** Create subscription by user command */
    SUBSCRIBE(new SubscribeCommand()),

    /** Delete subscription by user command */
    UNSUBSCRIBE(new UnsubscribeCommand()),

    /** Subscription by user command */
    SUBS_BY_USER(new SubsByUserCommand()),

    /** Get all editions command */
    EDITION_LIST(new EditionListCommand()),

    /** Get all editions by admin command */
    EDITION_ADMIN_LIST(new EditionAdminListCommand()),

    /** Load theme data to edition create page command */
    PAGE_CREATE_EDITION(new PageCreateEditionCommand()),

    /** Create edition command */
    CREATE_EDITION(new CreateEditionCommand()),

    /** Load edition data to edition edit page command */
    PAGE_EDIT_EDITION(new PageEditEditionCommand()),

    /** Edit edition command */
    EDIT_EDITION(new EditEditionCommand()),

    /** Delete edition command */
    DELETE_EDITION(new DeleteEditionCommand()),

    /** Read edition command */
    READ_EDITION(new ReadEditionCommand()),

    /** Sort editions command */
    SORT(new SortEditionsCommand()),

    /** Get editions by theme command */
    EDITIONS_BY_THEME(new EditionsByThemeCommand()),

    /** Search editions by title command */
    SEARCH(new SearchEditionsCommand()),

    /** Get all themes by admin command */
    THEME_ADMIN_LIST(new ThemeListCommand()),

    /** Create theme command */
    CREATE_THEME(new CreateThemeCommand()),

    /** Load theme data to theme edit page command */
    PAGE_EDIT_THEME(new PageEditThemeCommand()),

    /** Edit theme command */
    EDIT_THEME(new EditThemeCommand()),

    /** Delete theme command */
    DELETE_THEME(new DeleteThemeCommand());

    ActionCommand command;

    CommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCurrentCommand() { return command; }
}
