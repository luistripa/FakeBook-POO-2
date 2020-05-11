package exceptions;

public class NoTopPosterException extends Exception {

    public static final long serialVersionUID = 1L;

    public NoTopPosterException() {
        super("Social distancing has reached fakebook. Post something to become the king of posters.");
    }
}
