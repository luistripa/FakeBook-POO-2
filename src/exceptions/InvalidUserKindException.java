package exceptions;

public class InvalidUserKindException extends Exception {

    public InvalidUserKindException(String userKind) {
        super(userKind+" is an invalid user kind!");
    }
}
