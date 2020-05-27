package exceptions;

/**
 * 
 * @author Luis Tripa ----- && Raquel Melo 57706
 *
 */
public class InvalidHashtagListException extends Exception {

    public static final long serialVersionUID = 1L;

    public InvalidHashtagListException() {
        super("Invalid hashtags list!");
    }
}
