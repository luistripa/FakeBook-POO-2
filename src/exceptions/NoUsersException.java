package exceptions;

public class NoUsersException extends Exception {

    public static final long serialVersionUID = 1L;

    public NoUsersException() {
        super("There are no users!");
    }
}
