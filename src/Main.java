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
			case REGISTER:
				processRegister(in, fb);
				break;
			case USERS:
				processUsers(in, fb);
				break;
			case ADDFRIEND:
				processAddFriend(in, fb);
				break;
			case FRIENDS:
				processFriends(in, fb);
				break;
			case POST:
				processPost(in, fb);
				break;
			case USERPOSTS:
				processUserPosts(in, fb);
				break;
			case COMMENT:
				processComment(in, fb);
				break;
			case READPOST:
				processReadPost(in, fb);
				break;
			case COMMENTSBYUSER:
				processCommentsByUser(in, fb);
				break;
			case TOPICFANATICS:
				processTopicFanatics(in, fb);
				break;
			case TOPICPOSTS:
				processTopicPosts(in, fb);
				break;
			case POPULARPOST:
				processPopularPost(in, fb);
				break;
			case TOPPOSTER:
				processTopPoster(in, fb);
				break;
			case RESPONSIVE:
				processResponsive(in, fb);
				break;
			case SHAMELESS:
				processShameless(in, fb);
				break;
			case HELP:
				processHelp();
				break;
			case EXIT:
				System.out.println(EXIT_MESSAGE);
				break;
			default:
				System.out.println(UNKNOWN_COMMAND);
				break;
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