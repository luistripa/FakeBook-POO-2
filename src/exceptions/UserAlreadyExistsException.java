package exceptions;

/**
 * Exception thrown if a given user already exists after a register was attempted.
 *
 * @author Luis Tripa 57882
 * @author Raquel Melo 57706
 *
 */
public class UserAlreadyExistsException extends Exception {

    public static final long serialVersionUID = 1L;

    public UserAlreadyExistsException(String userID) {
        super(userID+" already exists!");
    }
}
