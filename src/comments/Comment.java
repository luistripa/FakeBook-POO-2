package comments;

import posts.Post;
import users.User;

/**
 * 
 * @author Luis Tripa 57882
 * @author Raquel Melo 57706
 *
 */
public interface Comment {

	/**
	 * Getter method for the user/author.
	 * @return author.
	 */
	User getAuthor();

	/**
	 * Getter method for the post to which the comment belongs.
	 * @return post.
	 */
	Post getPost();
	
	/**
	 * Getter method for the stance of the comment.
	 * @return stance.
	 */
	CommentStance getStance();
	
	/**
	 * Getter method for the comment content.
	 * @return commentContent.
	 */
	String getCommentContent();
	
	/**
	 * Getter method for the ID of the post to which the comment belongs.
	 * @return postID.
	 */
	int getID();
	
	/**
	 * Getter method for the kind of the post where the comment is.
	 * @return kind.
	 */
	String getPostStance();

	/**
	 * Getter method for the author of the post where the comment is.
	 * @return id of the author.
	 */
	String getPostAuthor();
	
}
