package exceptions;

/**
 * Exception thrown if there are no posts on FakeBook.
 *
 * @author Luis Tripa 57882
 * @author Raquel Melo 57706
 *
 */
public class NoPostsException extends Exception {

    public static final long serialVersionUID = 1L;

    public NoPostsException() {
        super("Social distancing has reached fakebook. Please post something.");
    }
}
