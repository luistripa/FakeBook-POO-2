package exceptions;

/**
 * Exception thrown if the comment stance of a comment is invalid relative to the user kind.
 *
 * @author Luis Tripa 57882
 * @author Raquel Melo 57706
 *
 */
public class InvalidCommentStanceException extends Exception {

    public static final long serialVersionUID = 1L;

    public InvalidCommentStanceException() {

        super("Invalid comment stance!");
    }
}
