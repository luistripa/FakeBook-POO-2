package users;

import comments.CommentStance;
import posts.PostKind;

import java.util.List;

/**
 * 
 * @author Luis Tripa 57882
 * @author Raquel Melo 57706
 *
 */
public interface UserFanatic extends User {

	/**
	 * Boolean method that checks if the user hates a certain topic.
	 *
	 * @param fanaticism The topic ID
	 * @return true if the user hates it, false otherwise
	 */
    boolean hasHateFor(String fanaticism);

    /**
	 * Boolean method that checks if the user loves a certain topic.
	 *
	 * @param fanaticism The topic ID
	 * @return true if the user loves it, false otherwise
	 */
    boolean hasLoveFor(String fanaticism);

	/**
	 * Checks if the fanatic user can post with the given params
	 *
	 * @param hashtags The hashtag list of the post
	 * @param stance The post stance
	 * @return true if can post, false otherwise
	 */
	boolean canPost(List<String> hashtags, PostKind stance);

	/**
	 * Checks if fanatic use can comment on a given post
	 *
	 * @param hashtags The hashtag list of the given post
	 * @param stance The post stance
	 * @param commentStance The comment stance
	 * @return true if can comment, false otherwise
	 */
	boolean canComment(List<String> hashtags, PostKind stance, CommentStance commentStance);
}
