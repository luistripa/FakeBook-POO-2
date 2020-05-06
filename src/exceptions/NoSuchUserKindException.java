package exceptions;

public class NoSuchUserKindException extends RuntimeException {

    public NoSuchUserKindException() {
        super("No such user kind. Make sure there is no typos.");
    }
}
