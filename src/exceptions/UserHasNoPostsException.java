package exceptions;

/**
 * 
 * @author Luis Tripa ----- && Raquel Melo 57706
 *
 */
public class UserHasNoPostsException extends Exception {

    public static final long serialVersionUID = 1L;

    public UserHasNoPostsException(String userID) {
        super(userID+" has no posts!");
    }
}
