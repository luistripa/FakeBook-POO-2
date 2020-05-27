package comments;

/**
 * 
 * @author Luis Tripa ----- && Raquel Melo 57706
 *
 */
public enum CommentStance {

    POSITIVE("positive"),
    NEGATIVE("negative");

	//CommentStance variables
    private String string;

    /**
	 * CommentStance constructor.
	 * @param string
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
