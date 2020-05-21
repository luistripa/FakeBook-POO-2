import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import comments.CommentStance;
import exceptions.*;
import fakebook.*;
import enums.HelpMenu;
import posts.*;
import users.*;
import enums.*;

import comments.Comment;

public class Main {

	// Success output messages
	public static final String USER_REGISTER	= "%s registered.\n";
	public static final String FRIEND_ADDED 	= "%s is friend of %s.\n";
	public static final String COMMENT_ADDED	= "Comment added!";
	public static final String EXIT_MESSAGE 	= "Bye!";

	// Error output messages
	public static final String UNKNOWN_COMMAND = "Unknown command. Type help to see available commands.";


	public static void main(String[] args) {

		// Initialize Scanner
		Scanner in = new Scanner(System.in);

		// Initialize FakeBook
		FakeBook fb = new FakeBookClass();

		Commands command = null;
		while (command != Commands.EXIT) {
			command = getCommand(in);
			processCommand(command, in, fb);
		}

	}

	private static void processCommand(Commands command, Scanner in, FakeBook fb) {
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
				in.nextLine();
				break;
		}
	}


	private static void processRegister(Scanner in, FakeBook fb) {
		try {
			tryToProcessRegister(in, fb);
		} catch (UserAlreadyExistsException | InvalidUserKindException e) {
			System.out.println(e.getMessage());
		} catch (InvalidFanaticismListException e) {
			System.out.println(e.getMessage());
			in.nextLine();
		}
	}

	private static void tryToProcessRegister(Scanner in, FakeBook fb) throws UserAlreadyExistsException, InvalidUserKindException, InvalidFanaticismListException {
		String userKind = in.next();
		String userID = in.nextLine().trim();
		UserKind kind = getUserKind(userKind);

		if (kind == UserKind.FANATIC) {

			List<String> loves = new ArrayList<>();
			List<String> hates = new ArrayList<>();

			int fanaticismCount = in.nextInt();
			for (int i = 0; i < fanaticismCount; i++) {
				String stance = in.next();
				String topic = in.next();
				if (loves.contains(topic) || hates.contains(topic))
					throw new InvalidFanaticismListException();
				else {
					if (stance.equals("loves"))
						loves.add(topic);
					else
						hates.add(topic);
				}
			}
			in.nextLine();
			fb.addUser(userID, kind, loves, hates);
		} else
			fb.addUser(userID, kind);
		System.out.printf(USER_REGISTER, userID);
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
		} catch (UserDoesNotExistException | UsersAreAlreadyFriendsException | UserCannotFriendItselfException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void tryToProcessAddFriend(Scanner in, FakeBook fb) throws UserDoesNotExistException, UsersAreAlreadyFriendsException, UserCannotFriendItselfException {
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
			System.out.print(user.getID());
			if (iter.hasNext())
				System.out.print(", ");
			else
				System.out.print(".");
		}
		System.out.println();
	}


	private static void processPost(Scanner in, FakeBook fb) {
		try {
			tryToProcessPost(in, fb);
		} catch (UserDoesNotExistException | InadequateStanceException e) {
			System.out.println(e.getMessage());
		} catch (InvalidHashtagListException e) {
			System.out.println(e.getMessage());
			in.nextLine();
			in.nextLine();
		}
	}

	private static void tryToProcessPost(Scanner in, FakeBook fb) throws UserDoesNotExistException, InvalidHashtagListException, InadequateStanceException {
		String userID = in.nextLine().trim();
		int hashTagCount = in.nextInt();

		// Get hashtags
		List<String> hashtags = new ArrayList<>(hashTagCount);
		for (int i=0 ; i<hashTagCount ; i++) {
			String hash = in.next();
			if (hashtags.contains(hash))
				throw new InvalidHashtagListException();
			hashtags.add(hash);
		}
		in.nextLine();

		String postStance = in.next();
		PostKind stance = getPostKind(postStance);
		String postContent = in.nextLine().trim();

		int postID = fb.post(userID, hashtags, stance, postContent);

		System.out.printf("%s sent a %s post to %d friends. Post id = %d.\n", userID, stance.getString(), fb.getUserFriendCount(userID), postID);
 	}


	private static void processUserPosts(Scanner in, FakeBook fb) {
		try {
			tryToProcessUserPosts(in, fb);
		} catch (UserDoesNotExistException | UserHasNoPostsException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void tryToProcessUserPosts(Scanner in, FakeBook fb) throws UserDoesNotExistException, UserHasNoPostsException {
		String userID = in.nextLine().trim();
		
		Iterator<Post> iter = fb.userPostIterator(userID);
		System.out.println(userID + " posts:");
		while (iter.hasNext()) {
			Post post = iter.next();
			System.out.printf("%d. [%s] %s [%d comments]\n", post.getPostID(), post.getKind().getString(), post.getPostContent(), post.getCommentCount());
		}
	}


	private static void processComment(Scanner in, FakeBook fb) {
		try {
			tryToProcessComment(in, fb);
		} catch (UserDoesNotExistException | UserHasNoAccessToPostException | PostDoesNotExistException | CannotCommentOnPostException | InvalidCommentStanceException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void tryToProcessComment(Scanner in, FakeBook fb) throws UserDoesNotExistException, UserHasNoAccessToPostException, PostDoesNotExistException, CannotCommentOnPostException, InvalidCommentStanceException {
		String userID = in.nextLine().trim();
		String authorID = in.nextLine();
		int postID = in.nextInt();
		String stance = in.next();
		String comment = in.nextLine().trim();

		CommentStance commentStance = getCommentStance(stance);

		fb.comment(userID, authorID, postID, commentStance, comment);

		System.out.println(COMMENT_ADDED);
	}


	private static void processReadPost(Scanner in, FakeBook fb) {
		try {
			tryToProcessReadPost(in, fb);
		} catch (UserDoesNotExistException | PostDoesNotExistException | NoCommentsException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void tryToProcessReadPost(Scanner in, FakeBook fb) throws UserDoesNotExistException, PostDoesNotExistException, NoCommentsException {
		String userID = in.nextLine().trim();
		int postID = in.nextInt(); in.nextLine();
		
		String kind = fb.getPostKind(userID, postID).getString();
		
		System.out.println("[" + userID + " " + kind + "] " + fb.getPostContent(userID, postID));
		
		Iterator<Comment> iter = fb.postCommentsIterator(userID, postID);
		while (iter.hasNext()) {
			Comment comment = iter.next();
			System.out.println("[" + comment.getAuthor().getID() + " " + comment.getStance().getString() + "] " + comment.getCommentContent());
		}
	}


	private static void processCommentsByUser(Scanner in, FakeBook fb) {
		try {
			tryToProcessCommentsByUser(in, fb);
		} catch (UserDoesNotExistException | NoCommentsException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void tryToProcessCommentsByUser(Scanner in, FakeBook fb) throws UserDoesNotExistException, NoCommentsException  {
		String userID = in.nextLine().trim();
		String topicID = in.nextLine().trim();
		
		Iterator<Comment> iter = fb.commentsByUser(userID, topicID);
		while(iter.hasNext()) {
			Comment comment = iter.next();
			System.out.println("[" + comment.getPostAuthor() + " " + comment.getPostStance() + " " + comment.getPostID() + " " 
								+ comment.getStance().getString() + "] " + comment.getCommentContent());
		}
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

	private static Commands getCommand(Scanner in) {
		try {
			return Commands.valueOf(in.next().toUpperCase());
		} catch (IllegalArgumentException e) {
			return Commands.UNKNOWN;
		}
	}

	private static UserKind getUserKind(String userKind) throws InvalidUserKindException {
		try {
			return UserKind.valueOf(userKind.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new InvalidUserKindException(userKind);
		}
	}

	private static PostKind getPostKind(String postKind) {
		return PostKind.valueOf(postKind.toUpperCase());
	}

	private static CommentStance getCommentStance(String commentStance) {
		return CommentStance.valueOf(commentStance.toUpperCase());
	}
}