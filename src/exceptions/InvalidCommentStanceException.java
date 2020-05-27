package exceptions;

/**
 * 
 * @author Luis Tripa ----- && Raquel Melo 57706
 *
 */
public class InvalidCommentStanceException extends Exception {

    public static final long serialVersionUID = 1L;

    public InvalidCommentStanceException() {

        super("Invalid comment stance!");
    }
}
