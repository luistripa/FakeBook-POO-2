package exceptions;

public class FanaticismNotFoundException extends Exception {

    public static final long serialVersionUID = 1L;

    public FanaticismNotFoundException(String fanaticismID) {
        super("Oh please, who would be a fanatic of "+fanaticismID+"?");
    }
}
