package exceptions;

public class InvalidUserKindException extends Exception {

    public static final long serialVersionUID = 1L;

    public InvalidUserKindException(String userKind) {
        super(userKind+" is an invalid user kind!");
    }
}
