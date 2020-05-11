package exceptions;

public class InvalidFanaticismListException extends Exception {

    public static final long serialVersionUID = 1L;

    public InvalidFanaticismListException() {
        super("Invalid fanaticism list!");
    }
}
