package exceptions;

public class InvalidCommentStanceException extends Exception {

    public static final long serialVersionUID = 1L;

    public InvalidCommentStanceException() {

        super("Invalid comment stance!");
    }
}
