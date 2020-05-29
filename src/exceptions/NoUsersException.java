package exceptions;

/**
 * Exception thrown if there are no registered users.
 *
 * @author Luis Tripa 57882
 * @author Raquel Melo 57706
 *
 */
public class NoUsersException extends Exception {

    public static final long serialVersionUID = 1L;

    public NoUsersException() {
        super("There are no users!");
    }
}
