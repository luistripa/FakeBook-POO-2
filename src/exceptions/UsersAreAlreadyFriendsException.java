package exceptions;

/**
 * Exception thrown if both users are already friends.
 *
 * @author Luis Tripa 57882
 * @author Raquel Melo 57706
 *
 */
public class UsersAreAlreadyFriendsException extends Exception {

    public static final long serialVersionUID = 1L;

    public UsersAreAlreadyFriendsException(String u1_ID, String u2_ID) {
        super(u1_ID+" must really admire "+u2_ID+"!");
    }
}
