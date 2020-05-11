package exceptions;

public class NoPostsException extends Exception {

    public static final long serialVersionUID = 1L;

    public NoPostsException() {
        super("Social distancing has reached fakebook. Please post something.");
    }
}
