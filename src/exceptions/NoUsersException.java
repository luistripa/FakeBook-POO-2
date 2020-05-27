package exceptions;

/**
 * 
 * @author Luis Tripa ----- && Raquel Melo 57706
 *
 */
public class NoUsersException extends Exception {

    public static final long serialVersionUID = 1L;

    public NoUsersException() {
        super("There are no users!");
    }
}
