package exceptions;

/**
 * 
 * @author Luis Tripa ----- && Raquel Melo 57706
 *
 */
public class UserDoesNotExistException extends Exception {

    public static final long serialVersionUID = 1L;

    public UserDoesNotExistException(String userID) {
        super(userID+" does not exist!");
    }
}
