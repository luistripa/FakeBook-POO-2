package exceptions;

public class UserHasNoPostWithIDException extends Exception {

    public UserHasNoPostWithIDException(String userID, int postID) {
        super(userID+" has no post "+postID+"!");
    }
}
