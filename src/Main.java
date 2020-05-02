import java.util.Scanner;
import fakebook.*;
import helpmenu.HelpMenu;

public class Main {

	// Command constants
	public static final String REGISTER 		= "REGISTER";
	public static final String USERS 			= "USERS";
	public static final String ADDFRIEND 		= "ADDFRIEND";
	public static final String FRIENDS 			= "FRIENDS";
	public static final String POST 			= "POST";
	public static final String USERPOSTS 		= "USERPOSTS";
	public static final String COMMENT 			= "COMMENT";
	public static final String READPOST 		= "READPOST";
	public static final String COMMENTSBYUSER 	= "COMMENTSBYUSER";
	public static final String TOPICFANATICS 	= "TOPICFANATICS";
	public static final String TOPICPOSTS 		= "TOPICPOSTS";
	public static final String POPULARPOST 		= "POPULARPOST";
	public static final String TOPPOSTER 		= "TOPPOSTER";
	public static final String RESPONSIVE 		= "RESPONSIVE";
	public static final String SHAMELESS 		= "SHAMELESS";
	public static final String HELP 			= "HELP";
	public static final String EXIT 			= "EXIT";

	// Success output messages
	public static final String EXIT_MESSAGE = "Bye!";

	// Error output messages
	public static final String UNKNOWN_COMMAND = "Unknown command";


	public static void main(String[] args) {

		// Initialize Scanner
		Scanner in = new Scanner(System.in);

		// Initialize FakeBook
		FakeBook fb = new FakeBookClass();

		String command = "";
		while (!command.equals(EXIT)) {
			command = getCommand(in);
			processCommand(command, in, fb);
		}

	}

	private static void processCommand(String command, Scanner in, FakeBook fb) {
		switch (command) {
			case REGISTER -> processRegister(in, fb);
			case USERS -> processUsers(in, fb);
			case ADDFRIEND -> processAddFriend(in, fb);
			case FRIENDS -> processFriends(in, fb);
			case POST -> processPost(in, fb);
			case USERPOSTS -> processUserPosts(in, fb);
			case COMMENT -> processComment(in, fb);
			case READPOST -> processReadPost(in, fb);
			case COMMENTSBYUSER -> processCommentsByUser(in, fb);
			case TOPICFANATICS -> processTopicFanatics(in, fb);
			case TOPICPOSTS -> processTopicPosts(in, fb);
			case POPULARPOST -> processPopularPost(in, fb);
			case TOPPOSTER -> processTopPoster(in, fb);
			case RESPONSIVE -> processResponsive(in, fb);
			case SHAMELESS -> processShameless(in, fb);
			case HELP -> processHelp();
			case EXIT -> System.out.println(EXIT_MESSAGE);
			default -> System.out.println(UNKNOWN_COMMAND);
		}
	}

	private static void processRegister(Scanner in, FakeBook fb) {

	}

	private static void processUsers(Scanner in, FakeBook fb) {

	}

	private static void processAddFriend(Scanner in, FakeBook fb) {

	}

	private static void processFriends(Scanner in, FakeBook fb) {

	}

	private static void processPost(Scanner in, FakeBook fb) {

	}

	private static void processUserPosts(Scanner in, FakeBook fb) {

	}

	private static void processComment(Scanner in, FakeBook fb) {

	}

	private static void processReadPost(Scanner in, FakeBook fb) {

	}

	private static void processCommentsByUser(Scanner in, FakeBook fb) {

	}

	private static void processTopicFanatics(Scanner in, FakeBook fb) {

	}

	private static void processTopicPosts(Scanner in, FakeBook fb) {

	}

	private static void processPopularPost(Scanner in, FakeBook fb) {

	}

	private static void processTopPoster(Scanner in, FakeBook fb) {

	}

	private static void processResponsive(Scanner in, FakeBook fb) {

	}

	private static void processShameless(Scanner in, FakeBook fb) {

	}


	private static void processHelp() {
		for (HelpMenu h: HelpMenu.values())
			System.out.println(h.getMessage());
	}

	private static String getCommand(Scanner in) {
		return in.next().toUpperCase();
	}
}