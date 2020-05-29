import java.util.*;


import fakebook.*;
import users.*;
import posts.*;
import comments.*;
import enums.*;
import exceptions.*;

/**
 * The main class. This holds all the primary operations
 *
 * @author Luis Tripa 57882
 * @author Raquel Melo 57706
 *
 */
public class Main {

	// Success output messages
	private static final String EXIT_MESSAGE 						= "Bye!";
	private static final String REGISTER_OUTPUT 					= "%s registered.\n";
	private static final String USERS_OUTPUT 						= "%s [%s] %d %d %d\n";
	private static final String ADDFRIEND_OUTPUT 					= "%s is friend of %s.\n";
	private static final String COMMENT_OUTPUT						= "Comment added!";
	private static final String POST_OUTPUT 						= "%s sent a %s post to %d friends. Post id = %d.\n";
	private static final String USERPOSTS_OUTPUT_TITLE 				= "%s posts:\n";
	private static final String USERPOSTS_OUTPUT_CONTENT 			= "%d. [%s] %s [%d comments]\n";
	private static final String READPOST_OUTPUT_TITLE 				= "[%s %s] %s\n";
	private static final String READPOST_OUTPUT_CONTENT 			= "[%s %s] %s\n";
	private static final String COMMENTS_BY_USER_OUTPUT 			= "[%s %s %d %s] %s\n";
	private static final String TOPIC_FANATICS_OUTPUT_COMMA 		= "%s, ";
	private static final String TOPIC_FANATICS_OUTPUT_TERMINATOR 	= "%s.\n";
	private static final String TOPIC_POSTS_OUTPUT 					= "%s %d %d: %s\n";
	private static final String POPULAR_POST_OUTPUT 				= "%s %d %d: %s\n";
	private static final String TOP_POSTER_OUTPUT 					= "%s %d %d.\n";
	private static final String RESPONSIVE_OUTPUT 					= "%s %d %d.\n";
	private static final String SHAMELESS_OUTPUT 					= "%s %d.\n";


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

