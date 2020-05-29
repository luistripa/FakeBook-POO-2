package exceptions;

/**
 * Exception thrown if the list of fanaticisms is invalid (repeated or contradicting elements).
 *
 * @author Luis Tripa 57882
 * @author Raquel Melo 57706
 *
 */
public class InvalidFanaticismListException extends Exception {

    public static final long serialVersionUID = 1L;

    public InvalidFanaticismListException() {
        super("Invalid fanaticism list!");
    }
}
