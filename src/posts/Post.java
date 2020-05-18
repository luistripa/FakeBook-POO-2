package posts;

import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;

import comments.Comment;
import users.User;

public interface Post {

	int getPostID();
	
	User getAuthor();
	
	PostKind getKind();
	
	int getCommentCount();
	
	List<String> getHashtags();
	
	Iterator<Comment> commentThread();
	
	String getPostContent();
	
	int compareTo(Post other);

    boolean isHonest();

    void comment(Comment comment);
}
