package exceptions;

/**
 * Exception thrown if a fanaticism is not found in the list
 *
 * @author Luis Tripa 57882
 * @author Raquel Melo 57706
 *
 */
public class FanaticismNotFoundException extends Exception {

    public static final long serialVersionUID = 1L;

    public FanaticismNotFoundException(String fanaticismID) {
        super("Oh please, who would be a fanatic of "+fanaticismID+"?");
    }
}
