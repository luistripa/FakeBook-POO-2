package exceptions;

/**
 * 
 * @author Luis Tripa ----- && Raquel Melo 57706
 *
 */
public class NoShamelessPostsException extends Exception {

    public static final long serialVersionUID = 1L;

    public NoShamelessPostsException() {
        super("Social distancing has reached fakebook. Post a lie and become the king of liars.");
    }
}
