package exceptions;

/**
 * Exception thrown if the list of hashtags is invalid (repeated elements).
 *
 * @author Luis Tripa 57882
 * @author Raquel Melo 57706
 *
 */
public class InvalidHashtagListException extends Exception {

    public static final long serialVersionUID = 1L;

    public InvalidHashtagListException() {
        super("Invalid hashtags list!");
    }
}
