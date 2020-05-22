package posts;

import java.util.Iterator;
import java.util.List;

import comments.Comment;
import users.User;

public interface Post {

	Integer getPostID();
	
	User getAuthor();
	
	PostKind getKind();
	
	Integer getCommentCount();
	
	List<String> getHashtags();
	
	Iterator<Comment> commentThread();
	
	String getPostContent();

    void comment(Comment comment);
}
