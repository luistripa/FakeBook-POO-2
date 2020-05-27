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
	 * @return postID.
	 */
	Integer getPostID();
	
	/**
	 * Getter method for the user/author.
	 * @return author.
	 */
	User getAuthor();
	
	/**
	 * Getter method for the kind of the post.
	 * @return PostKind.
	 */
	PostKind getKind();
	
	/**
	 * Getter method for the number of comments on that post.
	 * @return number of comments.
	 */
	Integer getCommentCount();
	
	/**
	 * Getter method for the hashtags of the post. 
	 * @return hashtags.
	 */
	List<String> getHashtags();
	
	/**
	 * Creates an iterator of all the comments in the post.
	 * @return the iterator.
	 */
	Iterator<Comment> commentThread();
	
	/**
	 * Getter method for the post content.
	 * @return postContent.
	 */
	String getPostContent();

	/**
	 * Method that allows to comment, adding a comment to the already existing comment thread.
	 * @param comment
	 */
    void comment(Comment comment);
}
