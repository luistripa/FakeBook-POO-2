package fakebook;

import exceptions.NoUsersException;
import users.User;

import java.util.Iterator;

public interface FakeBook {


    Iterator<User> userIterator() throws NoUsersException;
}
