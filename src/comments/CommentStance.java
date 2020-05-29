package comments;

/**
 * Enumerator that defines the stance of a comment.
 *
 * @author Luis Tripa 57882
 * @author Raquel Melo 57706
 *
 */
public enum CommentStance {

    POSITIVE("positive"),
    NEGATIVE("negative");

    private final String string;

    /**
	 * CommentStance constructor.
	 * @param string The string that identifies the stance. This is only used for output purposes only.
	 */
    CommentStance(String string) {
        this.string = string;
    }

    /**
	 * Getter method for the string.
	 * @return string
	 */
    public String getString() {
        return string;
    }
}
