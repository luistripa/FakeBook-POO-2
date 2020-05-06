package exceptions;

public class UserHasNoPostsException extends Exception {

    public UserHasNoPostsException(String userID) {
        super(userID+" has no posts!");
    }
}
