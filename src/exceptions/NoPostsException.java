package exceptions;

public class NoPostsException extends Exception {

	public NoPostsException() {
        super("Social distancing has reached fakebook. Please post something.");
    }
}
