package exceptions;

/**
 * Exception thrown if there is no comments on a given post.
 *
 * @author Luis Tripa 57882
 * @author Raquel Melo 57706
 *
 */
public class NoCommentsException extends Exception {

    public static final long serialVersionUID = 1L;

    public NoCommentsException() {
        super("No comments!");
    }
}
