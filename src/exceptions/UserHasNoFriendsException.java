package exceptions;

/**
 * 
 * @author Luis Tripa ----- && Raquel Melo 57706
 *
 */
public class UserHasNoFriendsException extends Exception {

    public static final long serialVersionUID = 1L;

    public UserHasNoFriendsException(String userID) {
        super(userID+" has no friends!");
    }
}
