package posts;

import java.util.Iterator;
import java.util.List;

import comments.Comment;
import users.User;

/**
 * 
 * @author Luis Tripa ----- && Raquel Melo 57706
 *
 */
public interface Post {

	/**
	 * Getter method for the ID of the post.
	 *
	 * @return The ID of the post
	 */
	Integer getID();
	
	/**
	 * Getter method for the user/author.
	 *
	 * @return A user object representing the author of the post
	 */
	User getAuthor();
	
	/**
	 * Getter method for the kind of the post.
	 *
	 * @return PostKind object.
	 */
	PostKind getKind();
	
	/**
	 * Getter method for the number of comments on that post.
	 *
	 * @return The number of comments in the post.
	 */
	Integer getCommentCount();
	
	/**
	 * Getter method for the hashtags of the post.
	 *
	 * @return A list of hashtags
	 */
	List<String> getHashtags();
	
	/**
	 * Creates an iterator of all the comments in the post.
	 *
	 * @return An iterator of comments
	 */
	Iterator<Comment> commentThread();
	
	/**
	 * Getter method for the post content.
	 *
	 * @return The post content
	 */
	String getContent();

	/**
	 * Method that adds a comment to the post's comment thread
	 *
	 * @param comment The comment object
	 */
    void comment(Comment comment);
}