		// Terminate the Scanner (I'll be back :P)
		in.close();
	}

	/**
	 * Command interpreter.
	 */
	private static void processCommand(Commands command, Scanner in, FakeBook fb) {
		//Method that chooses which command will run, depending on the input
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
				System.out.println(Commands.UNKNOWN.getMessage());
				in.nextLine();
				break;
		}
	}
	
	/**
	 * Method that tries to execute the Register command. If it fails it will process the adequate exception and send an output of the respective error message.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network FakeBook, to which we will add the user.
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
	 * Method that registers a user, by adding a new one to the collection of users.
	 * In case the user is a fanatic, the method also reads the user fanaticisms.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network FakeBook, to which we will add the user.
	 * @throws UserAlreadyExistsException if the user already exists.
	 * @throws InvalidUserKindException if the given user kind is invalid.
	 * @throws InvalidFanaticismListException if the user is a fanatic and the list of fanaticisms is invalid (repeated or contradicting elements)
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
		System.out.printf(REGISTER_OUTPUT, userID);
	}


	/**
	 * Method that tries to execute the Users command. If it fails it will process the adequate exception and send an output of the respective error message.
	 * 
	 * @param fb is the the social network FakeBook, to which we will add the user.
	 */
	private static void processUsers(FakeBook fb) {
		try {
			tryToProcessUsers(fb);
		} catch (NoUsersException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method that lists all the users in the FakeBook.
	 * 
	 * @param fb is the the social network FakeBook, to which we will add the user.
	 * @throws NoUsersException if there are no users registered in FakeBook. :-(
	 */
	private static void tryToProcessUsers(FakeBook fb) throws NoUsersException {
		Iterator<User> iter = fb.userIterator();
		while (iter.hasNext()) {
			User u = iter.next();
			System.out.printf(USERS_OUTPUT, u.getID(), u.getUserKind().getString(), u.getFriendCount(), u.getPostsCount(), u.getCommentsCount());
		}
	}

	/**
	 * Method that tries to execute the AddFriend command. If it fails it will process the adequate exception and send an output of the respective error message.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network FakeBook, to which we will add the user.
	 */
	private static void processAddFriend(Scanner in, FakeBook fb) {
		try {
			tryToProcessAddFriend(in, fb);
		} catch (UserDoesNotExistException | UsersAreAlreadyFriendsException | UserCannotFriendItselfException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method that sets a friendship between two users.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network FakeBook, to which we will add the user.
	 * @throws UserDoesNotExistException if any of the users do not exist.
	 * @throws UsersAreAlreadyFriendsException if the users are already friends.
	 * @throws UserCannotFriendItselfException if an user attempted to friend itself.
	 */
	private static void tryToProcessAddFriend(Scanner in, FakeBook fb) throws UserDoesNotExistException, UsersAreAlreadyFriendsException, UserCannotFriendItselfException {
		String u1_ID = in.nextLine().trim();
		String u2_ID = in.nextLine().trim();

		fb.addFriend(u1_ID, u2_ID);
		System.out.printf(ADDFRIEND_OUTPUT, u1_ID, u2_ID);
	}

	/**
	 * Method that tries to execute the Friends command. If it fails it will process the adequate exception and send an output of the respective error message.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network FakeBook, to which we will add the user.
	 */
	private static void processFriends(Scanner in, FakeBook fb) {
		try {
			tryToProcessFriends(in, fb);
		} catch (UserDoesNotExistException | UserHasNoFriendsException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method that lists all the friends of the given user.
	 *  
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network FakeBook, to which we will add the user.
	 * @throws UserDoesNotExistException if the user does not exist.
	 * @throws UserHasNoFriendsException if the user does not have any friends
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
	 * @param fb is the the social network FakeBook, to which we will add the user.
	 */
	private static void processPost(Scanner in, FakeBook fb) {
		try {
			tryToProcessPost(in, fb);
		} catch (UserDoesNotExistException | InadequateStanceException e) {
			System.out.println(e.getMessage());
		} catch (InvalidHashtagListException e) {
			System.out.println(e.getMessage());
			// These two nextLine() methods are necessary to guarantee that if the hashtag get operation fails mid-way it will always compensate the number of lines.
			in.nextLine();
			in.nextLine();
		}
	}

	/**
	 * Method that allows the user to post a new message.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network FakeBook, to which we will add the user.
	 * @throws UserDoesNotExistException if the user does not exist.
	 * @throws InvalidHashtagListException if the hashtag list is invalid (repeated elements).
	 * @throws InadequateStanceException if the post has an invalid stance according to the user kind.
	 */
	private static void tryToProcessPost(Scanner in, FakeBook fb) throws UserDoesNotExistException, InvalidHashtagListException, InadequateStanceException {
		String userID = in.nextLine().trim();
		int hashTagCount = in.nextInt();

		// Get topics/hashtags of post
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

		System.out.printf(POST_OUTPUT, userID, stance.getString(), fb.getUserFriendCount(userID), postID);
 	}

	/**
	 * Method that tries to execute the UserPosts command. If it fails it will process the adequate exception and send an output of the respective error message.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network FakeBook, to which we will add the user.
	 */
	private static void processUserPosts(Scanner in, FakeBook fb) {
		try {
			tryToProcessUserPosts(in, fb);
		} catch (UserDoesNotExistException | UserHasNoPostsException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method that lists all the posts of the given user.
	 *  
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network FakeBook, to which we will add the user.
	 * @throws UserDoesNotExistException if the user does not exist.
	 * @throws UserHasNoPostsException if the user does not have any posts.
	 */
	private static void tryToProcessUserPosts(Scanner in, FakeBook fb) throws UserDoesNotExistException, UserHasNoPostsException {
		String userID = in.nextLine().trim();
		
		Iterator<Post> iter = fb.userPostIterator(userID);
		System.out.printf(USERPOSTS_OUTPUT_TITLE, userID);

		while (iter.hasNext()) {
			Post post = iter.next();
			System.out.printf(USERPOSTS_OUTPUT_CONTENT, post.getID(), post.getKind().getString(), post.getContent(), post.getCommentCount());
		}
	}

	/**
	 * Method that tries to execute the Comment command. If it fails it will process the adequate exception and send an output of the respective error message.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network FakeBook, to which we will add the user.
	 */
	private static void processComment(Scanner in, FakeBook fb) {
		try {
			tryToProcessComment(in, fb);
		} catch (UserDoesNotExistException | UserHasNoAccessToPostException | PostDoesNotExistException | CannotCommentOnPostException | InvalidCommentStanceException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method that allows the user to comment on a post.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network FakeBook, to which we will add the user.
	 * @throws UserDoesNotExistException if one of both post author or comment author don't exist.
	 * @throws UserHasNoAccessToPostException if the comment author has no access to the post with given ID (because they are not friends or because the post was made before the comment author was a friend of the post author).
	 * @throws PostDoesNotExistException if the given post does not exist.
	 * @throws CannotCommentOnPostException if the comment author cannot comment on the post (because of an invalid user stance).
	 * @throws InvalidCommentStanceException if the stance of the comment is invalid.
	 */
	private static void tryToProcessComment(Scanner in, FakeBook fb) throws UserDoesNotExistException, UserHasNoAccessToPostException, PostDoesNotExistException, CannotCommentOnPostException, InvalidCommentStanceException {
		String userID = in.nextLine().trim();
		String authorID = in.nextLine();
		int postID = in.nextInt();
		String stance = in.next();
		String comment = in.nextLine().trim();

		CommentStance commentStance = getCommentStance(stance);

		fb.comment(userID, authorID, postID, commentStance, comment);

		System.out.println(COMMENT_OUTPUT);
	}

	/**
	 * Method that tries to execute the ReadPost command. If it fails it will process the adequate exception and send an output of the respective error message.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network FakeBook, to which we will add the user.
	 */
	private static void processReadPost(Scanner in, FakeBook fb) {
		try {
			tryToProcessReadPost(in, fb);
		} catch (UserDoesNotExistException | PostDoesNotExistException | NoCommentsException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method that shows the detailed information of a certain post, printing it.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network FakeBook, to which we will add the user.
	 * @throws UserDoesNotExistException if the given user does not exist.
	 * @throws PostDoesNotExistException if the given user does not have the post with the given ID.
	 * @throws NoCommentsException if the post has no comments.
	 */
	private static void tryToProcessReadPost(Scanner in, FakeBook fb) throws UserDoesNotExistException, PostDoesNotExistException, NoCommentsException {
		String userID = in.nextLine().trim();
		int postID = in.nextInt(); in.nextLine();
		
		String kind = fb.getPostKind(userID, postID).getString();

		System.out.printf(READPOST_OUTPUT_TITLE, userID, kind, fb.getPostContent(userID, postID));
		
		Iterator<Comment> iter = fb.postCommentsIterator(userID, postID);
		while (iter.hasNext()) {
			Comment comment = iter.next();
			System.out.printf(READPOST_OUTPUT_CONTENT, comment.getAuthor().getID(), comment.getStance().getString(), comment.getCommentContent());
		}
	}

	/**
	 * Method that tries to execute the CommentsByUser command. If it fails it will process the adequate exception and send an output of the respective error message.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network FakeBook, to which we will add the user.
	 */
	private static void processCommentsByUser(Scanner in, FakeBook fb) {
		try {
			tryToProcessCommentsByUser(in, fb);
		} catch (UserDoesNotExistException | NoCommentsException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method that lists all the comments made by a user on a certain topic/hashtag.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network FakeBook, to which we will add the user.
	 * @throws UserDoesNotExistException if the given user does not exist.
	 * @throws NoCommentsException if there are no comments on FakeBook.
	 */
	private static void tryToProcessCommentsByUser(Scanner in, FakeBook fb) throws UserDoesNotExistException, NoCommentsException  {
		String userID = in.nextLine().trim();
		String topicID = in.nextLine().trim();
		
		Iterator<Comment> iter = fb.commentsByUser(userID, topicID);
		while(iter.hasNext()) {
			Comment comment = iter.next();
			System.out.printf(COMMENTS_BY_USER_OUTPUT, comment.getPostAuthor(), comment.getPostStance(), comment.getID(), comment.getStance().getString(), comment.getCommentContent());
		}
	}

	/**
	 * Method that tries to execute the TopicFanatics command. If it fails it will process the adequate exception and send an output of the respective error message.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network FakeBook, to which we will add the user.
	 */
	private static void processTopicFanatics(Scanner in, FakeBook fb) {
		try {
			tryToProcessTopicFanatics(in, fb);
		} catch (FanaticismNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method that lists all the fanatic users on the given topic.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network FakeBook, to which we will add the user.
	 * @throws FanaticismNotFoundException if the given fanaticism was not found.
	 */
	private static void tryToProcessTopicFanatics(Scanner in, FakeBook fb) throws FanaticismNotFoundException {
		String topic = in.nextLine().trim();

		Iterator<User> iter = fb.topicFanatics(topic);

		while (iter.hasNext()) {
			User u = iter.next();
			if (iter.hasNext())
				System.out.printf(TOPIC_FANATICS_OUTPUT_COMMA, u.getID());
			else
				System.out.printf(TOPIC_FANATICS_OUTPUT_TERMINATOR, u.getID());
		}
	}

	/**
	 * Method that tries to execute the TopicPosts command. If it fails it will process the adequate exception and send an output of the respective error message.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network FakeBook, to which we will add the user.
	 */
	private static void processTopicPosts(Scanner in, FakeBook fb) {
		try {
			tryToProcessTopicPosts(in, fb);
		} catch (InvalidNumberOfPostsToListException | TopicNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Method that lists all the posts on the given topic.
	 * 
	 * @param in is the input from where the information will be read.
	 * @param fb is the the social network FakeBook, to which we will add the user.
	 * @throws InvalidNumberOfPostsToListException if the number of posts to list is less than 1.
	 * @throws TopicNotFoundException if the topic was not found.
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
			System.out.printf(TOPIC_POSTS_OUTPUT, p.getAuthor().getID(), p.getID(), p.getCommentCount(), p.getContent());
			postNumber--;
		}
	}
	
	/**
	 * Method that tries to execute the PopularPost command. If it fails it will process the adequate exception and send an output of the respective error message.
	 * 
	 * @param fb is the the social network FakeBook, to which we will add the user.
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
	 * the post content.
	 * 
	 * @param fb is the the social network FakeBook, to which we will add the user.
	 * @throws NoPostsException if there isn't any post on FakeBook yet.
	 */
	private static void tryToProcessPopularPost(FakeBook fb) throws NoPostsException {
		Post p = fb.popularPost();
		System.out.printf(POPULAR_POST_OUTPUT, p.getAuthor().getID(), p.getID(), p.getCommentCount(), p.getContent());
	}

	/**
	 * Method that tries to execute the TopPoster command. If it fails it will process the adequate exception and send an output of the respective error message.
	 * 
	 * @param fb is the the social network FakeBook, to which we will add the user.
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
	 * the number of posts and the number of comments made.
	 * 
	 * @param fb is the the social network FakeBook, to which we will add the user.
	 * @throws NoTopPosterException if there isn't any post on FakeBook yet.
	 */
	private static void tryToProcessTopPoster(FakeBook fb) throws NoTopPosterException {
		User u = fb.topPoster();
		System.out.printf(TOP_POSTER_OUTPUT, u.getID(), u.getPostsCount(), u.getCommentsCount());
	}

	/**
	 * Method that tries to execute the Responsive command. If it fails it will process the adequate exception and send an output of the respective error message.
	 * 
	 * @param fb is the the social network FakeBook, to which we will add the user.
	 */
	private static void processResponsive(FakeBook fb) {
		try {
			tryToProcessResponsive(fb);
		} catch (NoResponsiveUsersException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method that shows the user with the highest percentage of commented posts.
	 * 
	 * @param fb is the the social network FakeBook, to which we will add the user.
	 * @throws NoResponsiveUsersException if there isn't any post on FakeBook yet
	 */
	private static void tryToProcessResponsive(FakeBook fb) throws NoResponsiveUsersException {
		User u = fb.responsive();
		System.out.printf(RESPONSIVE_OUTPUT, u.getID(), u.getReadPostNumber(), u.getTotalAccessiblePosts());
	}

	/**
	 * Method that tries to execute the Shameless command. If it fails it will process the adequate exception and send an output of the respective error message.
	 * 
	 * @param fb is the the social network FakeBook, to which we will add the user.
	 */
	private static void processShameless(FakeBook fb) {
		try {
			tryToProcessShameless(fb);
		} catch (NoShamelessUsersException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method that shows the user who is the top liar, in other words, the one who has posted more lies, either by reinforcing a fake news or by giving 
	 * a negative comment to an honest post.
	 * 
	 * @param fb is the the social network FakeBook, to which we will add the user.
	 * @throws NoShamelessUsersException if there is no post on FakeBook which falls under the characteristics mentioned above.
	 */
	private static void tryToProcessShameless(FakeBook fb) throws NoShamelessUsersException {
		User u = fb.shameless();
		System.out.printf(SHAMELESS_OUTPUT, u.getID(), u.getNumberOfLies());
	}


	/* Private Methods */

	/**
	 * Method that prints all the possible commands for this program and their respective description.
	 */
	private static void processHelp() {
		for (Commands c: Commands.values())
			if (c != Commands.UNKNOWN)
				System.out.println(c.getMessage());
	}

	/**
	 * Method that gets the right command to execute, according to the input.
	 *
	 * @param in The Scanner object
	 * @return An object of type Commands corresponding to the right command to execute.
	 */
	private static Commands getCommand(Scanner in) {
		try {
			return Commands.valueOf(in.next().toUpperCase());
		} catch (IllegalArgumentException e) {
			return Commands.UNKNOWN;
		}
	}

	/**
	 * Method that converts string userKind to an object of type UserKind.
	 *
	 * @param userKind The string identifying the user kind.
	 * @return Object of type UserKind
	 * @throws InvalidUserKindException when the user kind specified was not found
	 */
	private static UserKind getUserKind(String userKind) throws InvalidUserKindException {
		try {
			return UserKind.valueOf(userKind.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new InvalidUserKindException(userKind);
		}
	}

	/**
	 * Method that converts string postKind to an object of type PostKind.
	 *
	 * @param postKind The string identifying the post kind (honest or fake).
	 * @return Object of type PostKind
	 */
	private static PostKind getPostKind(String postKind) {
		return PostKind.valueOf(postKind.toUpperCase());
	}

	/**
	 * Method that converts string commentStance to an object of type CommentStance.
	 *
	 * @param commentStance The string identifying the comment stance.
	 * @return Object of type CommentStance
	 */
	private static CommentStance getCommentStance(String commentStance) {
		return CommentStance.valueOf(commentStance.toUpperCase());
	}
}