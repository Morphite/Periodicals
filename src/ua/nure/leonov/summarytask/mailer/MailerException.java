package ua.nure.leonov.summarytask.mailer;

public class MailerException extends Exception {
    public MailerException() {
        super();
    }

    public MailerException(String message) {
        super(message);
    }

    public MailerException(String message, Throwable cause) {
        super(message, cause);
    }

    public MailerException(Throwable cause) {
        super(cause);
    }

    protected MailerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
