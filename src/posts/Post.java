package posts;

import java.util.Map;
import java.util.SortedMap;

import comments.Comment;
import hashtags.HashTag;
import users.User;

public interface Post {

	int getPostID();
	
	User getAuthor();
	
	PostKind getKind();
	
	int getCommentCount();
	
	Map<String, HashTag> getHashtags();
	
	SortedMap<String, Comment> getCommentThread();
	
	String getPostText();
	
	int compareTo(Post other);
	
}
