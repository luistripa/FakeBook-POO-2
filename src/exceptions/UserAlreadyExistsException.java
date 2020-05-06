package exceptions;

public class UserAlreadyExistsException extends Exception{

    public UserAlreadyExistsException(String userID) {
        super(userID+" already exists!");
    }
}
