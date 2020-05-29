package exceptions;

/**
 * Exception thrown if the user has not friends.
 *
 * @author Luis Tripa 57882
 * @author Raquel Melo 57706
 *
 */
public class UserHasNoFriendsException extends Exception {

    public static final long serialVersionUID = 1L;

    public UserHasNoFriendsException(String userID) {
        super(userID+" has no friends!");
    }
}
