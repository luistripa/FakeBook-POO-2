package exceptions;

public class NoUsersException extends Exception {

    public NoUsersException() {
        super("There are no users!");
    }
}
