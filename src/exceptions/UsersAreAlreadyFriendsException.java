package exceptions;

public class UsersAreAlreadyFriendsException extends Exception {

    public static final long serialVersionUID = 1L;

    public UsersAreAlreadyFriendsException(String u1_ID, String u2_ID) {
        super(u1_ID+" must really admire "+u2_ID+"!");
    }
}
