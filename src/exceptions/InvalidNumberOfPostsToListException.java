package exceptions;

/**
 * 
 * @author Luis Tripa ----- && Raquel Melo 57706
 *
 */
public class InvalidNumberOfPostsToListException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidNumberOfPostsToListException() {
        super("Invalid number of posts to present!");
    }
}
