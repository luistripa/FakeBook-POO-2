package posts;

/**
 * An enumerator containing all kinds of posts.
 *
 * @author Luis Tripa 57882
 * @author Raquel Melo 57706
 *
 */
public enum PostKind {

    HONEST("honest"),
    FAKE("fake");

	//PostKind variables
    private final String postType;

    /**
	 * PostKind constructor.
	 * @param string
	 */
    PostKind(String string) {
        this.postType = string;
    }

    /**
	 * Getter method for the string.
	 * @return postType
	 */
    public String getString() {
        return postType;
    }
}
