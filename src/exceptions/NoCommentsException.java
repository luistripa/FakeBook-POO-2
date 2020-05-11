package exceptions;

public class NoCommentsException extends Exception {

    public static final long serialVersionUID = 1L;

    public NoCommentsException() {
        super("No comments!");
    }
}
