package exceptions;

/**
 * Exception thrown if a user does not have access to the post it is trying to comment on.
 *
 * @author Luis Tripa 57882
 * @author Raquel Melo 57706
 *
 */
public class UserHasNoAccessToPostException extends Exception {

    public static final long serialVersionUID = 1L;

    public UserHasNoAccessToPostException(String userID, int postID, String authorID) {
        super(userID+" has no access to post "+postID+" by "+authorID+"!");
    }
}
