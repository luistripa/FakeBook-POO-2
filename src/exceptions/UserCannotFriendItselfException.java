package exceptions;

/**
 * 
 * @author Luis Tripa ----- && Raquel Melo 57706
 *
 */
public class UserCannotFriendItselfException extends Exception {

    public static final long serialVersionUID = 11L;

    public UserCannotFriendItselfException(String u1_id, String u2_id) {
        super(u1_id+" cannot be the same as "+u2_id+"!");
    }
}
