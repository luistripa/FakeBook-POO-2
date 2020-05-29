package exceptions;

/**
 * Exception thrown if there are no shameless users.
 *
 * @author Luis Tripa 57882
 * @author Raquel Melo 57706
 *
 */
public class NoShamelessUsersException extends Exception {

    public static final long serialVersionUID = 1L;

    public NoShamelessUsersException() {
        super("Social distancing has reached fakebook. Post a lie and become the king of liars.");
    }
}
