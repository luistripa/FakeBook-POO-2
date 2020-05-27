package posts;

/**
 * 
 * @author Luis Tripa ----- && Raquel Melo 57706
 *
 */
public enum PostKind {

    HONEST("honest"),
    FAKE("fake");

	//PostKind variables
    private final String postType;

    /**
	 * PostKind constructor.
	 * @param message
	 */
    PostKind(String message) {
        this.postType = message;
    }

    /**
	 * Getter method for the string.
	 * @return postType
	 */
    public String getString() {
        return postType;
    }
}
