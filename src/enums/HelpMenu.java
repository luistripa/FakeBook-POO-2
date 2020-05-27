package enums;

/**
 * 
 * @author Luis Tripa ----- && Raquel Melo 57706
 *
 */
public enum HelpMenu {
    REGISTER("register - registers a new user"),
    USERS("users - lists all users"),
    ADDFRIEND("addfriend - adds a new friend"),
    FRIENDS("friends - lists the user friends"),
    POST("post - posts a new message"),
    USERPOSTS("userposts - lists all posts by a user"),
    COMMENT("comment - user comments on a post"),
    READPOST("readpost - prints detailed info on a post"),
    COMMENTSBYUSER("commentsbyuser - shows all the comments by a user on a given post"),
    TOPICFANATICS("topicfanatics - shows a list of fanatic users on a topic"),
    TOPICPOSTS("topicposts - shows a list of posts on a given topic"),
    POPULARPOST("popularpost - shows the most commented post"),
    TOPPOSTER("topposter - shows the user with more posts"),
    RESPONSIVE("responsive - shows the user with a higher percentage of commented posts"),
    SHAMELESS("shameless - shows the top liars"),
    HELP("help - shows the available commands"),
    EXIT("exit - terminates the execution of the program");

	//HelpMenu variables
    private final String message;

    /**
	 * HelpMenu constructor.
	 * @param message
	 */
    HelpMenu(String message) {
        this.message = message;
    }

    /**
	 * Getter method for the message.
	 * @return message
	 */
    public String getMessage() {
        return message;
    }
}
