package exceptions;

/**
 * Exception thrown if the number of posts to list is less than 1.
 *
 * @author Luis Tripa 57882
 * @author Raquel Melo 57706
 *
 */
public class InvalidNumberOfPostsToListException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidNumberOfPostsToListException() {
        super("Invalid number of posts to present!");
    }
}
