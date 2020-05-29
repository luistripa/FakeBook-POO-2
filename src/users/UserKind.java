package users;

/**
 * An enumerator listing all kinds of users.
 * @author Luis Tripa 57882
 * @author Raquel Melo 57706
 *
 */
public enum UserKind {

    SELFCENTERED("selfcentered"),
    NAIVE("naive"),
    LIAR("liar"),
    FANATIC("fanatic");

	//UserKind variables
    private final String userKind;

    /**
	 * UserKind constructor.
     *
	 * @param userKind The user kind identifier.
	 */
    UserKind(String userKind) {
        this.userKind = userKind;
    }

    /**
	 * Getter method for the string.
     *
	 * @return The respective userKind ID
	 */
    public String getString() {
        return userKind;
    }
}
