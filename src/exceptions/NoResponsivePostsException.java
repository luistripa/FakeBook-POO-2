package exceptions;

public class NoResponsivePostsException extends Exception {

    public static final long serialVersionUID = 1L;

    public NoResponsivePostsException() {
        super("Social distancing has reached fakebook. Post something and then comment your own post to become the king of responsiveness.");
    }
}
