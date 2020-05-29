package exceptions;

/**
 * Exception thrown if a user cannot comment on a post.
 *
 * @author Luis Tripa 57882
 * @author Raquel Melo 57706
 *
 */
public class CannotCommentOnPostException extends Exception {

    public static final long serialVersionUID = 1L;

    public CannotCommentOnPostException(String userID) {
        super(userID+" cannot comment on this post!");
    }
}
