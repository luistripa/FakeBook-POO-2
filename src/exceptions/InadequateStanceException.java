package exceptions;

/**
 * Exception thrown if a post has an inadequate exception relative to the user kind.
 *
 * @author Luis Tripa 57882
 * @author Raquel Melo 57706
 *
 */
public class InadequateStanceException extends Exception {

    public static final long serialVersionUID = 1L;

    public InadequateStanceException() {
        super("Inadequate stance!");
    }
}
