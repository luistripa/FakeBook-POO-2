package exceptions;

public class InvalidHashtagListException extends Exception {

    public static final long serialVersionUID = 1L;

    public InvalidHashtagListException() {
        super("Invalid hashtags list!");
    }
}
