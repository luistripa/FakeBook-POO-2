package exceptions;

public class UserAlreadyExistsException extends Exception {

    public static final long serialVersionUID = 1L;

    public UserAlreadyExistsException(String userID) {
        super(userID+" already exists!");
    }
}
