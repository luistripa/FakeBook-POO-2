package exceptions;

public class PostDoesNotExistException extends Exception {

    public PostDoesNotExistException(String userID, int postID) {
        super(userID+" has no post "+postID+"!");
    }
}
