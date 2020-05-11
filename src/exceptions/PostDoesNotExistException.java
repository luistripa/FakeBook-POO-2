package exceptions;

public class PostDoesNotExistException extends Exception {

    public static final long serialVersionUID = 1L;

    public PostDoesNotExistException(String userID, int postID) {
        super(userID+" has no post "+postID+"!");
    }
}
