package exceptions;

public class UserHasNoFriendsException extends Exception {

    public UserHasNoFriendsException(String userID) {
        super(userID+" has no friends!");
    }
}
