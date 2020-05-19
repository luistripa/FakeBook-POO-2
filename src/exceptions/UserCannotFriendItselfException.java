package exceptions;

public class UserCannotFriendItselfException extends Exception {

    public static final long serialVersionUID = 11L;

    public UserCannotFriendItselfException(String u1_id, String u2_id) {
        super(u1_id+" cannot be the same as "+u2_id+"!");
    }
}
