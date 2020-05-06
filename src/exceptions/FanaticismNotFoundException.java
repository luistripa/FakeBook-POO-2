package exceptions;

public class FanaticismNotFoundException extends Exception {

    public FanaticismNotFoundException(String fanaticismID) {
        super("Oh please, who would be a fanatic of "+fanaticismID+"?");
    }
}
