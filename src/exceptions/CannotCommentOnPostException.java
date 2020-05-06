package exceptions;

public class CannotCommentOnPostException extends Exception {

    public CannotCommentOnPostException(String userID) {
        super(userID+" cannot comment on this post!");
    }
}
