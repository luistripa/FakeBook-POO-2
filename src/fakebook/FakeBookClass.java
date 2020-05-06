package fakebook;

import exceptions.NoUsersException;
import users.*;

import java.util.Iterator;

public class FakeBookClass implements FakeBook {

    private UserCollection users;

    public FakeBookClass() {
        users = new UserCollectionClass();
    }




    public Iterator<User> userIterator() throws NoUsersException {
        Iterator<User> iter = users.iterator();
        if (iter.hasNext())
            return iter;
        throw new NoUsersException();
    }
}
