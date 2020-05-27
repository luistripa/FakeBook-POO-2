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

/**
 * 
 * @author Luís Tripa ----- && Raquel Melo 57706
 *
 */
public class Main {

	// Success output messages
	public static final String USER_REGISTER	= "%s registered.\n";
	public static final String FRIEND_ADDED 	= "%s is friend of %s.\n";
	public static final String COMMENT_ADDED	= "Comment added!";
	public static final String EXIT_MESSAGE 	= "Bye!";

	// Error output messages
	public static final String UNKNOWN_COMMAND = "Unknown command. Type help to see available commands.";

	/**
	 * Main program. Calls the command interpreter.
	 * @param args - arguments needed to run the program.
	 */
	public static void main(String[] args) {

		// Initialize Scanner
		Scanner in = new Scanner(System.in);

		// Initialize FakeBook
		FakeBook fb = new FakeBookClass();

		Commands command = null;
		//Unless it receives the exit command, it will be waiting to process another command.
		while (command != Commands.EXIT) {
			command = getCommand(in);
			processCommand(command, in, fb);
		}

	}

	/**
	 * Commands interpreter.
	 */
	private static void processCommand(Commands command, Scanner in, FakeBook fb) {
		//Method that chooses which command will run, depending on the input//
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
				processPopularPost(fb);
				break;
			case TOPPOSTER:
				processTopPoster(fb);
				break;
			case RESPONSIVE:
				processResponsive(fb);
				break;
			case SHAMELESS:
				processShameless(fb);
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
	
	/**
	 * Method that tries to execute the Register command. If it fails it will process the adequate exception and send an output of the respective error message.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network Fakebook, to which we will add the user.
	 */
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

	/**
	 * Method that registers a user, by adding a new one to the collection of users. It fails if its identifier already exists or the input is invalid.
	 * In case the user is a fanatic, the list of fanaticisms given must be valid (doesnt have repeated fanaticisms).
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network Fakebook, to which we will add the user.
	 * @throws UserAlreadyExistsException, InvalidUserKindException, InvalidFanaticismListException.
	 */
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


	/**
	 * Method that tries to execute the Users command. If it fails it will process the adequate exception and send an output of the respective error message.
	 * 
	 * @param fb is the the social network Fakebook, to which we will add the user.
	 */
	private static void processUsers(FakeBook fb) {
		try {
			tryToProcessUsers(fb);
		} catch (NoUsersException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method that lists all the users in the fakebook. It fails if there are no users registered.
	 * 
	 * @param fb is the the social network Fakebook, to which we will add the user.
	 * @throws NoUsersException.
	 */
	private static void tryToProcessUsers(FakeBook fb) throws NoUsersException {
		Iterator<User> iter = fb.userIterator();
		while (iter.hasNext()) {
			User u = iter.next();
			System.out.printf("%s [%s] %d %d %d\n", u.getID(), u.getUserKind().getString(), u.getFriendCount(), u.getPostsCount(), u.getCommentsCount());
		}
	}

	/**
	 * Method that tries to execute the AddFriend command. If it fails it will process the adequate exception and send an output of the respective error message.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network Fakebook, to which we will add the user.
	 */
	private static void processAddFriend(Scanner in, FakeBook fb) {
		try {
			tryToProcessAddFriend(in, fb);
		} catch (UserDoesNotExistException | UsersAreAlreadyFriendsException | UserCannotFriendItselfException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method that sets a friendship between two users. If fails if one of the users doesnt exist; there is already a friendship between those two users or
	 * the user is trying to set a friendship with himself.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network Fakebook, to which we will add the user.
	 * @throws UserDoesNotExistException, UsersAreAlreadyFriendsException, UserCannotFriendItselfException.
	 */
	private static void tryToProcessAddFriend(Scanner in, FakeBook fb) throws UserDoesNotExistException, UsersAreAlreadyFriendsException, UserCannotFriendItselfException {
		String u1_ID = in.nextLine().trim();
		String u2_ID = in.nextLine().trim();

		fb.addFriend(u1_ID, u2_ID);
		System.out.printf(FRIEND_ADDED, u1_ID, u2_ID);
	}

	/**
	 * Method that tries to execute the Friends command. If it fails it will process the adequate exception and send an output of the respective error message.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network Fakebook, to which we will add the user.
	 */
	private static void processFriends(Scanner in, FakeBook fb) {
		try {
			tryToProcessFriends(in, fb);
		} catch (UserDoesNotExistException | UserHasNoFriendsException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method that lists all the friends of the given user. It fails if the given user is not registered or if the users hasnt any friends. 
	 *  
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network Fakebook, to which we will add the user.
	 * @throws UserDoesNotExistException, UserHasNoFriendsException.
	 */
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

	/**
	 * Method that tries to execute the Post command. If it fails it will process the adequate exception and send an output of the respective error message.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network Fakebook, to which we will add the user.
	 */
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

	/**
	 * Method that allows the user to post a new message. It fails if the user who is trying to post does not exist; the number of hashtags is not equal
	 * or greater than 0, or the post stance contradicts the the stance of the user.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network Fakebook, to which we will add the user.
	 * @throws UserDoesNotExistException, InvalidHashtagListException, InadequateStanceException.
	 */
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

	/**
	 * Method that tries to execute the UserPosts command. If it fails it will process the adequate exception and send an output of the respective error message.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network Fakebook, to which we will add the user.
	 */
	private static void processUserPosts(Scanner in, FakeBook fb) {
		try {
			tryToProcessUserPosts(in, fb);
		} catch (UserDoesNotExistException | UserHasNoPostsException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method that lists all the posts of the given user. It fails if the given user is not registered or if the users hasnt made any post yet. 
	 *  
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network Fakebook, to which we will add the user.
	 * @throws UserDoesNotExistException, UserHasNoPostsException.
	 */
	private static void tryToProcessUserPosts(Scanner in, FakeBook fb) throws UserDoesNotExistException, UserHasNoPostsException {
		String userID = in.nextLine().trim();
		
		Iterator<Post> iter = fb.userPostIterator(userID);
		System.out.println(userID + " posts:");
		while (iter.hasNext()) {
			Post post = iter.next();
			System.out.printf("%d. [%s] %s [%d comments]\n", post.getPostID(), post.getKind().getString(), post.getPostContent(), post.getCommentCount());
		}
	}

	/**
	 * Method that tries to execute the Comment command. If it fails it will process the adequate exception and send an output of the respective error message.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network Fakebook, to which we will add the user.
	 */
	private static void processComment(Scanner in, FakeBook fb) {
		try {
			tryToProcessComment(in, fb);
		} catch (UserDoesNotExistException | UserHasNoAccessToPostException | PostDoesNotExistException | CannotCommentOnPostException | InvalidCommentStanceException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method that allows the user to comment on a post. It fails if the user who is trying to comment does not exist; the user hasnt got access to that post
	 * (because the two users were not friends by the time the post was made); the post the user is trying to leave a comment on does not exist; the user cannot 
	 * comment on that post (e.g. because he is self-centered); or if the comment stance is invalid for that user id and that post.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network Fakebook, to which we will add the user.
	 * @throws UserDoesNotExistException, UserHasNoAccessToPostException, PostDoesNotExistException, CannotCommentOnPostException, InvalidCommentStanceException.
	 */
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

	/**
	 * Method that tries to execute the ReadPost command. If it fails it will process the adequate exception and send an output of the respective error message.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network Fakebook, to which we will add the user.
	 */
	private static void processReadPost(Scanner in, FakeBook fb) {
		try {
			tryToProcessReadPost(in, fb);
		} catch (UserDoesNotExistException | PostDoesNotExistException | NoCommentsException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method that shows the detailed information of a certain post, printing it. It fails if the author of the post is not registered; that user has not a post
	 * with the given id; or if that post hasnt any posts yet.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network Fakebook, to which we will add the user.
	 * @throws UserDoesNotExistException, PostDoesNotExistException, NoCommentsException.
	 */
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

	/**
	 * Method that tries to execute the CommentsByUser command. If it fails it will process the adequate exception and send an output of the respective error message.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network Fakebook, to which we will add the user.
	 */
	private static void processCommentsByUser(Scanner in, FakeBook fb) {
		try {
			tryToProcessCommentsByUser(in, fb);
		} catch (UserDoesNotExistException | NoCommentsException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method that lists all the comments made by a user on a certain topic/hashtag. It fails if the user is not registered; or if that user has not made any
	 * comments yet.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network Fakebook, to which we will add the user.
	 * @throws UserDoesNotExistException, NoCommentsException.
	 */
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

	/**
	 * Method that tries to execute the TopicFanatics command. If it fails it will process the adequate exception and send an output of the respective error message.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network Fakebook, to which we will add the user.
	 */
	private static void processTopicFanatics(Scanner in, FakeBook fb) {
		try {
			tryToProcessTopicFanatics(in, fb);
		} catch (FanaticismNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method that lists all the fanatic users on the given topic. It fails if that kind of fanaticism does not exist.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network Fakebook, to which we will add the user.
	 * @throws FanaticismNotFoundException.
	 */
	private static void tryToProcessTopicFanatics(Scanner in, FakeBook fb) throws FanaticismNotFoundException {
		String topic = in.nextLine().trim();

		Iterator<User> iter = fb.topicFanatics(topic);

		while (iter.hasNext()) {
			User u = iter.next();
			if (iter.hasNext())
				System.out.print(u.getID()+", ");
			else
				System.out.println(u.getID()+".");
		}
	}

	/**
	 * Method that tries to execute the TopicPosts command. If it fails it will process the adequate exception and send an output of the respective error message.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network Fakebook, to which we will add the user.
	 */
	private static void processTopicPosts(Scanner in, FakeBook fb) {
		try {
			tryToProcessTopicPosts(in, fb);
		} catch (InvalidNumberOfPostsToListException | TopicNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Method that lists all the posts on the given topic. It fails if the number of posts to list is invalid (it must be equal or greater than 1); or if the
	 * given topic does not exist.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network Fakebook, to which we will add the user.
	 * @throws InvalidNumberOfPostsToListException, TopicNotFoundException.
	 */
	private static void tryToProcessTopicPosts(Scanner in, FakeBook fb) throws InvalidNumberOfPostsToListException, TopicNotFoundException {
		String topic = in.next();
		int postNumber = in.nextInt();
		in.nextLine();

		if (postNumber < 1)
			throw new InvalidNumberOfPostsToListException();

		Iterator<Post> iter = fb.topicPosts(topic);

		while (iter.hasNext() && postNumber != 0) {
			Post p = iter.next();
			System.out.printf("%s %d %d: %s\n", p.getAuthor().getID(), p.getPostID(), p.getCommentCount(), p.getPostContent());
			postNumber--;
		}
	}
	
	/**
	 * Method that tries to execute the PopularPost command. If it fails it will process the adequate exception and send an output of the respective error message.
	 * 
	 * @param fb is the the social network Fakebook, to which we will add the user.
	 */
	private static void processPopularPost(FakeBook fb) {
		try {
			tryToProcessPopularPost(fb);
		} catch (NoPostsException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method that shows the most popular post, in other words, the most commented post. It prints its author id, the post id, the number of comments and
	 * the post content. It fails if there isnt any post on the social network yet.
	 * 
	 * @param fb is the the social network Fakebook, to which we will add the user.
	 * @throws NoPostsException.
	 */
	private static void tryToProcessPopularPost(FakeBook fb) throws NoPostsException {
		Post p = fb.popularPost();
		System.out.printf("%s %d %d: %s\n", p.getAuthor().getID(), p.getPostID(), p.getCommentCount(), p.getPostContent());
	}

	/**
	 * Method that tries to execute the TopPoster command. If it fails it will process the adequate exception and send an output of the respective error message.
	 * 
	 * @param fb is the the social network Fakebook, to which we will add the user.
	 */
	private static void processTopPoster(FakeBook fb) {
		try {
			tryToProcessTopPoster(fb);
		} catch (NoTopPosterException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Method that shows the top poster, in other words, the user who made more posts (and in case of a tie, the one who made more comments). It prints his/hers id,
	 * the number of posts and the number of comments made. It fails if there isnt any post on the social network yet.
	 * 
	 * @param fb is the the social network Fakebook, to which we will add the user.
	 * @throws NoTopPosterException.
	 */
	private static void tryToProcessTopPoster(FakeBook fb) throws NoTopPosterException {
		User u = fb.topPoster();
		System.out.printf("%s %d %d.\n", u.getID(), u.getPostsCount(), u.getCommentsCount());
	}

	/**
	 * Method that tries to execute the Responsive command. If it fails it will process the adequate exception and send an output of the respective error message.
	 * 
	 * @param fb is the the social network Fakebook, to which we will add the user.
	 */
	private static void processResponsive(FakeBook fb) {
		try {
			tryToProcessResponsive(fb);
		} catch (NoResponsivePostsException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method that shows the user with the highest percentage of commented posts. It fails if there isnt any post on the social network yet.
	 * 
	 * @param fb is the the social network Fakebook, to which we will add the user.
	 * @throws NoResponsivePostsException.
	 */
	private static void tryToProcessResponsive(FakeBook fb) throws NoResponsivePostsException {
		User u = fb.responsive();
		System.out.printf("%s %d %d.\n", u.getID(), u.getReadPostNumber(), u.getTotalAccessiblePosts());
	}

	/**
	 * Method that tries to execute the Shameless command. If it fails it will process the adequate exception and send an output of the respective error message.
	 * 
	 * @param fb is the the social network Fakebook, to which we will add the user.
	 */
	private static void processShameless(FakeBook fb) {
		try {
			tryToProcessShameless(fb);
		} catch (NoShamelessPostsException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method that shows the user who is the top liar, in other words, the one who has posted more lies, either by reinforcing a fake news or by giving 
	 * a negative comment to an honest post. It fails if there isnt any post on the social network yet.
	 * 
	 * @param fb is the the social network Fakebook, to which we will add the user.
	 * @throws NoShamelessPostsException.
	 */
	private static void tryToProcessShameless(FakeBook fb) throws NoShamelessPostsException {
		User u = fb.shameless();
		System.out.printf("%s %d.\n", u.getID(), u.getNumberOfLies());
	}


	/* Private Methods */

	/**
	 * Method that prints all the possible commands for this program, with a little description.
	 */
	private static void processHelp() {
		for (HelpMenu h: HelpMenu.values())
			System.out.println(h.getMessage());
	}

	/**
	 * Method that gets the right command to execute, according to the input.
	 * @param in
	 * @return the command to be executed. It fails if the input is invalid.
	 */
	private static Commands getCommand(Scanner in) {
		try {
			return Commands.valueOf(in.next().toUpperCase());
		} catch (IllegalArgumentException e) {
			return Commands.UNKNOWN;
		}
	}

	/**
	 * 
	 * @param userKind
	 * @return
	 * @throws InvalidUserKindException
	 */
	private static UserKind getUserKind(String userKind) throws InvalidUserKindException {
		try {
			return UserKind.valueOf(userKind.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new InvalidUserKindException(userKind);
		}
	}

	/**
	 * 
	 * @param postKind
	 * @return
	 */
	private static PostKind getPostKind(String postKind) {
		return PostKind.valueOf(postKind.toUpperCase());
	}

	/**
	 * 
	 * @param commentStance
	 * @return
	 */
	private static CommentStance getCommentStance(String commentStance) {
		return CommentStance.valueOf(commentStance.toUpperCase());
	}
}