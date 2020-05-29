package exceptions;

/**
 * Exception thrown if a given user does not exist
 *
 * @author Luis Tripa 57882
 * @author Raquel Melo 57706
 *
 */
public class UserDoesNotExistException extends Exception {

    public static final long serialVersionUID = 1L;

    public UserDoesNotExistException(String userID) {
        super(userID+" does not exist!");
    }
}
