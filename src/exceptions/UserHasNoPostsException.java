package exceptions;

/**
 * Exception thrown if the user has no posts
 *
 * @author Luis Tripa 57882
 * @author Raquel Melo 57706
 *
 */
public class UserHasNoPostsException extends Exception {

    public static final long serialVersionUID = 1L;

    public UserHasNoPostsException(String userID) {
        super(userID+" has no posts!");
    }
}
