package exceptions;

public class InadequateStanceException extends Exception {

    public static final long serialVersionUID = 1L;

    public InadequateStanceException() {
        super("Inadequate stance!");
    }
}
