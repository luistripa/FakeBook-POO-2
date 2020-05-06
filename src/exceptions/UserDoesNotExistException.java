package exceptions;

public class UserDoesNotExistException extends Exception {

    public UserDoesNotExistException(String userID) {
        super(userID+" does not exist!");
    }
}
