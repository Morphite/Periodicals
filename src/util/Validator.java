package util;

import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Class Validator which check different types of data
 */
public class Validator {

    /** Constant Logger */
    private final static Logger LOG = Logger.getLogger(Validator.class);

    /** Constant pattern for login */
    private final static Pattern pLogin = Pattern.compile("([A-Za-zА-Яа-я0-9_-]){3,16}");

    /** Constant pattern for password */
    private final static Pattern pPassword = Pattern.compile("([A-Za-zА-Яа-я0-9_-]){8,20}");

    /** Constant pattern for email */
    private final static Pattern pEmail = Pattern.compile("([A-za-z0-9_\\.-]+)@([A-za-z0-9_\\.-]+)\\.([A-za-z\\.]{2,6})");

    /** Constant pattern for name */
    private final static Pattern pName = Pattern.compile("([A-Za-zА-Яа-я]){2,25}");

    /** Constant pattern for surname */
    private final static Pattern pSurname = Pattern.compile("([A-Za-zА-Яа-я]){2,25}");

    /** Constant pattern for title of edition */
    private final static Pattern pTitleEdition = Pattern.compile("(.){3,45}");

    /** Constant pattern for text of edition */
    private final static Pattern pTextEdition = Pattern.compile("(.){10,100}");

    /** Constant pattern for publisher of edition */
    private final static Pattern pPublisherEdition = Pattern.compile("(.){5,45}");

    /** Constant min price */
    private final static int PRICE_MIN = 1;

    /** Constant max price */
    private final static int PRICE_MAX = 1000;

    /** Constant min money to add */
    private final static int BALANCE_MIN_MONEY_TO_ADD = 10;

    /** Constant max money to add */
    private final static int BALANCE_MAX_MONEY_TO_ADD = 5000;

    /** Constant min money to create user */
    private final static int USER_MIN_MONEY_CREATE_USER = 0;

    /** Constant max money to create user */
    private final static int USER_MAX_MONEY_CREATE_USER = 10000;


    /**
     * Validate login.
     *
     * @param enterLogin the enter login
     * @return true, if successful
     */
    public static boolean validateLogin(String enterLogin) {
        Matcher mLogin = pLogin.matcher(enterLogin);
        LOG.debug("Validate Login: " + mLogin.matches());
        return mLogin.matches();
    }

    /**
     * Validate password.
     *
     * @param enterPass the enter pass
     * @return true, if successful
     */
    public static boolean validatePass(String enterPass) {
        Matcher mPassword = pPassword.matcher(enterPass);
        LOG.debug("Validate Password: " + mPassword.matches());
        return mPassword.matches();
    }

    /**
     * Validate email.
     *
     * @param enterEmail the enter email
     * @return true, if successful
     */
    public static boolean validateEmail(String enterEmail) {
        Matcher mEmail = pEmail.matcher(enterEmail);
        LOG.debug("Validate Email: " + mEmail.matches());
        return mEmail.matches();
    }

    /**
     * Validate name.
     *
     * @param enterName the enter name
     * @return true, if successful
     */
    public static boolean validateName(String enterName) {
        Matcher mName = pName.matcher(enterName);
        LOG.debug("Validate Name: " + mName.matches());
        return mName.matches();
    }

    /**
     * Validate surname.
     *
     * @param enterSurname the enter surname
     * @return true, if successful
     */
    public static boolean validateSurname(String enterSurname) {
        Matcher mSurname = pSurname.matcher(enterSurname);
        LOG.debug("Validate Surname: " + mSurname.matches());
        return mSurname.matches();
    }

    /**
     * Validate name tour.
     *
     * @param enterText the enter name tour
     * @return true, if successful
     */
    public static boolean validateTextEdition(String enterText) {
        Matcher mText = pTextEdition.matcher(enterText);
        LOG.debug("Validate TextEdition: " + mText.matches());
        return mText.matches();
    }

    /**
     * Validate country or city name.
     *
     * @param enterTitle the enter name
     * @return true, if successful
     */
    public static boolean validateTitleEdition(String enterTitle) {
        Matcher mTitle = pTitleEdition.matcher(enterTitle);
        LOG.debug("Validate TitleEdition: " + mTitle.matches());
        return mTitle.matches();
    }

    /**
     * Validate edition publisher.
     *
     * @param enterPublisher the enter publisher
     * @return true, if successful
     */
    public static boolean validatePublisherEdition(String enterPublisher) {
        Matcher mPublish = pPublisherEdition.matcher(enterPublisher);
        LOG.debug("Validate PublisherEdition: " + mPublish.matches());
        return mPublish.matches();
    }

    /**
     * Validate price.
     *
     * @param enterPrice the enter price
     * @return true, if successful
     */
    public static boolean validatePrice(double enterPrice) {
        if (enterPrice == 0) {
            return false;
        } else {
            LOG.debug("Validate Price: " + (enterPrice >= PRICE_MIN && enterPrice <= PRICE_MAX));
            return enterPrice >= PRICE_MIN && enterPrice <= PRICE_MAX;
        }
    }

    /**
     * Validate string.
     *
     * @param enterString the enter string
     * @return true, if successful
     */
    public static boolean validateString(String enterString) {
        LOG.debug("Validate String: " + !enterString.isEmpty());
        return (!enterString.isEmpty());
    }

    /**
     * Validate balance to add.
     *
     * @param money the money
     * @return true, if successful
     */
    public static boolean validateBalanceToAdd(double money) {
        LOG.debug("Validate money to add: " + (money >= BALANCE_MIN_MONEY_TO_ADD && money <= BALANCE_MAX_MONEY_TO_ADD));
        return money >= BALANCE_MIN_MONEY_TO_ADD && money <= BALANCE_MAX_MONEY_TO_ADD;
    }

    /**
     * Validate user create money.
     *
     * @param money the money
     * @return true, if successful
     */
    public static boolean validateUserCreateMoney(double money) {
        LOG.debug("Validate money create user: " + (money >= USER_MIN_MONEY_CREATE_USER && money <= USER_MAX_MONEY_CREATE_USER));
        return money >= USER_MIN_MONEY_CREATE_USER && money <= USER_MAX_MONEY_CREATE_USER;
    }
}
