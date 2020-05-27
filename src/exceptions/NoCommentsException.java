package exceptions;

/**
 * 
 * @author Luis Tripa ----- && Raquel Melo 57706
 *
 */
public class NoCommentsException extends Exception {

    public static final long serialVersionUID = 1L;

    public NoCommentsException() {
        super("No comments!");
    }
}
