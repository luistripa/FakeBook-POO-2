package exceptions;

/**
 * Exception thrown if the given post does not exist.
 *
 * @author Luis Tripa 57882
 * @author Raquel Melo 57706
 *
 */
public class PostDoesNotExistException extends Exception {

    public static final long serialVersionUID = 1L;

    public PostDoesNotExistException(String userID, int postID) {
        super(userID+" has no post "+postID+"!");
    }
}
