package exceptions;

public class UserHasNoAccessToPostException extends Exception {

    public static final long serialVersionUID = 1L;

    public UserHasNoAccessToPostException(String userID, int postID, String authorID) {
        super(userID+" has no access to post "+postID+" by "+authorID+"!");
    }
}
