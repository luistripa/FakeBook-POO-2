package exceptions;

/**
 * Exception thrown if there is not a top poster. This implies that there are no posts on FakeBook.
 *
 * @author Luis Tripa 57882
 * @author Raquel Melo 57706
 *
 */
public class NoTopPosterException extends Exception {

    public static final long serialVersionUID = 1L;

    public NoTopPosterException() {
        super("Social distancing has reached fakebook. Post something to become the king of posters.");
    }
}
