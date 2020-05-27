package users;

/**
 * 
 * @author Luis Tripa ----- && Raquel Melo 57706
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
	 * @param userKind
	 */
    UserKind(String userKind) {
        this.userKind = userKind;
    }

    /**
	 * Getter method for the string.
	 * @return userKind
	 */
    public String getString() {
        return userKind;
    }
}
