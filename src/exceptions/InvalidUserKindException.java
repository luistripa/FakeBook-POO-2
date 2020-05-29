package exceptions;

/**
 * Exception thrown if the given user kind is invalid.
 *
 * @author Luis Tripa 57882
 * @author Raquel Melo 57706
 *
 */
public class InvalidUserKindException extends Exception {

    public static final long serialVersionUID = 1L;

    public InvalidUserKindException(String userKind) {
        super(userKind+" is an invalid user kind!");
    }
}
