package exceptions;

/**
 * Exception thrown if there are no responsive users.
 *
 * @author Luis Tripa 57882
 * @author Raquel Melo 57706
 *
 */
public class NoResponsiveUsersException extends Exception {

    public static final long serialVersionUID = 1L;

    public NoResponsiveUsersException() {
        super("Social distancing has reached fakebook. Post something and then comment your own post to become the king of responsiveness.");
    }
}
