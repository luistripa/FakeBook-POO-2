import java.util.Iterator;
import java.util.Scanner;

import exceptions.*;
import fakebook.*;
import helpmenu.HelpMenu;
import users.*;

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
	public static final String FRIEND_ADDED = "%s is friend of %s\n";
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
				processUsers(fb);
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
		try {
			tryToProcessRegister(in, fb);
		} catch (UserAlreadyExistsException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void tryToProcessRegister(Scanner in, FakeBook fb) throws UserAlreadyExistsException {
		// TODO
	}


	private static void processUsers(FakeBook fb) {
		try {
			tryToProcessUsers(fb);
		} catch (NoUsersException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void tryToProcessUsers(FakeBook fb) throws NoUsersException {
		Iterator<User> iter = fb.userIterator();
		while (iter.hasNext()) {
			User u = iter.next();
			System.out.printf("%s [%s] %d %d %d\n", u.getID(), u.getUserKind().getString(), u.getFriendCount(), u.getPostsCount(), u.getCommentsCount());
		}
	}


	private static void processAddFriend(Scanner in, FakeBook fb) {
		try {
			tryToProcessAddFriend(in, fb);
		} catch (UserDoesNotExistException | UsersAreAlreadyFriendsException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void tryToProcessAddFriend(Scanner in, FakeBook fb) throws UserDoesNotExistException, UsersAreAlreadyFriendsException {
		String u1_ID = in.nextLine().trim();
		String u2_ID = in.nextLine().trim();

		fb.addFriend(u1_ID, u2_ID);
		System.out.printf(FRIEND_ADDED, u1_ID, u2_ID);
	}


	private static void processFriends(Scanner in, FakeBook fb) {
		try {
			tryToProcessFriends(in, fb);
		} catch (UserDoesNotExistException | UserHasNoFriendsException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void tryToProcessFriends(Scanner in, FakeBook fb) throws UserDoesNotExistException, UserHasNoFriendsException {
		String userID = in.nextLine().trim();

		Iterator<User> iter = fb.userFriendIterator(userID);
		while (iter.hasNext()) {
			User user = iter.next();
			System.out.printf(user.getID());
			if (iter.hasNext())
				System.out.printf(", ");
			else
				System.out.printf(".");
		}
		System.out.println();
	}


	private static void processPost(Scanner in, FakeBook fb) {
		try {
			tryToProcessPost(in, fb);
		} catch (UserDoesNotExistException | InvalidHashtagListException | InadequateStanceException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void tryToProcessPost(Scanner in, FakeBook fb) throws UserDoesNotExistException, InvalidHashtagListException, InadequateStanceException {
		// TODO
	}


	private static void processUserPosts(Scanner in, FakeBook fb) {
		try {
			tryToProcessUserPosts(in, fb);
		} catch (UserDoesNotExistException e) {
			System.out.println(e.getMessage());
		} catch (UserHasNoPostsException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void tryToProcessUserPosts(Scanner in, FakeBook fb) throws UserDoesNotExistException, UserHasNoPostsException {
		// TODO
	}


	private static void processComment(Scanner in, FakeBook fb) {
		try {
			tryToProcessComment(in, fb);
		} catch (UserDoesNotExistException | UserHasNoAccessToPostException | PostDoesNotExistException | CannotCommentOnPostException | InvalidCommentStanceException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void tryToProcessComment(Scanner in, FakeBook fb) throws UserDoesNotExistException, UserHasNoAccessToPostException, PostDoesNotExistException, CannotCommentOnPostException, InvalidCommentStanceException {
		// TODO
	}


	private static void processReadPost(Scanner in, FakeBook fb) {
		try {
			tryToProcessReadPost(in, fb);
		} catch (UserDoesNotExistException | UserHasNoPostWithIDException | NoCommentsException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void tryToProcessReadPost(Scanner in, FakeBook fb) throws UserDoesNotExistException, UserHasNoPostWithIDException, NoCommentsException {
		// TODO
	}


	private static void processCommentsByUser(Scanner in, FakeBook fb) {
		try {
			tryToProcessCommentsByUser(in, fb);
		} catch (UserDoesNotExistException | NoCommentsException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void tryToProcessCommentsByUser(Scanner in, FakeBook fb) throws UserDoesNotExistException, NoCommentsException  {
		// TODO
	}


	private static void processTopicFanatics(Scanner in, FakeBook fb) {
		try {
			tryToProcessTopicFanatics(in, fb);
		} catch (FanaticismNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void tryToProcessTopicFanatics(Scanner in, FakeBook fb) throws FanaticismNotFoundException {
		// TODO
	}


	private static void processTopicPosts(Scanner in, FakeBook fb) {
		try {
			tryToProcessTopicPosts(in, fb);
		} catch (TopicNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void tryToProcessTopicPosts(Scanner in, FakeBook fb) throws TopicNotFoundException {
		// TODO
	}


	private static void processPopularPost(Scanner in, FakeBook fb) {
		try {
			tryToProcessPopularPost(in, fb);
		} catch (NoPostsException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void tryToProcessPopularPost(Scanner in, FakeBook fb) throws NoPostsException {
		// TODO
	}


	private static void processTopPoster(Scanner in, FakeBook fb) {
		try {
			tryToProcessTopPoster(in, fb);
		} catch (NoTopPosterException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void tryToProcessTopPoster(Scanner in, FakeBook fb) throws NoTopPosterException {
		// TODO
	}


	private static void processResponsive(Scanner in, FakeBook fb) {
		try {
			tryToProcessResponsive(in, fb);
		} catch (NoResponsivePostsException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void tryToProcessResponsive(Scanner in, FakeBook fb) throws NoResponsivePostsException {
		// TODO
	}

	private static void processShameless(Scanner in, FakeBook fb) {
		try {
			tryToProcessShameless(in, fb);
		} catch (NoShamelessPostsException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void tryToProcessShameless(Scanner in, FakeBook fb) throws NoShamelessPostsException {
		// TODO
	}


	/* Private Methods */

	private static void processHelp() {
		for (HelpMenu h: HelpMenu.values())
			System.out.println(h.getMessage());
	}

	private static String getCommand(Scanner in) {
		return in.next().toUpperCase();
	}

	private static UserKind getUserKind(String userKind) {
		for (UserKind uk: UserKind.values()) {
			if (uk.getString().equals(userKind))
				return uk;
		}
		throw new NoSuchUserKindException();
	}
}