package users;

public interface User {

    String getID();

    UserKind getUserKind();

    int getFriendCount();

    int getPostsCount();

    int getCommentsCount();

}
